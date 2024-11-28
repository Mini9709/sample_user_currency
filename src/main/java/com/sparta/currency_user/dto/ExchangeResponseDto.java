package com.sparta.currency_user.dto;

import com.sparta.currency_user.enumclass.Status;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeResponseDto {

    private BigDecimal amountAfterExchange;
    private Status status;
    private String message;

    public ExchangeResponseDto(BigDecimal amountAfterExchange, Status status, String message) {
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.message = message;
    }
}
