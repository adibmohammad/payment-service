package com.carttocart.payment.controller;

import com.carttocart.payment.model.dto.BankAccountRequestDto;
import com.carttocart.payment.model.dto.BankTransactionRequestDto;
import com.carttocart.payment.service.api.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/" + PaymentController.PATH)
public class PaymentController {

    public static final String PATH = "payment";

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/cards")
    public ResponseEntity getCardList(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity(paymentService.getAllCard(page, size), HttpStatus.OK);
    }

    @PostMapping("/cards/add")
    public ResponseEntity addNewCard(@Valid @RequestBody BankAccountRequestDto request) {
        return new ResponseEntity(paymentService.addNewCard(request), HttpStatus.CREATED);
    }

    @PostMapping("/cards/transfer")
    public ResponseEntity moneyTransfer(@Valid @RequestBody BankTransactionRequestDto request){
        return new ResponseEntity(paymentService.moneyTransfer(request), HttpStatus.OK);
    }
}
