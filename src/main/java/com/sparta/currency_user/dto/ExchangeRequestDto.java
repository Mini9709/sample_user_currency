package com.sparta.currency_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExchangeRequestDto {
    private Long amountInKrw;
    private Long toCurrencyId;
}
