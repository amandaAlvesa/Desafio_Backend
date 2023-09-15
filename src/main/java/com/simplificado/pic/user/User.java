package com.simplificado.pic.user;

import java.math.BigDecimal;

import com.simplificado.pic.domain.UserType;
import com.simplificado.pic.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName, lastName, password;
	private BigDecimal balance;
	
	@Column(unique = true)
	private String document;
	@Column(unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	public User (UserDto data) {
		this.email = data.email();
		this.balance = data.balance();
		this.userType = data.userType();
		this.lastName = data.lastName();
		this.document = data.document();
		this.password = data.password();
		this.firstName = data.firstName();
	}
}
