package com.ms_yanki.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.ms_yanki.api.WalletApi;
import com.ms_yanki.model.BalanceResponse;
import com.ms_yanki.model.CardAssociationRequest;
import com.ms_yanki.model.PaymentRequest;
import com.ms_yanki.model.PaymentResponse;
import com.ms_yanki.model.WalletRegistrationRequest;
import com.ms_yanki.model.WalletResponse;
import com.ms_yanki.service.WalletService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

}
