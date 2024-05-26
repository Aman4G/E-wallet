package com.ewallet.controller;

import com.ewallet.request.PurchaseRequest;
import com.ewallet.response.PurchaseResponse;
import com.ewallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> initiatePurchase(@RequestBody PurchaseRequest request) {
        PurchaseResponse transaction = walletService.initiatePurchase(request);
        return ResponseEntity.ok(transaction);
    }

//    @PostMapping("/purchase")
//    public ResponseEntity<Transaction> initiatePurchase(@RequestBody PurchaseRequest request) {
//        Transaction transaction = walletService.initiatePurchase(request);
//        return ResponseEntity.ok(transaction);
//    }
}
