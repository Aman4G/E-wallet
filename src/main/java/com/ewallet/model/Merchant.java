package com.ewallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "merchants")
@Getter
@Setter
@AllArgsConstructor
public class Merchant {

//    @Id
    private String id;

    private String name;

    // Getters and Setters
}
