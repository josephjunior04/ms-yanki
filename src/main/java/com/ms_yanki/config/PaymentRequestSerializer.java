package com.ms_yanki.config;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms_yanki.model.PaymentRequest;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PaymentRequestSerializer implements Serializer<PaymentRequest> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, PaymentRequest data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            log.info("Error to serialize PaymentRequest: {}", e.getMessage());
            throw new RuntimeException("Error al serializar PaymentRequest", e);
        }
    }
}
