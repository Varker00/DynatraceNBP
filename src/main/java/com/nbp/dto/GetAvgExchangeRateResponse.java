package com.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbp.tables.TableA;
import lombok.Builder;
import org.springframework.cglib.core.internal.Function;

@Builder
public class GetAvgExchangeRateResponse {

    @JsonProperty
    private double avgRate;

    public static Function<TableA, GetAvgExchangeRateResponse> entityToDtoMapper() {
        return AvgExchangeRateA -> GetAvgExchangeRateResponse.builder()
                .avgRate(AvgExchangeRateA.getRates().get(0).getMid())
                .build();
    }
}
