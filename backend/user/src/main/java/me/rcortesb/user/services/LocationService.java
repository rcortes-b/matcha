package me.rcortesb.user.services;

import me.rcortesb.user.domain.dto.LocationPointDTO;

public interface LocationService {
    void updateLocation(String userId, LocationPointDTO locationPointDTO);
	void loadUsersToES();
}
