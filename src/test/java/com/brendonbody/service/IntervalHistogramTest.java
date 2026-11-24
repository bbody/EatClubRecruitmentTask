package com.brendonbody.service;

import com.brendonbody.domain.DealWindow;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntervalHistogramTest {
    @Test
    public void singleTimeIsThePeak() {
        var deal = new DealWindow(
                LocalTime.of(9, 0),
                LocalTime.of(17, 0)
        );

        var intervalHistogram = new IntervalHistogram();
        intervalHistogram.addDealTime(deal);

        var peakWindow = intervalHistogram.getPeakTime();

        assertEquals(deal, peakWindow);
    }

    @Test
    public void firstSameTimeIsThePeak() {
        var deal1 = new DealWindow(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)
        );
        var deal2 = new DealWindow(
                LocalTime.of(11, 0),
                LocalTime.of(12, 0)
        );

        var intervalHistogram = new IntervalHistogram();
        intervalHistogram.addDealTime(deal1);
        intervalHistogram.addDealTime(deal2);

        var peakWindow = intervalHistogram.getPeakTime();

        assertEquals(deal1, peakWindow);
    }

    @Test
    public void overLapIsThePeak() {
        var deal1 = new DealWindow(
                LocalTime.of(9, 0),
                LocalTime.of(12, 0)
        );
        var deal2 = new DealWindow(
                LocalTime.of(11, 0),
                LocalTime.of(14, 0)
        );

        var intervalHistogram = new IntervalHistogram();
        intervalHistogram.addDealTime(deal1);
        intervalHistogram.addDealTime(deal2);

        var peakWindow = intervalHistogram.getPeakTime();

        assertEquals(deal2.start(), peakWindow.start());
        assertEquals(deal1.end(), peakWindow.end());
    }

    @Test
    public void overLapWithMinutesIsThePeak() {
        var deal1 = new DealWindow(
                LocalTime.of(9, 15),
                LocalTime.of(11, 45)
        );
        var deal2 = new DealWindow(
                LocalTime.of(10, 15),
                LocalTime.of(14, 30)
        );

        var intervalHistogram = new IntervalHistogram();
        intervalHistogram.addDealTime(deal1);
        intervalHistogram.addDealTime(deal2);

        var peakWindow = intervalHistogram.getPeakTime();

        assertEquals(deal2.start(), peakWindow.start());
        assertEquals(deal1.end(), peakWindow.end());
    }

    @Test
    public void noTimes() {
        var intervalHistogram = new IntervalHistogram();
        assertNull(intervalHistogram.getPeakTime());
    }

    @Test
    public void highestOverlapIsThePeak() {
        var intervalHistogram = new IntervalHistogram();

        intervalHistogram.addDealTime(new DealWindow(
                LocalTime.of(9, 0),
                LocalTime.of(15, 0)
        ));

        intervalHistogram.addDealTime(new DealWindow(
                LocalTime.of(10, 0),
                LocalTime.of(14, 0)
        ));

        intervalHistogram.addDealTime(new DealWindow(
                LocalTime.of(11, 0),
                LocalTime.of(12, 0)
        ));

        assertEquals(
                new DealWindow(LocalTime.of(11, 0), LocalTime.of(12, 0)),
                intervalHistogram.getPeakTime()
        );
    }
}
