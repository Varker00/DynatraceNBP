package com.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbp.tables.TableC;
import lombok.Builder;
import org.springframework.cglib.core.internal.Function;

@Builder
public class GetMaxBidAskDifferenceResponse {

    @JsonProperty
    private double maxDifference;


    public static Function<TableC, GetMaxBidAskDifferenceResponse> entityToDtoMapper() {
        return MaxBidAskDifference -> GetMaxBidAskDifferenceResponse.builder()
                .maxDifference(
                        MaxBidAskDifference.getRates().get(0).getAsk() - MaxBidAskDifference.getRates().get(0).getBid())
                .build();
    }
}
