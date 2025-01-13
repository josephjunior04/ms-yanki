package com.ms_yanki.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ms_yanki.model.BalanceResponse;
import com.ms_yanki.model.CardAssociationRequest;
import com.ms_yanki.model.PaymentRequest;
import com.ms_yanki.model.PaymentResponse;
import com.ms_yanki.model.Wallet;
import com.ms_yanki.model.WalletRegistrationRequest;
import com.ms_yanki.model.WalletResponse;
import com.ms_yanki.repository.WalletRepository;
import com.ms_yanki.service.WalletService;

import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final WalletRepository walletRepository;

    @Override
    public Single<WalletResponse> associateCard(String phoneNumber, CardAssociationRequest request) {
        return walletRepository.findByPhoneNumber(phoneNumber)
                .flatMap(wallet -> {
                    kafkaTemplate.send("check-card-balance", request.getCardNumber());

                    wallet.setCardAssociated(true);

                    return walletRepository.save(wallet)
                            .flatMap(savedWallet -> {
                                WalletResponse response = new WalletResponse();
                                response.setPhoneNumber(savedWallet.getPhoneNumber());
                                return Single.just(response);
                            });
                })
                .onErrorResumeNext(e -> Single.error(new RuntimeException("Wallet not found")));
    }

    @Override
    public Single<BalanceResponse> getWalletBalance(String phoneNumber) {
        return walletRepository.findByPhoneNumber(phoneNumber)
                .flatMap(wallet -> {
                    if (wallet.isCardAssociated()) {
                        kafkaTemplate.send("get-card-balance", wallet.getPhoneNumber());
                    }
                    BalanceResponse response = new BalanceResponse();
                    response.setPhoneNumber(phoneNumber);
                    return Single.just(response);
                })
                .onErrorResumeNext(e -> Single.error(new RuntimeException("Wallet not found")));
    }

    @Override
    public Single<WalletResponse> registerWallet(WalletRegistrationRequest request) {
        return Single.create(emitter -> {
            Wallet wallet = new Wallet();
            wallet.setPhoneNumber(request.getPhoneNumber());
            wallet.setDocumentId(request.getDocumentNumber())
            wallet.setEmail(request.getEmail());
            wallet.setImei(request.getImei());
            wallet.setCardAssociated(false);

            walletRepository.save(wallet)
                    .map(savedWallet -> {
                        WalletResponse response = new WalletResponse();
                        response.setPhoneNumber(savedWallet.getPhoneNumber());
                        return response;
                    })
                    .subscribe(emitter::onSuccess, emitter::onError);
        });
    }

    @Override
    public Single<PaymentResponse> sendPayment(PaymentRequest request) {
        return Single.create(emitter -> {
            kafkaTemplate.send("process-payment", request);

            PaymentResponse response = new PaymentResponse();
            response.setTransactionId("TX-" + System.currentTimeMillis());
            emitter.onSuccess(response);
        });
    }

}
