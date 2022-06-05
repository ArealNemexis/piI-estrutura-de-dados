package com.panelinha.workertransaction.model;

import com.panelinha.workertransaction.constants.StatusEnum;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_TRANSACTION", schema = "public")
public class TransactionEntity {
    @Id
    @SequenceGenerator(name="seq_trans_id", sequenceName="seq_trans_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_trans_id")
    private Long id;

    @Column(name="SENDER_USER_ID")
    private Long senderUserId;
    @Column(name="DESTINY_USER_ID")
    private Long destinyUserId;

    @Column(name="TRANSACTION_VALUE")
    private Long transactionValue;


    @Column(name = "STATUS")
    private int status = StatusEnum.PENDING.ordinal();

    @CreationTimestamp
    private Date registrationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Long getDestinyUserId() {
        return destinyUserId;
    }

    public void setDestinyUserId(Long destinyUserId) {
        this.destinyUserId = destinyUserId;
    }

    public Long getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Long transactionValue) {
        this.transactionValue = transactionValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getFinishedUpdateDate() {
        return finishedUpdateDate;
    }

    public void setFinishedUpdateDate(Date finishedUpdateDate) {
        this.finishedUpdateDate = finishedUpdateDate;
    }

    public TransactionEntity() {

    }


    public TransactionEntity(Long senderUserId, Long destinyUserId, Long transactionValue) {
        this.senderUserId = senderUserId;
        this.destinyUserId = destinyUserId;
        this.transactionValue = transactionValue;
    }

}
