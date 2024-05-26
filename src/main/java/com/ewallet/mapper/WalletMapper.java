package com.ewallet.mapper;

import com.ewallet.dto.WalletDto;
import com.ewallet.model.Wallet;
import com.ewallet.request.PurchaseRequest;

public class WalletMapper {

    public static Wallet mapDtoToWalletEntity(PurchaseRequest request){
        WalletDto walletDto = request.getWallet();
        return  new Wallet(walletDto.getId(), walletDto.getUserId(), walletDto.getBalance());
    }
}
