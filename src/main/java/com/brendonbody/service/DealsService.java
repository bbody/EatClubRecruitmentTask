package com.brendonbody.service;

import com.brendonbody.clients.RestaurantsClient;
import com.brendonbody.clients.eat_club_responses.RestaurantResponse;
import com.brendonbody.domain.Deal;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DealsService {
    private final RestaurantsClient restaurantsClient;

    public DealsService(RestaurantsClient restaurantsClient) {
        this.restaurantsClient = restaurantsClient;
    }

    public List<Deal> getDeals(LocalTime timeOfDay) {
        return restaurantsClient.getRestaurants()
                .stream()
                .flatMap(DealsService::extreactDeals)
                .filter(deal -> timeOfDay == null || deal.isDealActiveAt(timeOfDay))
                .toList();
    }

    private static Stream<Deal> extreactDeals(RestaurantResponse restaurantResponse) {
        return Arrays.stream(restaurantResponse.deals()).map(
                (dealResponse) -> Deal.fromRestaurantDealResponse(restaurantResponse, dealResponse)
        );
    }
}
