package com.brendonbody.domain;

import com.brendonbody.clients.eat_club_responses.DealResponse;
import com.brendonbody.clients.eat_club_responses.RestaurantResponse;
import com.brendonbody.util.TimeParser;

import java.time.LocalTime;

public record Deal(
        String restaurantObjectId,
        String restaurantName,
        String restaurantAddress1,
        String restaurantSuburb,
        LocalTime restaurantOpen,
        LocalTime restaurantClose,
        String dealObjectId,
        int discount,
        boolean dineIn,
        boolean lightning,
        int qtyLeft
) {
    public boolean isDealActiveAt(LocalTime timeOfDay) {
        if (!restaurantOpen.isAfter(restaurantClose)) {
            // At deal start we except the deal to be active but only before the end
            return timeOfDay.isBefore(restaurantClose) && !timeOfDay.isBefore(restaurantOpen);
        }

        return isDealActiveOverMidnight(timeOfDay);
    }

    private boolean isDealActiveOverMidnight(LocalTime timeOfDay) {
        // Time of day is between open and midnight
        if (!timeOfDay.isBefore(restaurantOpen)) {
            return true;
        }

        // Time of day is after midnight but before close
        return timeOfDay.isBefore(restaurantClose);
    }

    public static Deal fromRestaurantDealResponse(RestaurantResponse restaurantResponse, DealResponse dealResponse) {
        return new Deal(
                restaurantResponse.objectId(),
                restaurantResponse.name(),
                restaurantResponse.address1(),
                restaurantResponse.suburb(),
                restaurantResponse.open(),
                restaurantResponse.close(),
                dealResponse.objectId(),
                dealResponse.discount(),
                dealResponse.dineIn(),
                dealResponse.lightning(),
                dealResponse.qtyLeft()
        );
    }
}
