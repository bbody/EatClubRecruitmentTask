package com.brendonbody.controllers;

import com.brendonbody.controllers.responses.DealResponse;
import com.brendonbody.service.DealsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/deals")
public class DealsController {
    private final DealsService dealsService;

    public DealsController(DealsService dealsService) {
        this.dealsService = dealsService;
    }

    @GetMapping
    public List<DealResponse> getDeals(
            @RequestParam(required = false)
            LocalTime timeOfDay
    ) {
        return this.dealsService.getDeals(timeOfDay).stream().map(DealResponse::fromDomain).toList();
    }
}
