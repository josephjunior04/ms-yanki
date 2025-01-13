package com.ms_yanki.service;

import com.ms_yanki.model.BalanceResponse;
import com.ms_yanki.model.CardAssociationRequest;
import com.ms_yanki.model.PaymentRequest;
import com.ms_yanki.model.PaymentResponse;
import com.ms_yanki.model.WalletRegistrationRequest;
import com.ms_yanki.model.WalletResponse;

import io.reactivex.rxjava3.core.Single;

public interface WalletService {
    Single<WalletResponse> associateCard(String phoneNumber, CardAssociationRequest request);

    Single<BalanceResponse> getWalletBalance(String phoneNumber);

    Single<WalletResponse> registerWallet(WalletRegistrationRequest request);

    Single<PaymentResponse> sendPayment(PaymentRequest request);
}
