package com.simplificado.pic.dto;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderiD, Long receiverId ) {

}
