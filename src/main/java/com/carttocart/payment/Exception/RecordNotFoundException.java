package com.carttocart.payment.Exception;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
