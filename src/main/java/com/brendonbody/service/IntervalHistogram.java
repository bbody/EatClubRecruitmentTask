package com.brendonbody.service;

import com.brendonbody.domain.DealWindow;

import java.time.LocalTime;

public class IntervalHistogram {
    private final int[] timeIntervals = new int[INTERVAL_COUNT];
    private static final int MINUTES_A_DAY = 60*24;
    private static final int MINUTES_PER_INTERVAL = 15;
    private static final int INTERVAL_COUNT = MINUTES_A_DAY/ MINUTES_PER_INTERVAL;

    public void addDealTime(DealWindow dealTime) {
        var start = getIndexFromTime(dealTime.start());
        var end = getIndexFromTime(dealTime.end());

        for (var i = start; i < end; i++) {
            timeIntervals[i]++;
        }
    }

    public DealWindow getPeakTime() {
        int highestCount = 0;
        int highestIndex = -1;

        for (var i = 0; i < timeIntervals.length; i++) {
            var intervalCount = timeIntervals[i];

            if (highestCount < intervalCount) {
                highestCount = intervalCount;
                highestIndex = i;
            }
        }

        if (highestIndex == -1) {
            return null;
        }

        var startIndex = highestIndex;
        var endIndex = startIndex + 1;
        while (endIndex < timeIntervals.length
                && timeIntervals[endIndex] == highestCount) {
            endIndex++;
        }

        var start = getTimeFromIndex(startIndex);
        var end = getTimeFromIndex(endIndex);

        return new DealWindow(start, end);
    }

    private LocalTime getTimeFromIndex(int idx) {
        return LocalTime.MIDNIGHT.plusMinutes(
                idx * MINUTES_PER_INTERVAL
        );
    }

    private int getIndexFromTime(LocalTime time) {
        return (time.getHour() * 60 + time.getMinute()) / MINUTES_PER_INTERVAL;
    }
}
