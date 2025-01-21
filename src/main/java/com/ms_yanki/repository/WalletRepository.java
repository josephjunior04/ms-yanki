package com.ms_yanki.repository;

import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import com.ms_yanki.model.Wallet;

import io.reactivex.rxjava3.core.Single;

public interface WalletRepository extends RxJava3CrudRepository<Wallet, String> {
    Single<Wallet> findByPhoneNumber(String phoneNumber);
}
