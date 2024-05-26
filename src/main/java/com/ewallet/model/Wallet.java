package com.ewallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "wallets")
@Getter
@Setter
@AllArgsConstructor
public class Wallet {

//    @Id
    private String id;

    private String userId;

    private BigDecimal balance;

    // Getters and Setters
}
