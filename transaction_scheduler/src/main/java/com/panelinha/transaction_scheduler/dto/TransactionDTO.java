package com.panelinha.transaction_scheduler.dto;

import java.io.Serializable;

public class TransactionDTO implements Serializable {
    private Long id;
    private Long senderId;
    private Long destinyId;
    private Long transactionValue;
    private Integer status;

    public TransactionDTO(Long id,Long senderId, Long destinyId, Long transactionValue, Integer status) {
        this.id = id;
        this.senderId = senderId;
        this.destinyId = destinyId;
        this.transactionValue = transactionValue;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getDestinyId() {
        return destinyId;
    }

    public void setDestinyId(Long destinyId) {
        this.destinyId = destinyId;
    }

    public Long getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Long transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
