package com.sparta.currency_user.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ExchangeRequestDto {

    @Positive
    private Long amountInKrw;
    private Long toCurrencyId;
}
