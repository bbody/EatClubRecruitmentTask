package com.brendonbody.domain;

import com.brendonbody.clients.eat_club_responses.DealResponse;
import com.brendonbody.clients.eat_club_responses.RestaurantResponse;

import java.time.LocalTime;

public record DealWindow(
        LocalTime start,
        LocalTime end
) {
    public static DealWindow fromRestaurantResponse(RestaurantResponse restaurantResponse, DealResponse dealResponse) {
        // Assuming if no time on the deal, then must be when open
        var start = dealResponse.start() != null ? dealResponse.start() : restaurantResponse.open();
        var close = dealResponse.close() != null ? dealResponse.close() : restaurantResponse.close();

        return new DealWindow(start, close);
    }
}
