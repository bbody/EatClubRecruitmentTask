package com.brendonbody.clients;

import com.brendonbody.clients.eat_club_responses.RestaurantsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantsApi {
    @GET("/misc/challengedata.json")
    public abstract Call<RestaurantsResponse> getRestaurants();
}
