package com.nbp.service;

import com.nbp.tables.TableA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AvgExchangeRateService {

    @Autowired
    RestTemplate restTemplate;

    public Optional<TableA> getExchangeRateData(String code, String date){

        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s", code, date);

        try {
            ResponseEntity<TableA> response = restTemplate.getForEntity(url, TableA.class);
            return Optional.ofNullable(response.getBody());
        }
        catch (HttpClientErrorException e){
            return Optional.empty();
        }
    }

}
