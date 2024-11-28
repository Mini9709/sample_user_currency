package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyResponseDto {
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private BigDecimal baseCurrency;
    private String symbol;

    public CurrencyResponseDto(Currency currency) {
        this.id = currency.getId();
        this.currencyName = currency.getCurrencyName();
        this.exchangeRate = currency.getExchangeRate();
        this.symbol = currency.getSymbol();
        this.baseCurrency = currency.getBaseCurrency();
    }

    public CurrencyResponseDto(Long id, String currencyName, BigDecimal exchangeRate, String symbol, BigDecimal baseCurrency) {
        this.id = id;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
        this.baseCurrency = baseCurrency;
    }

    public static CurrencyResponseDto toDto(Currency currency) {
        return new CurrencyResponseDto(
            currency.getId(),
            currency.getCurrencyName(),
            currency.getExchangeRate(),
            currency.getSymbol(),
            currency.getBaseCurrency()
        );
    }
}
