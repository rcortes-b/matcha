package me.rcortesb.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OSMResponseDTO (
        @JsonProperty("display_name")
        String displayName,
        Address address
) {
    public record Address(String city,
                          String state,
                          @JsonProperty("country_code") String countryCode,
                          String town,
                          String province,
                          String village,
                          String region) {}
}
