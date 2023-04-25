package com.nbp.service;

import com.nbp.tables.TableA;
import com.nbp.tables.TableADetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MinMaxExchangeRateService {

    @Autowired
    RestTemplate restTemplate;

    public Optional<TableA> getExchangeRateData(String code, int n){
        try {
            String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d", code, n);

            ResponseEntity<TableA> response = restTemplate.getForEntity(url, TableA.class);

            ArrayList<TableADetails> rates = response.getBody().getRates();
            int minIndex = 0, maxIndex = 0;
            double minVal = rates.get(0).getMid(), maxVal = rates.get(0).getMid();

            for(int i = 1; i < rates.size(); i++){
                double value = rates.get(i).getMid();
                if (value < minVal){
                    minVal = value;
                    minIndex = i;
                }
                else if (value > maxVal){
                    maxVal = value;
                    maxIndex = i;
                }
            }

            //Only leave the min and max value in the response
            response.getBody().setRates(new ArrayList<>(
                    List.of(rates.get(minIndex),
                            rates.get(maxIndex)
                    )
            ));

            return Optional.ofNullable(response.getBody());
        }
        catch (HttpClientErrorException e){
            return Optional.empty();
        }
    }

}
