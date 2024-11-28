package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enumclass.Status;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class FindExchangeResponseDto {

    private Long amountInKrw;
    private BigDecimal amountAfterExchange;
    private Status status;

    private String name;
    private String currencyName;

    public FindExchangeResponseDto(String name, String currencyName, Long amountInKrw, BigDecimal amountAfterExchange, Status status) {
        this.name = name;
        this.currencyName = currencyName;
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public static FindExchangeResponseDto toDto(UserCurrency userCurrency) {
        return new FindExchangeResponseDto(
                userCurrency.getUser().getName(),
                userCurrency.getCurrency().getCurrencyName(),
                userCurrency.getAmountInKrw(),
                userCurrency.getAmountAfterExchange(),
                userCurrency.getStatus()
        );
    }
}
