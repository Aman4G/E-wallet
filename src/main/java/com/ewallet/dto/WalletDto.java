package com.ewallet.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class WalletDto {
    private String id;

    private String userId;

    private BigDecimal balance;
}
