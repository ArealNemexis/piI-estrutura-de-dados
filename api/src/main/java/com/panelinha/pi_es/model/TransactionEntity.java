package com.panelinha.pi_es.model;

import com.panelinha.pi_es.constants.StatusEnum;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_TRANSACTION", schema = "public")
public class TransactionEntity {
    @Id
    @SequenceGenerator(name="seq_id", sequenceName="seq_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id")
    private Long id;

    private Long senderUserId;

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
}
