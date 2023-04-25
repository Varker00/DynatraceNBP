package com.nbp.apis;



import com.nbp.dto.GetMinMaxExchangeRateResponse;
import com.nbp.tables.TableA;
import com.nbp.service.MinMaxExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/minMax")
public class MinMaxExchangeRateController {

    @Autowired
    private MinMaxExchangeRateService minMaxExchangeRateService;

    // AKA Task 2

    @GetMapping("/{code}/{n}")
    public ResponseEntity getMinMaxExchangeRate(@PathVariable String code, @PathVariable int n) {

        if (n <= 0 || n > 255) return ResponseEntity.badRequest().body("The number of quotations should be between 1 and 255");

        Optional<TableA> avgExchangeRate = minMaxExchangeRateService.getExchangeRateData(code, n);

        return avgExchangeRate
                .map(avgValue -> ResponseEntity
                        .ok(GetMinMaxExchangeRateResponse
                                .entityToDtoMapper()
                                .apply(avgValue)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }

}
