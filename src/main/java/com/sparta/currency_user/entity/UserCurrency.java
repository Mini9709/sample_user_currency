package com.sparta.currency_user.entity;

import com.sparta.currency_user.enumclass.Status;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "user_currency")
@Getter
public class UserCurrency extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amountInKrw;
    private BigDecimal amountAfterExchange;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_currency_id")
    private Currency currency;

    public UserCurrency(Long amountInKrw, BigDecimal amountAfterExchange, Status status, User user, Currency currency) {

        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.user = user;
        this.currency = currency;
    }

    public UserCurrency() {}

    public void updateStatus(Status status) {

        this.status = status;
    }
}
