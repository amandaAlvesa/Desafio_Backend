package com.simplificado.pic.infra;

import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simplificado.pic.dto.ExceptionDto;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CrontrollExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException expt) {
		ExceptionDto dto = new ExceptionDto("Usuario já cadastrado", "400");
		return ResponseEntity.badRequest().body(dto);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threat400(EntityNotFoundException excp) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity threatGeneralException(Exception exception) {
		ExceptionDto excDto = new ExceptionDto(exception.getMessage(), "500");
		return ResponseEntity.internalServerError().body(excDto);
	}
}
