package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@Profile("dev")

//환율 검사 테스트
public class DataInitializer {

    @Autowired
    private CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        Currency currency1 = new Currency("테스트 통화1", BigDecimal.valueOf(-1), "$", BigDecimal.valueOf(10));
        Currency currency2 = new Currency("테스트 통화2", BigDecimal.valueOf(1000), "₩", BigDecimal.valueOf(1000));
        Currency currency3 = new Currency("테스트 통화3", BigDecimal.valueOf(900), "¥", BigDecimal.valueOf(1));

        currencyRepository.save(currency1);
        currencyRepository.save(currency2);
        currencyRepository.save(currency3);

        for (Currency currency : currencyRepository.findAll()) {
            if (currency.getExchangeRate().compareTo(BigDecimal.ZERO) < 0) {
                log.info("해당 통화의 환율이 유효하지 않습니다 : " + currency.getCurrencyName());
                currencyRepository.delete(currency);
            }
        }
    }
}
