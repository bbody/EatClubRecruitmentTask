package com.brendonbody.controllers.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record DealResponse(
        String restaurantObjectId,
        String restaurantName,
        String restaurantAddress1,
        String restaurantSuburb,

        // To meet the assignment spec
        @JsonFormat(pattern = "h:mma", locale = "en")
        LocalTime restaurantOpen,

        // To meet the assignment spec
        @JsonFormat(pattern = "h:mma", locale = "en")
        LocalTime restaurantClose,

        String dealObjectId,

        // To meet the assignment spec
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        int discount,

        // To meet the assignment spec
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        boolean dineIn,

        // To meet the assignment spec
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        boolean lightning,

        // To meet the assignment spec
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        int qtyLeft
){
    public static DealResponse fromDomain(com.brendonbody.domain.Deal domainDeal) {
        return new DealResponse(
                domainDeal.restaurantObjectId(),
                domainDeal.restaurantName(),
                domainDeal.restaurantAddress1(),
                domainDeal.restaurantSuburb(),
                domainDeal.restaurantOpen(),
                domainDeal.restaurantClose(),
                domainDeal.dealObjectId(),
                domainDeal.discount(),
                domainDeal.dineIn(),
                domainDeal.lightning(),
                domainDeal.qtyLeft()
        );
    }
}
