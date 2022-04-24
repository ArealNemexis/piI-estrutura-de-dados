package com.panelinha.pi_es.repository;

import com.panelinha.pi_es.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByStatus(int status);
}
