package me.rcortesb.user.services.impl;

import me.rcortesb.user.domain.dto.LocationPointDTO;
import me.rcortesb.user.domain.dto.OSMResponseDTO;
import me.rcortesb.user.domain.entity.User;
import me.rcortesb.user.repository.UserRepository;
import me.rcortesb.user.services.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {
    final private WebClient webClient;
    final private UserRepository userRepository;

    public LocationServiceImpl(WebClient webClient, UserRepository userRepository) {
        this.webClient = webClient;
        this.userRepository = userRepository;
    }

    @Override
    public void updateLocation(String userId, LocationPointDTO locationPointDTO) {
        User currectUser = userRepository.findById(UUID.fromString(userId))
                                         .orElseThrow(() -> new RuntimeException("user not found"));
        double lat = locationPointDTO.latitude();
        double lon = locationPointDTO.longitude();

        OSMResponseDTO location = getCurrentLocation(lat, lon);
        currectUser.setLongitude(lon);
        currectUser.setLatitude(lat);
        currectUser.setCity(getFirstValidAddress(location.address().city(),
                                                 location.address().town(),
                                                 location.address().village()));
        currectUser.setState(getFirstValidAddress(location.address().state(),
                                                  location.address().province(),
                                                  location.address().region()));
        currectUser.setCountryCode(location.address().countryCode());
        userRepository.save(currectUser);
    }

    private OSMResponseDTO getCurrentLocation(double lat, double lon) {
        OSMResponseDTO apiResponse =
                webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/reverse")
                                .queryParam("format", "json")
                                .queryParam("lat", lat)
                                .queryParam("lon", lon)
                                .build())
                        .retrieve()
                        .bodyToMono(OSMResponseDTO.class)
                        .block();
        return apiResponse;
    }

    private String getFirstValidAddress(String... values) {
        for (String address : values) {
            if (address != null && !address.isBlank()) {
                if (address.contains("/")) {
                    return address.substring(0, address.indexOf("/")).trim();
                }
                return address;
            }
        }
        return "unknown";
    }
}
