package com.panelinha.workertransaction.repository;

import com.panelinha.workertransaction.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByStatus(int status);
}
