package com.panelinha.transaction_scheduler.repository;

import com.panelinha.transaction_scheduler.model.BankUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankUserRepository extends JpaRepository<BankUserEntity, Long> {

//    @Override
//    Optional<BankUserEntity> findById(Long aLong);
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<BankUserEntity> findById(Long id);

}
