package com.ewallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

//    @Id
    private String id;

    private String walletId;

    private String merchantId;

    private BigDecimal amount;

    private String currency;

    private LocalDateTime timestamp;

    // Getters and Setters
}
