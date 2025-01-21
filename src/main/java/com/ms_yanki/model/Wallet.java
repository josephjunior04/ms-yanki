package com.ms_yanki.model;

import java.math.BigDecimal;

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
    private Boolean cardAssociated;
    private String nroCardAssociated;
    private BigDecimal balance;
}
