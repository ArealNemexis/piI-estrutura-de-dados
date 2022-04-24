package com.panelinha.pi_es.controller;

import com.panelinha.pi_es.dto.TransactionCreateRequestDTO;
import com.panelinha.pi_es.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("")
    public ResponseEntity createTransaction(@RequestBody TransactionCreateRequestDTO requestDTO) throws Exception {
        try{
            transactionService.createTransaction(requestDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
