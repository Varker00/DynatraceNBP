package com.nbp.apis;


import com.nbp.dto.GetAvgExchangeRateResponse;
import com.nbp.tables.TableA;
import com.nbp.service.AvgExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/avgRate")
public class AvgExchangeRateController {

    @Autowired
    private AvgExchangeRateService avgExchangeRateService;

    // AKA Task 1

    @GetMapping("/{code}/{date}")
    public ResponseEntity getAvgExchangeRate(@PathVariable String code, @PathVariable String date) {

        Optional<TableA> avgExchangeRate = avgExchangeRateService.getExchangeRateData(code, date);

        return avgExchangeRate
                .map(avgValue -> ResponseEntity
                        .ok(GetAvgExchangeRateResponse
                            .entityToDtoMapper()
                            .apply(avgValue)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }

}
