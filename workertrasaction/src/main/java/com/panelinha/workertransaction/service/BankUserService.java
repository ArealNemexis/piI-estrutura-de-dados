package com.panelinha.workertransaction.service;

import com.panelinha.workertransaction.dto.BankUserCreateRequestDTO;
import com.panelinha.workertransaction.model.BankUserEntity;
import com.panelinha.workertransaction.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BankUserService {

    @Autowired
    BankUserRepository bankUserRepository;

    public void createBankUser(BankUserCreateRequestDTO userCreateDTO) throws Exception {

        if (userCreateDTO.getBalance() > 0) {

            BankUserEntity newUser = new BankUserEntity(userCreateDTO.getBalance());

            try {
                bankUserRepository.save(newUser);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Erro ao criar usuario");
            }
        }else {
            throw new Exception("balance negativo");
        }
    }

    public Long getUser(Long id){
        return Objects.requireNonNull(bankUserRepository.findById(id).orElse(null)).getBalance();
    }
}
