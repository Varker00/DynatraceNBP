package com.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbp.tables.TableA;
import lombok.Builder;
import org.springframework.cglib.core.internal.Function;

@Builder
public class GetMinMaxExchangeRateResponse {

    @JsonProperty
    private double minAvgValue;
    @JsonProperty
    private double maxAvgValue;

    public static Function<TableA, GetMinMaxExchangeRateResponse> entityToDtoMapper() {
        return AvgExchangeRate -> GetMinMaxExchangeRateResponse.builder()
                .minAvgValue(AvgExchangeRate.getRates().get(0).getMid())
                .maxAvgValue(AvgExchangeRate.getRates().get(1).getMid())
                .build();
    }
}
