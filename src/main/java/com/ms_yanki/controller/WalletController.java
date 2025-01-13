package com.ms_yanki.controller;

import org.springframework.http.ResponseEntity;
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
public class WalletController implements WalletApi {

    private final WalletService walletService;

    @Override
    public Mono<ResponseEntity<WalletResponse>> associateCard(String phoneNumber,
            @Valid Mono<CardAssociationRequest> cardAssociationRequest, ServerWebExchange exchange) {
        return cardAssociationRequest
                .flatMap(request -> Mono.from(() -> walletService.associateCard(phoneNumber, request))
                .map(walletResponse -> ResponseEntity.ok(walletResponse)));
    }

    @Override
    public Mono<ResponseEntity<BalanceResponse>> getWalletBalance(String phoneNumber, ServerWebExchange exchange) {
        // TODO Auto-generated method stub
        return WalletApi.super.getWalletBalance(phoneNumber, exchange);
    }

    @Override
    public Mono<ResponseEntity<WalletResponse>> registerWallet(
            @Valid Mono<WalletRegistrationRequest> walletRegistrationRequest, ServerWebExchange exchange) {
        // TODO Auto-generated method stub
        return WalletApi.super.registerWallet(walletRegistrationRequest, exchange);
    }

    @Override
    public Mono<ResponseEntity<PaymentResponse>> sendPayment(@Valid Mono<PaymentRequest> paymentRequest,
            ServerWebExchange exchange) {
        // TODO Auto-generated method stub
        return WalletApi.super.sendPayment(paymentRequest, exchange);
    }

}
