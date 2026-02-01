package me.rcortesb.user.controller;

import jakarta.validation.Valid;
import me.rcortesb.user.domain.dto.LocationPointDTO;
import me.rcortesb.user.services.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/location")
public class LocationController {
    final private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PatchMapping
    public ResponseEntity<Void> updateLocation(@RequestHeader("X-User-Id") String userId,
                                            @RequestBody @Valid LocationPointDTO locationPointDTO) {
        locationService.updateLocation(userId, locationPointDTO);
        return ResponseEntity.ok().build();
    }


}
