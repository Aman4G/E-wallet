package com.ewallet.repository;

import com.ewallet.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {
    Wallet findByUserId(String userId);
}
