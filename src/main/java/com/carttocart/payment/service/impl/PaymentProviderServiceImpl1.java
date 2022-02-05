package com.carttocart.payment.service.impl;

import com.carttocart.payment.model.dto.PaymentProviderRequestDto1;
import com.carttocart.payment.service.api.PaymentProviderService1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class PaymentProviderServiceImpl1 implements PaymentProviderService1 {

    private final RestTemplate restTemplate;

    @Value("${webservice.payment.provider1.url}")
    private String provider1Url;

    public PaymentProviderServiceImpl1(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> callPaymentProvider1(PaymentProviderRequestDto1 dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PaymentProviderRequestDto1> entity = new HttpEntity(dto, headers);

        return restTemplate.exchange(provider1Url, HttpMethod.POST, entity, String.class);
    }
}
