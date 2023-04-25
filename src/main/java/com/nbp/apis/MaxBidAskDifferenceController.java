package com.nbp.apis;



import com.nbp.dto.GetMaxBidAskDifferenceResponse;
import com.nbp.service.MaxBidAskDifferenceService;
import com.nbp.tables.TableC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/maxDiff")
public class MaxBidAskDifferenceController {

    @Autowired
    private MaxBidAskDifferenceService maxBidAskDifferenceService;

    // AKA Task 3

    @GetMapping("/{code}/{n}")
    public ResponseEntity getMaxBidAskDiff(@PathVariable String code, @PathVariable int n) {

        if (n <= 0 || n > 255) return ResponseEntity.badRequest().body("The number of quotations should be between 1 and 255");

        Optional<TableC> avgExchangeRate = maxBidAskDifferenceService.getBidAskData(code, n);

        return avgExchangeRate
                .map(avgValue -> ResponseEntity
                        .ok(GetMaxBidAskDifferenceResponse
                                .entityToDtoMapper()
                                .apply(avgValue)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

    }

}
