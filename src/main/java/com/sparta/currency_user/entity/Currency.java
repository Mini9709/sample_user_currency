package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private BigDecimal baseCurrency;
    private String symbol;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private List<UserCurrency> userCurrencies = new ArrayList<>();

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol, BigDecimal baseCurrency) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
        this.baseCurrency = baseCurrency;
    }

    public Currency() {}
}
