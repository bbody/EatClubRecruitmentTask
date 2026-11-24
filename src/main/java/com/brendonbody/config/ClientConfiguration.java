package com.brendonbody.config;

import com.brendonbody.clients.RestaurantsApi;
import com.brendonbody.clients.RestaurantsClient;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class ClientConfiguration {
    @Bean
    public RestaurantsApi restaurantsApi() {
        OkHttpClient httpClient = new OkHttpClient();

        return new Retrofit.Builder()
                .baseUrl("https://eccdn.com.au")
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(EatClubObjectMapper.create()))
                .build()
                .create(RestaurantsApi.class);
    }

    @Bean
    public RestaurantsClient restaurantsClient(RestaurantsApi restaurantsApi) {
        return new RestaurantsClient(restaurantsApi);
    }
}
