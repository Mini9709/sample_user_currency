package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.TotalExchangeResponseDto;
import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {

    // 쿼리를 이용한 환전 총합 반환
    @Query("select new com.sparta.currency_user.dto.TotalExchangeResponseDto(count(uc.amountInKrw), sum(uc.amountInKrw))" +
            "from UserCurrency uc where uc.status = com.sparta.currency_user.enumclass.Status.NORMAL group by uc.user.id")
    List<TotalExchangeResponseDto> findTotalExchangeById();
}
