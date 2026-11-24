package com.brendonbody.clients.eat_club_responses;

import java.time.LocalTime;

public record RestaurantResponse(
        String objectId,
        String name,
        String address1,
        String suburb,
        String[] cuisines,
        String imageLink,
        LocalTime open,
        LocalTime close,
        DealResponse[] deals
){}
