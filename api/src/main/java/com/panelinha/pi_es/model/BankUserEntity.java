package com.panelinha.pi_es.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_BANK_USER", schema = "public")
public class BankUserEntity {
    @Id
    @SequenceGenerator(name="seq_id", sequenceName="seq_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id")
    private Long id;

    @Column(name="BALANCE")
    private Long balance;

    @CreationTimestamp
    private Date registrationDate;


}
