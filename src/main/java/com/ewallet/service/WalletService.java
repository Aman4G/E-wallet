package com.ewallet.service;

import com.ewallet.request.PurchaseRequest;
import com.ewallet.response.PurchaseResponse;

public interface WalletService {
    PurchaseResponse initiatePurchase(PurchaseRequest request);
}
