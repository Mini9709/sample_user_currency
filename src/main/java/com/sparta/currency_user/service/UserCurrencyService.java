package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.*;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enumclass.Status;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final UserCurrencyRepository userCurrencyRepository;

    @Transactional
    public ExchangeResponseDto requestExchange(Long userId, ExchangeRequestDto dto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Currency currency =  currencyRepository.findById(dto.getToCurrencyId()).orElseThrow(() -> new IllegalArgumentException("통화를 찾을 수 없습니다."));

        BigDecimal amountAfterExchange = BigDecimal.valueOf(dto.getAmountInKrw()).divide(currency.getExchangeRate(), 2, BigDecimal.ROUND_HALF_UP);

        UserCurrency savedExchange = userCurrencyRepository.save(new UserCurrency(dto.getAmountInKrw(), amountAfterExchange, Status.NORMAL, user, currency));

        return new ExchangeResponseDto(savedExchange.getAmountAfterExchange(), Status.NORMAL, "환전 요청이 완료되었습니다.");
    }

    public List<FindExchangeResponseDto> findExchangeById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")).getUserCurrencies().stream().map(FindExchangeResponseDto::toDto).toList();
    }

    @Transactional
    public CancelledExchangeResponseDto cancelExchangeById(Long userId, Long exchangeId) {
        UserCurrency userCurrency = userCurrencyRepository.findById(exchangeId).orElseThrow(() -> new IllegalArgumentException("해당 요청을 찾을 수 없습니다."));

        if (userId != userCurrency.getUser().getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 요청의 권리가 없습니다.");
        }

        userCurrency.updateStatus(Status.CANCELLED);

        return new CancelledExchangeResponseDto(exchangeId, Status.CANCELLED, "환전 요청이 취소되었습니다.");
    }
}
