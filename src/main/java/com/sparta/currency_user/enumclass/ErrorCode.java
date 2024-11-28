package com.sparta.currency_user.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST 에러코드
    INVALID_PARAMETER_USER(HttpStatus.BAD_REQUEST, "ERR0001", "해당 사용자를 찾을 수 없습니다."),
    INVALID_PARAMETER_CURRENCY(HttpStatus.BAD_REQUEST,"ERR0002", "해당 통화를 찾을 수 없습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "ERR0003", "잘못된 입력값입니다. "),

    //403 FORBIDDEN 에러코드
    FORBBIDEN_USER(HttpStatus.FORBIDDEN, "ERR3001", "접근이 금지된 사용자입니다."),

    //404 NOT_FOUND 에러코드
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR4001", "사용자 id를 입력해주세요."),
    CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR5001", "통화 id를 입력해주세요."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERR0000", "서버 에러 발생");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
