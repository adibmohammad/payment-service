package com.carttocart.payment.service.api;

import com.carttocart.payment.model.dto.PaymentProviderRequestDto2;
import org.springframework.http.ResponseEntity;

public interface PaymentProviderService2 {
    ResponseEntity<String> callPaymentProvider2(PaymentProviderRequestDto2 dto);
}
