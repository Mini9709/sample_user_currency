package com.sparta.currency_user.dto;

import com.sparta.currency_user.enumclass.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CancelledExchangeResponseDto {

    private Long exchangeId;
    private Status status;
    private String message;
}
