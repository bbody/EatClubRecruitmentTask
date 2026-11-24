package com.brendonbody.controllers.responses;

import com.brendonbody.domain.DealWindow;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public record PeakTimesResponse(
        // To meet the assignment spec
        @JsonFormat(pattern = "h:mma", locale = "en")
        LocalTime peakTimeStart,
        // To meet the assignment spec
        @JsonFormat(pattern = "h:mma", locale = "en")
        LocalTime peakTimeEnd
) {
    public static PeakTimesResponse fromDomain(DealWindow peakTimes) {
        return new PeakTimesResponse(
                peakTimes.start(),
                peakTimes.end()
        );
    }
}
