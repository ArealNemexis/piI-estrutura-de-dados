package com.panelinha.workertransaction.repository;

import com.panelinha.workertransaction.model.BankUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankUserRepository extends JpaRepository<BankUserEntity, Long> {

//    @Override
//    Optional<BankUserEntity> findById(Long aLong);
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<BankUserEntity> findById(Long id);

}
