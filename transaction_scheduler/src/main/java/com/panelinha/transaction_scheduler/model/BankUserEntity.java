package com.panelinha.transaction_scheduler.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_BANK_USER", schema = "public")
public class BankUserEntity {
    @Id
    @SequenceGenerator(name = "seq_id", sequenceName = "seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    private Long id;

    @Column(name = "BALANCE")
    private Long balance;

    @CreationTimestamp
    private Date registrationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public BankUserEntity(Long balance) {
        this.balance = balance;
    }

    public BankUserEntity() {

    }

}
