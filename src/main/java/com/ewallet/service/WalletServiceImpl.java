package com.ewallet.service;

import com.ewallet.exception.InsufficientBalanceException;
import com.ewallet.exception.ResourceNotFoundException;
import com.ewallet.kafka.KafkaProducer;
import com.ewallet.mapper.MerchantMapper;
import com.ewallet.mapper.UserMapper;
import com.ewallet.mapper.WalletMapper;
import com.ewallet.model.Merchant;
import com.ewallet.model.Transaction;
import com.ewallet.model.User;
import com.ewallet.model.Wallet;
import com.ewallet.repository.MerchantRepository;
import com.ewallet.repository.TransactionRepository;
import com.ewallet.repository.UserRepository;
import com.ewallet.repository.WalletRepository;
import com.ewallet.request.PurchaseRequest;
import com.ewallet.response.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class WalletServiceImpl implements WalletService {

    Logger logger;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public PurchaseResponse initiatePurchase(PurchaseRequest request) {
        User user = UserMapper.mapDtoToUserEntity(request);
        Wallet wallet = WalletMapper.mapDtoToWalletEntity(request);
        Merchant merchant = MerchantMapper.mapDtoToMerchantEntity(request);
        userRepository.save(user);
        walletRepository.save(wallet);
        merchantRepository.save(merchant);

        user = userRepository.findById(request.getUser().getId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + request.getUser().getId()));
        wallet = walletRepository.findByUserId(user.getId());

        if(user.getAge() < 6 || user.getAge() > 17){
            throw new ResourceNotFoundException("Wallet do not exist for this age");
        }

        if (wallet == null) {
            throw new NoSuchElementException("Wallet not found for User ID: " + user.getId());
        }
        merchant = merchantRepository.findById(request.getMerchant().getId())
                .orElseThrow(() -> new NoSuchElementException("Merchant not found with ID: " + request.getMerchant().getId()));

        if (wallet.getBalance().compareTo(request.getPurchaseAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient wallet balance");
        }

        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setAmount(request.getPurchaseAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setWalletId(wallet.getId());
        transaction.setMerchantId(merchant.getId());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        wallet.setBalance(wallet.getBalance().subtract(request.getPurchaseAmount()));
        walletRepository.save(wallet);

        kafkaProducer.sendNotification(transaction);

        PurchaseResponse response = new PurchaseResponse();
        response.setTransactionId(transaction.getId());
        response.setAmount(transaction.getAmount());
        response.setCurrency(transaction.getCurrency());
        response.setTimestamp(transaction.getTimestamp());

        return response;
    }
}
