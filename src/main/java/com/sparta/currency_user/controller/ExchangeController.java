package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.CancelledExchangeResponseDto;
import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.dto.FindExchangeResponseDto;
import com.sparta.currency_user.service.UserCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final UserCurrencyService userCurrencyService;

    @PostMapping("/{userId}")
    public ResponseEntity<ExchangeResponseDto> requestExchange(@PathVariable Long userId, @RequestBody ExchangeRequestDto dto) {
        return ResponseEntity.ok().body(userCurrencyService.requestExchange(userId, dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FindExchangeResponseDto>> getExchangeById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userCurrencyService.findExchangeById(userId));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<CancelledExchangeResponseDto> updateExchangeById(@PathVariable Long userId, @RequestParam Long exchangeId) {
        return ResponseEntity.ok().body(userCurrencyService.cancelExchangeById(userId, exchangeId));
    }
}
