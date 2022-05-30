package com.panelinha.pi_es.service;

import com.panelinha.pi_es.constants.StatusEnum;
import com.panelinha.pi_es.dto.TransactionCreateRequestDTO;
import com.panelinha.pi_es.dto.TransactionDTO;
import com.panelinha.pi_es.model.BankUserEntity;
import com.panelinha.pi_es.model.TransactionEntity;
import com.panelinha.pi_es.repository.BankUserRepository;
import com.panelinha.pi_es.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BankUserRepository bankUserRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public void createTransaction(TransactionCreateRequestDTO requestDTO) throws Exception {

        BankUserEntity userDestiny = bankUserRepository.findById(requestDTO.getDestinyID()).orElseThrow(() -> new Exception("Destiny not found"));
        BankUserEntity userSender = bankUserRepository.findById(requestDTO.getSenderID()).orElseThrow(() -> new Exception("Sender not found"));
//        BankUserEntity userSender = entityManager.find(BankUserEntity.class, requestDTO.getSenderID());
//        BankUserEntity userDestiny = entityManager.find(BankUserEntity.class, requestDTO.getDestinyID());

        TransactionEntity newTransaction = new TransactionEntity(userSender.getId(), userDestiny.getId(), requestDTO.getTransactionValue());

        transactionRepository.save(newTransaction);

    }

    public List<TransactionDTO> getPendingTransactions() {
        List<TransactionEntity> pendingRaws = transactionRepository.findByStatus(StatusEnum.PENDING.ordinal());


        System.out.println(pendingRaws.size());
        List<TransactionDTO> pendingDtos = new ArrayList<TransactionDTO>();

        pendingRaws.forEach(transactionEntity -> {
            TransactionDTO dto = new TransactionDTO(transactionEntity.getId(),
                    transactionEntity.getSenderUserId(),
                    transactionEntity.getDestinyUserId(),
                    transactionEntity.getTransactionValue(),
                    transactionEntity.getStatus());
            pendingDtos.add(dto);
        });


        return pendingDtos;
    }

    public List<TransactionDTO> getProcessingTransactions() {
        List<TransactionEntity> processingRaws = transactionRepository.findByStatus(StatusEnum.PROCESSING.ordinal());


        List<TransactionDTO> processingDtos = new ArrayList<TransactionDTO>();

        processingRaws.forEach(transactionEntity -> {
            TransactionDTO dto = new TransactionDTO(transactionEntity.getId(),
                    transactionEntity.getSenderUserId(),
                    transactionEntity.getDestinyUserId(),
                    transactionEntity.getTransactionValue(),
                    transactionEntity.getStatus());
            processingDtos.add(dto);
        });


        return processingDtos;
    }


    public void changeStatus(TransactionDTO dto, StatusEnum status) throws Exception {
        TransactionEntity entity = transactionRepository.findById(dto.getId()).orElseThrow(() -> new Exception("Transaction Not Found"));

        entity.setStatus(status.ordinal());

        transactionRepository.save(entity);
    }

    public void transaction(TransactionDTO dto) throws Exception {
        BankUserEntity userDestiny = bankUserRepository.findById(dto.getDestinyId()).orElseThrow(() -> new Exception("Destiny not found"));
        BankUserEntity userSender = bankUserRepository.findById(dto.getSenderId()).orElseThrow(() -> new Exception("Sender not found"));

        if (userSender.getBalance() >= dto.getTransactionValue()) {
            userDestiny.setBalance(userDestiny.getBalance() + dto.getTransactionValue());
            userSender.setBalance(userSender.getBalance() - dto.getTransactionValue());

            this.changeStatus(dto, StatusEnum.SUCCESS);

            bankUserRepository.save(userDestiny);
            bankUserRepository.save(userSender);
            return;
        }
        throw new Exception("Insufficient Funds");
    }

}
