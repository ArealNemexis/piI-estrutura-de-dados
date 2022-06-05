package com.panelinha.transaction_scheduler.dto;

public class TransactionCreateRequestDTO {
    private Long senderID;
    private Long destinyID;
    private Long transactionValue;


    public Long getSenderID() {
        return senderID;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getDestinyID() {
        return destinyID;
    }

    public void setDestinyID(Long destinyID) {
        this.destinyID = destinyID;
    }

    public Long getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Long transactionValue) {
        this.transactionValue = transactionValue;
    }
}
