package com.simplificado.pic.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.simplificado.pic.domain.UserType;
import com.simplificado.pic.dto.UserDto;
import com.simplificado.pic.repositories.UserRepository;
import com.simplificado.pic.user.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	
	//void->boolean
	public boolean validateTransactional(User sender, BigDecimal amount) throws Exception{
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Lojista não realiza transação");
		}
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente para tranferencia");
		}
		return true;
	}
	
	public User findUserById(Long id) throws Exception {
		return this.repository.findUserById(id).orElseThrow(() -> 
			new Exception("Usuario não encontrado"));	
	}

	public void saveUser(User user) {
		this.repository.save(user);
	}

	public User createUser(UserDto data) {
		User newUser = new User(data);
		this.saveUser(newUser);
		return newUser;
	}

	public List<User> getAllUser() {
		return this.repository.findAll();
	}
}
