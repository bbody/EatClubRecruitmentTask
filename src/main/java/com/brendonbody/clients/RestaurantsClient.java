package com.brendonbody.clients;

import com.brendonbody.clients.eat_club_responses.RestaurantResponse;
import com.brendonbody.errors.UpStreamException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RestaurantsClient {
    private final RestaurantsApi restaurantsApi;
    public RestaurantsClient(RestaurantsApi restaurantsApi) {
        this.restaurantsApi = restaurantsApi;
    }

    public List<RestaurantResponse> getRestaurants() {
        String APPLICATION_NAME = "Restaurant Details API";
        try {
            var restaurantsResponse = restaurantsApi.getRestaurants().execute();
            var restaurantsBody = restaurantsResponse.body();
            if (restaurantsBody == null) {
                throw new UpStreamException(APPLICATION_NAME, "Response is empty");
            }

            return Arrays.stream(restaurantsBody.restaurants()).toList();
        } catch (IOException e) {
            throw new UpStreamException(APPLICATION_NAME, "Issue calling", e);
        }
    }
}
