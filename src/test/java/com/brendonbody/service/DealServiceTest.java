package com.brendonbody.service;

import com.brendonbody.clients.RestaurantsApi;
import com.brendonbody.clients.RestaurantsClient;
import com.brendonbody.domain.Deal;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DealServiceTest {
    RestaurantsApi fakeRestaurantsApi = new FakeRestaurantsApi();
    RestaurantsClient restaurantsClient = new RestaurantsClient(fakeRestaurantsApi);
    DealsService restaurantService = new DealsService(restaurantsClient);

    @Test
    void returnsAllDeals() {
        var deals = assertDoesNotThrow(() -> restaurantService.getDeals(null));
        assertEquals(11, deals.size());
    }

    @Test
    void returnsAllRestaurants() {
        var deals = assertDoesNotThrow(() -> restaurantService.getDeals(null));
        var uniqueRestaurantNames = getUniqueRestaurantNames(deals);

        assertEquals(6, uniqueRestaurantNames.size());

        assertTrue(uniqueRestaurantNames.containsAll(List.of(
                "Masala Kitchen",
                "ABC Chicken",
                "Vrindavan",
                "Kekou",
                "Gyoza Gyoza Melbourne Central",
                "OzzyThai Cafe Bar "
        )));
    }

    @Test
    void mapsDealsFromRestaurant() {
        var deals = assertDoesNotThrow(() -> restaurantService.getDeals(null));

        // First 2 should be Masala Kitchen
        IntStream.range(0, 2).forEach((idx) -> {
            var deal = deals.get(idx);
            assertEquals("DEA567C5-F64C-3C03-FF00-E3B24909BE00", deal.restaurantObjectId());
            assertEquals("Masala Kitchen", deal.restaurantName());
            assertEquals("55 Walsh Street", deal.restaurantAddress1());
            assertEquals("Lower East", deal.restaurantSuburb());
            assertEquals(LocalTime.of(15,0), deal.restaurantOpen());
            assertEquals(LocalTime.of(21,0), deal.restaurantClose());
        });
        assertEquals("DEA567C5-0000-3C03-FF00-E3B24909BE00", deals.get(0).dealObjectId());
        assertEquals(50, deals.get(0).discount());
        assertEquals(false, deals.get(0).dineIn());
        assertEquals(true, deals.get(0).lightning());
        assertEquals(5, deals.get(0).qtyLeft());
    }

    @Test
    void filterByTimeOfDayWhenNoBusinessesOpen() {
        var deals = assertDoesNotThrow(() -> restaurantService.getDeals(LocalTime.MIDNIGHT));
        assertEquals(0, deals.size());
    }

    @Test
    void filterByTimeOfDayWhenAfterMostBusinessesOpen() {
        var deals = assertDoesNotThrow(() -> restaurantService.getDeals(LocalTime.of(22, 0)));
        assertEquals(4, deals.size());
        var restaurantNames = getUniqueRestaurantNames(deals);
        assertTrue(restaurantNames.containsAll(List.of(
                "ABC Chicken",
                "Kekou"
        )));
    }

    @Test
    void filterByTimeOfDayWhenBeforeMostBusinessesOpen() {
        var deals = assertDoesNotThrow(() -> restaurantService.getDeals(LocalTime.of(10, 0)));
        assertEquals(2, deals.size());
        var restaurantNames = getUniqueRestaurantNames(deals);
        assertEquals(1, restaurantNames.size());
        assertEquals("OzzyThai Cafe Bar ", restaurantNames.get(0));
    }

    @NotNull
    private static List<String> getUniqueRestaurantNames(List<Deal> deals) {
        return deals.stream().map((Deal::restaurantName)).collect(Collectors.toSet()).stream().toList();
    }
}
