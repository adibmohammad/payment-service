package com.carttocart.payment.service.impl;

import com.carttocart.payment.model.dto.PaymentProviderRequestDto2;
import com.carttocart.payment.service.api.PaymentProviderService2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class PaymentProviderServiceImpl2 implements PaymentProviderService2 {

    private final RestTemplate restTemplate;

    @Value("${webservice.payment.provider2.url}")
    private String provider2Url;

    public PaymentProviderServiceImpl2(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> callPaymentProvider2(PaymentProviderRequestDto2 dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PaymentProviderRequestDto2> entity = new HttpEntity(dto, headers);

        return restTemplate.exchange(provider2Url, HttpMethod.POST, entity, String.class);
    }
}
