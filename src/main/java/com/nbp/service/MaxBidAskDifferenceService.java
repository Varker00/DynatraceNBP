package com.nbp.service;

import com.nbp.tables.TableC;
import com.nbp.tables.TableCDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaxBidAskDifferenceService {

    @Autowired
    RestTemplate restTemplate;

    public Optional<TableC> getBidAskData(String code, int n){
        try {
            String url = String.format("http://api.nbp.pl/api/exchangerates/rates/c/%s/last/%d", code, n);

            ResponseEntity<TableC> response = restTemplate.getForEntity(url, TableC.class);

            ArrayList<TableCDetails> rates = response.getBody().getRates();
            int maxIndex = 0;
            double maxDiff = rates.get(0).getAsk() - rates.get(0).getBid();

            for(int i = 1; i < rates.size(); i++){
                double diff = rates.get(i).getAsk() - rates.get(i).getBid();
                if (diff > maxDiff){
                    maxDiff = diff;
                    maxIndex = i;
                }
            }

            // Only leave record with the largest bid/ask difference in the response
            response.getBody().setRates(new ArrayList<>(
                    List.of(rates.get(maxIndex))
            ));

            return Optional.ofNullable(response.getBody());
        }
        catch (HttpClientErrorException e){
            return Optional.empty();
        }
    }

}
