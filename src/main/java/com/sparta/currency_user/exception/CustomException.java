package com.sparta.currency_user.exception;

import com.sparta.currency_user.enumclass.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends Exception {
    private ErrorCode errorCode;
}
