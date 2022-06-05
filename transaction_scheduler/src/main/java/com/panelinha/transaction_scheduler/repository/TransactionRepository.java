package com.panelinha.transaction_scheduler.repository;

import com.panelinha.transaction_scheduler.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByStatus(int status);
}
