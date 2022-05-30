package com.panelinha.pi_es.repository;

import com.panelinha.pi_es.model.BankUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BankUserRepository extends JpaRepository<BankUserEntity, Long> {

//    @Override
//    Optional<BankUserEntity> findById(Long aLong);
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<BankUserEntity> findById(Long id);

}
