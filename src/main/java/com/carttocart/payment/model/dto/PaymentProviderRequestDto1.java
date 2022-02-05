package com.carttocart.payment.model.dto;

public class PaymentProviderRequestDto1 {

    private String source;
    private String dest;
    private String cvv2;
    private String expDate;
    private String pin;
    private Long amount;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public static PaymentProviderRequestDto1 map(BankTransactionRequestDto request, String source){
        PaymentProviderRequestDto1 dto = new PaymentProviderRequestDto1();
        dto.setSource(source);
        dto.setDest(request.getDestinationCardNumber());
        dto.setAmount(request.getAmount());
        dto.setPin(request.getPin());
        dto.setCvv2(request.getCvv2());
        dto.setExpDate(request.getExpireDate());
        return dto;
    }
}
