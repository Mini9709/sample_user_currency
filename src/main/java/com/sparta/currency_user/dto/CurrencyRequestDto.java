package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotBlank
    private String currencyName;

    @Positive
    private BigDecimal exchangeRate;

    @Size(min = 1, max = 1, message = "올바른 기호를 입력해주세요.")
    @Pattern(regexp = "[₩$€¥£元₱₣]", message = "올바른 기호를 입력해주세요.")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
