package com.simplificado.picpay.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transaction, Long>{

}
