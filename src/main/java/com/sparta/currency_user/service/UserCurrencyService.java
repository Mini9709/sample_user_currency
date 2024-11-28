package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.*;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.enumclass.ErrorCode;
import com.sparta.currency_user.enumclass.Status;
import com.sparta.currency_user.exception.CustomException;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final UserCurrencyRepository userCurrencyRepository;

    // 환전 요청 기능
    @Transactional
    public ExchangeResponseDto requestExchange(Long userId, ExchangeRequestDto dto) throws CustomException {

        if (userId == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.INVALID_PARAMETER_USER));
        Currency currency =  currencyRepository.findById(dto.getToCurrencyId()).orElseThrow(() -> new CustomException(ErrorCode.INVALID_PARAMETER_CURRENCY));

        BigDecimal amountAfterExchange = BigDecimal.valueOf(dto.getAmountInKrw()).divide(currency.getExchangeRate().multiply(currency.getBaseCurrency()), 2, BigDecimal.ROUND_HALF_UP);

        UserCurrency savedExchange = userCurrencyRepository.save(new UserCurrency(dto.getAmountInKrw(), amountAfterExchange, Status.NORMAL, user, currency));

        return new ExchangeResponseDto(savedExchange.getAmountAfterExchange(), Status.NORMAL, "환전 요청이 완료되었습니다.");
    }

    // 환전 조회 기능
    public List<FindExchangeResponseDto> findExchangeById(Long userId) throws CustomException {

        if (userId == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.INVALID_PARAMETER_USER)).getUserCurrencies().stream().map(FindExchangeResponseDto::toDto).toList();
    }

    // 환전 취소 기능
    @Transactional
    public CancelledExchangeResponseDto cancelExchangeById(Long userId, Long exchangeId) throws CustomException {

        if (userId == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        if (exchangeId == null) {
            throw new CustomException(ErrorCode.CURRENCY_NOT_FOUND);
        }

        // userId의 유효성 검사를 위한 구문
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.INVALID_PARAMETER_USER));

        UserCurrency userCurrency = userCurrencyRepository.findById(exchangeId).orElseThrow(() -> new CustomException(ErrorCode.INVALID_PARAMETER_CURRENCY));

        if (userId != userCurrency.getUser().getId()) {
            throw new CustomException(ErrorCode.FORBBIDEN_USER);
        }

        userCurrency.updateStatus(Status.CANCELLED);

        return new CancelledExchangeResponseDto(exchangeId, Status.CANCELLED, "환전 요청이 취소되었습니다.");
    }

    // 환전 총합 기능
    public List<TotalExchangeResponseDto> findTotalExchangeById() {

        return userCurrencyRepository.findTotalExchangeById();
    }
}
