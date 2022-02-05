package com.carttocart.payment.service.api;

import com.carttocart.payment.model.dto.PaymentProviderRequestDto1;
import org.springframework.http.ResponseEntity;

public interface PaymentProviderService1 {
    ResponseEntity<String> callPaymentProvider1(PaymentProviderRequestDto1 dto);
}
