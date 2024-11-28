package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.*;
import com.sparta.currency_user.exception.CustomException;
import com.sparta.currency_user.service.UserCurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final UserCurrencyService userCurrencyService;

    // 환전 요청
    @PostMapping("/{userId}")
    public ResponseEntity<ExchangeResponseDto> requestExchange(@PathVariable Long userId, @RequestBody @Valid ExchangeRequestDto dto) throws CustomException {

        return ResponseEntity.ok().body(userCurrencyService.requestExchange(userId, dto));
    }

    // 사용자 환전 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<FindExchangeResponseDto>> getExchangeById(@PathVariable Long userId) throws CustomException {

        return ResponseEntity.ok().body(userCurrencyService.findExchangeById(userId));
    }

    // 사용자 환전 취소
    @PatchMapping("/{userId}")
    public ResponseEntity<CancelledExchangeResponseDto> updateExchangeById(@PathVariable Long userId, @RequestParam Long exchangeId) throws CustomException {

        return ResponseEntity.ok().body(userCurrencyService.cancelExchangeById(userId, exchangeId));
    }

    // 각 사용자 별 환전 총합 조회
    @GetMapping("/total")
    public ResponseEntity<List<TotalExchangeResponseDto>> getExchangeTotal() {

        return ResponseEntity.ok().body(userCurrencyService.findTotalExchangeById());
    }
}
