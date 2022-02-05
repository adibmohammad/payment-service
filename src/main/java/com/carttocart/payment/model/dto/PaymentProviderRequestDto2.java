package com.carttocart.payment.model.dto;

public class PaymentProviderRequestDto2 {

    private String sourcePan;
    private String targetPan;
    private String cvv2;
    private String pin2;
    private String expire;
    private Long amount;

    public String getSourcePan() {
        return sourcePan;
    }

    public void setSourcePan(String sourcePan) {
        this.sourcePan = sourcePan;
    }

    public String getTargetPan() {
        return targetPan;
    }

    public void setTargetPan(String targetPan) {
        this.targetPan = targetPan;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public static PaymentProviderRequestDto2 map(BankTransactionRequestDto request, String source){

        PaymentProviderRequestDto2 dto = new PaymentProviderRequestDto2();
        dto.setSourcePan(source.replace("-",""));
        dto.setTargetPan(request.getDestinationCardNumber());
        dto.setAmount(request.getAmount());
        dto.setCvv2(request.getCvv2());
        dto.setPin2(request.getPin());
        dto.setExpire(new StringBuilder(request.getExpireDate()).insert(6,"/").toString());
        return dto;
    }
}
