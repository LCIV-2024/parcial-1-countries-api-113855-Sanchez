package ar.edu.utn.frc.tup.lciii.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryClient {

    @Autowired
    private RestTemplate restTemplate;



}
