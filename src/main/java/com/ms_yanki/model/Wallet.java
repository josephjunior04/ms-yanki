package com.ms_yanki.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "wallets")
@Data
public class Wallet {

    @Id
    private String id;
    private String phoneNumber;
    private String documentId;
    private String email;
    private String imei;
    private boolean cardAssociated;
}
