package com.panelinha.pi_es.controller;

import com.panelinha.pi_es.dto.BankUserCreateRequestDTO;
import com.panelinha.pi_es.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankUser")
public class BankUserController {

    @Autowired
    BankUserService bankUserService;

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody BankUserCreateRequestDTO requestDTO) {
        try {
            bankUserService.createBankUser(requestDTO);
            return new ResponseEntity(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public Long findUser(@PathVariable Long id){
        return bankUserService.getUser(id);
    }
}
