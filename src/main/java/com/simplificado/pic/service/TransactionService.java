package com.simplificado.pic.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simplificado.pic.dto.TransactionDTO;
import com.simplificado.pic.transaction.Transaction;
import com.simplificado.pic.transaction.TransactionRepository;
import com.simplificado.pic.user.User;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NotificationService notificationService;

//	public boolean authorizeTransaction(User sender, BigDecimal value) {
//		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",Map.class);
//		if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
//			String message = (String) authorizationResponse.getBody().get("message");
//			return "Autorizado".equalsIgnoreCase(message);
//		} else {
//			return false;
//		}
//	}

	public Transaction createTransaction(TransactionDTO dto) throws Exception {
		User sender = this.userService.findUserById(dto.senderiD());
		User receiver = this.userService.findUserById(dto.receiverId());

		boolean isAuthorized = userService.validateTransactional(sender, dto.value());

//		boolean isAuthorized = this.authorizeTransaction(sender, dto.value());
//
		if (!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}
		

		Transaction transaction = new Transaction();
		transaction.setAmount(transaction.getAmount());
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setTimeStamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(dto.value()));
		receiver.setBalance(receiver.getBalance().add(dto.value()));
		
		this.repository.save(transaction);
		this.userService.saveUser(receiver);
		this.userService.saveUser(sender);
		

		this.notificationService.sendNotification(receiver, "Transação Recebida");
		this.notificationService.sendNotification(sender, "Transação Enviada");
		
		return transaction;
	}

}
