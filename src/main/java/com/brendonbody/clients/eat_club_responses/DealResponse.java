package com.brendonbody.clients.eat_club_responses;

import java.time.LocalTime;

public record DealResponse(
        String objectId,
        int discount,
        boolean dineIn,
        boolean lightning,
        int qtyLeft,
        LocalTime open,
        LocalTime close,
        LocalTime start,
        LocalTime end
){}
