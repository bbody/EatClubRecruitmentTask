package com.brendonbody.service;

import com.brendonbody.clients.RestaurantsClient;
import com.brendonbody.domain.DealWindow;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


@Service
public class PeakTimesService {
    private final RestaurantsClient restaurantsClient;

    public PeakTimesService(RestaurantsClient restaurantsClient) {
        this.restaurantsClient = restaurantsClient;
    }

    public DealWindow getPeakTimes() {
        List<DealWindow> dealTimes = restaurantsClient.getRestaurants()
                .stream()
                .flatMap(
                        (restaurantResponse -> Arrays.stream(restaurantResponse.deals())
                                .map((dealResponse) -> DealWindow.fromRestaurantResponse(restaurantResponse, dealResponse))))
                .toList();
        IntervalHistogram intervalHistogram = new IntervalHistogram();
        dealTimes.forEach(intervalHistogram::addDealTime);
        return intervalHistogram.getPeakTime();
    }
}
