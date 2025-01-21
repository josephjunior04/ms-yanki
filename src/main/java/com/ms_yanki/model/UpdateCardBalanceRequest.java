package com.ms_yanki.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateCardBalanceRequest {
    private String nroCard;
    private BigDecimal amount;
    private String type;
}
