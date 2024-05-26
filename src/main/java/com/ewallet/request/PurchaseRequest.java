package com.ewallet.request;

import com.ewallet.dto.MerchantDto;
import com.ewallet.dto.UserDto;
import com.ewallet.dto.WalletDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

    private UserDto user;

    private MerchantDto merchant;

    private WalletDto wallet;

    private BigDecimal purchaseAmount;

    private String currency;

}
