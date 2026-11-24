package com.brendonbody.service;

import com.brendonbody.clients.RestaurantsApi;
import com.brendonbody.clients.eat_club_responses.RestaurantResponse;
import com.brendonbody.clients.eat_club_responses.RestaurantsResponse;
import com.brendonbody.config.EatClubObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;
import retrofit2.mock.Calls;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

public class FakeRestaurantsApi implements RestaurantsApi {
    private final ObjectMapper objectMapper = EatClubObjectMapper.create();

    @Override
    public Call<RestaurantsResponse> getRestaurants() {
        try (InputStream input = getClass().getResourceAsStream("/stubs/response.json")) {
            RestaurantsResponse restaurantsResponse = objectMapper.readValue(input, RestaurantsResponse.class);
            return Calls.response(restaurantsResponse);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
