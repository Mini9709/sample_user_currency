package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class TotalExchangeResponseDto {
    private Long count;
    private Long total;

    public TotalExchangeResponseDto(Long count, Long total) {
        this.count = count;
        this.total = total;
    }
}
