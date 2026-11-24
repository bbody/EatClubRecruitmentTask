package com.brendonbody.controllers;

import com.brendonbody.controllers.responses.PeakTimesResponse;
import com.brendonbody.service.PeakTimesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peak_times")
public class PeakTimesController {
    private final PeakTimesService peakTimesService;

    public PeakTimesController(PeakTimesService peakTimesService) {
        this.peakTimesService = peakTimesService;
    }

    @GetMapping
    public PeakTimesResponse getPeakTimes() {
        return PeakTimesResponse.fromDomain(peakTimesService.getPeakTimes());
    }
}
