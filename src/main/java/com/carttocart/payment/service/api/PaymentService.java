package com.carttocart.payment.service.api;

import com.carttocart.payment.model.dto.BankAccountRequestDto;
import com.carttocart.payment.model.dto.BankAccountResponseDto;
import com.carttocart.payment.model.dto.BankTransactionRequestDto;

import java.util.List;

public interface PaymentService {
    List<BankAccountResponseDto> getAllCard(int page, int size);
    String addNewCard(BankAccountRequestDto request);
    boolean moneyTransfer(BankTransactionRequestDto request);
}
