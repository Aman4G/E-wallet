package com.ewallet.mapper;

import com.ewallet.dto.MerchantDto;
import com.ewallet.model.Merchant;
import com.ewallet.request.PurchaseRequest;

public class MerchantMapper {

    public static Merchant mapDtoToMerchantEntity(PurchaseRequest request) {
        MerchantDto merchantDto = request.getMerchant();
        return new Merchant(merchantDto.getId(), merchantDto.getName());
    }
}
