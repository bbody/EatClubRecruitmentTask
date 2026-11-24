package com.brendonbody.domain;

import com.brendonbody.util.TimeParser;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealTest {
    private final LocalTime openTime = LocalTime.of(9,0);
    private final LocalTime closeTime = LocalTime.of(17, 0);
    private final Deal deal = buildDeal(openTime, closeTime);

    @Test
    public void timeIsBeforeOpen() {
        assertFalse(deal.isDealActiveAt(LocalTime.of(8,0)));
    }

    @Test
    public void timeIsAfterOpen() {
        assertFalse(deal.isDealActiveAt(LocalTime.of(18,0)));
    }

    @Test
    public void timeIsDuringOpenHours() {
        assertTrue(deal.isDealActiveAt(LocalTime.of(12,0)));
    }

    @Test
    public void timeIsAtOpen() {
        assertTrue(deal.isDealActiveAt(openTime));
    }

    @Test
    public void timeIsJustBeforeOpen() {
        assertFalse(deal.isDealActiveAt(openTime.minusMinutes(1)));
    }

    @Test
    public void timeIsJustAfterOpen() {
        assertTrue(deal.isDealActiveAt(openTime.plusMinutes(1)));
    }

    @Test
    public void timeIsAtClose() {
        assertFalse(deal.isDealActiveAt(closeTime));
    }

    @Test
    public void timeIsJustBeforeClose() {
        assertTrue(deal.isDealActiveAt(closeTime.minusMinutes(1)));
    }

    @Test
    public void timeIsJustAfterClose() {
        assertFalse(deal.isDealActiveAt(closeTime.plusMinutes(1)));
    }

    @Test
    public void openTimeIsAfterCloseTimeDuringBusinessHours() {
        // Some buisnesses may have close "earlier" than opening e.g. nightclock opens at 10pm but closes at 2am
        // 10pm to 2am
        var deal = buildDeal(
                LocalTime.of(22,0),
                LocalTime.of(2, 0)
        );
        assertTrue(deal.isDealActiveAt(LocalTime.of(23, 0)));
    }

    @Test
    public void openTimeIsAfterCloseTimeOutsideBusinessHours() {
        // Some buisnesses may have close "earlier" than opening e.g. nightclock opens at 10pm but closes at 2am
        // 10pm to 2am
        var deal = buildDeal(
                LocalTime.of(22,0),
                LocalTime.of(2, 0)
        );
        assertFalse(deal.isDealActiveAt(LocalTime.of(21, 0)));
    }

    private Deal buildDeal(LocalTime open, LocalTime close) {
        return new Deal(
                "SOME_RESTAURANT_ID",
                "SOME NAME",
                "10 Place Street",
                "Melbourne",
                open,
                close,
                "SOME_DEAL_ID",
                50,
                false,
                true,
                42
        );
    }
}
