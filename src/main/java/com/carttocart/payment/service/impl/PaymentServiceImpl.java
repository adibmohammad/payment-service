package com.carttocart.payment.service.impl;

import com.carttocart.payment.Exception.RecordNotFoundException;
import com.carttocart.payment.model.dto.*;
import com.carttocart.payment.model.entity.BankAccount;
import com.carttocart.payment.model.entity.BankTransaction;
import com.carttocart.payment.model.entity.ShortMessage;
import com.carttocart.payment.model.enums.BankTransactionStatusEnum;
import com.carttocart.payment.repository.BankAccountRepository;
import com.carttocart.payment.repository.BankTransactionRepository;
import com.carttocart.payment.repository.ShortMessageRepository;
import com.carttocart.payment.repository.UserRepository;
import com.carttocart.payment.service.api.PaymentProviderService1;
import com.carttocart.payment.service.api.PaymentProviderService2;
import com.carttocart.payment.service.api.PaymentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final BankTransactionRepository bankTransactionRepository;
    private final ShortMessageRepository shortMessageRepository;
    private final PaymentProviderService1 paymentProviderService1;
    private final PaymentProviderService2 paymentProviderService2;

    public PaymentServiceImpl(BankAccountRepository bankAccountRepository, UserRepository userRepository, BankTransactionRepository bankTransactionRepository, ShortMessageRepository shortMessageRepository, PaymentProviderService1 paymentProviderService1, PaymentProviderService2 paymentProviderService2) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
        this.bankTransactionRepository = bankTransactionRepository;
        this.shortMessageRepository = shortMessageRepository;
        this.paymentProviderService1 = paymentProviderService1;
        this.paymentProviderService2 = paymentProviderService2;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BankAccountResponseDto> getAllCard(int page, int size) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BankAccount> bankAccountList = bankAccountRepository.getCardList(user.getUsername(), PageRequest.of(page, size));
        if (bankAccountList == null || bankAccountList.isEmpty()) {
            throw new RecordNotFoundException(" record not found by username : " + user.getUsername());
        }
        return bankAccountList.stream().map(BankAccountResponseDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String addNewCard(BankAccountRequestDto request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(request.getAccountNumber());
        bankAccount.setCardNumber(request.getCardNumber());
        bankAccount.setCvv2(request.getCvv2());
        bankAccount.setPinCode(request.getPinCode());
        String expDate = request.getExpireDate();
        Date convertDate = null;
        try {
            convertDate = new SimpleDateFormat("yyyy-MM-dd").parse(expDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bankAccount.setUser(userRepository.getUserByUsername(user.getUsername()).get());
        bankAccount.setExpireDate(convertDate);
        bankAccount.setActiveFlag(true);
        bankAccountRepository.save(bankAccount);
        return "";
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean moneyTransfer(BankTransactionRequestDto request) {
        BankAccount bankAccount = bankAccountRepository.findById(request.getAccountId()).orElseThrow(
                () -> new RecordNotFoundException(" record not found by account id : " + request.getAccountId()));
        ResponseEntity<String> stringResponseEntity;
        if (bankAccount.getCardNumber().substring(0, 4).equals("6037")) {
            stringResponseEntity = paymentProviderService1.callPaymentProvider1(PaymentProviderRequestDto1.map(request, bankAccount.getCardNumber()));
        } else {
            stringResponseEntity = paymentProviderService2.callPaymentProvider2(PaymentProviderRequestDto2.map(request, bankAccount.getCardNumber()));
        }
        addTransactionLog(bankAccount, request, stringResponseEntity.getStatusCode());
        return stringResponseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public void addTransactionLog(BankAccount bankAccount, BankTransactionRequestDto request, HttpStatus httpStatus) {

        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setFromCard(bankAccount);
        bankTransaction.setAmount(request.getAmount());
        if (httpStatus.equals(HttpStatus.OK)) {
            bankTransaction.setStatus(BankTransactionStatusEnum.SUCCESSFUL.getStatus());
        } else {
            bankTransaction.setStatus(BankTransactionStatusEnum.UNSUCCESSFUL.getStatus());
        }
        bankTransaction.setToCardNumber(request.getDestinationCardNumber());
        bankTransactionRepository.save(bankTransaction);

        if (httpStatus.equals(HttpStatus.OK)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ShortMessage sms = new ShortMessage();
            sms.setCounter(0);
            sms.setSendingFlag(false);
            sms.setText("Your money is processed!");
            sms.setMobileNumber(userRepository.getUserByUsername(user.getUsername()).get().getMobileNumber());
            shortMessageRepository.save(sms);
        }
    }
}
