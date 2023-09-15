package com.simplificado.pic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.simplificado.pic.service.TransactionService;
import com.simplificado.pic.transaction.Transaction;
import com.simplificado.pic.dto.TransactionDTO;


@RestController
@RequestMapping("/trasaction")
public class TransactionCrontroller {

	@Autowired
	private TransactionService service;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransactionion(@RequestBody TransactionDTO dto) throws Exception{
		Transaction newTransaction = this.service.createTransaction(dto);
		return new ResponseEntity<Transaction>(newTransaction, HttpStatus.OK);
	}
}
