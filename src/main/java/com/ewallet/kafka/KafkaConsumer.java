package com.ewallet.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = Logger.getLogger(KafkaConsumer.class.getName());

    @KafkaListener(topics = "wallet_notifications", groupId = "walletGroup")
    public void consume(Object message){
        LOGGER.info("Message received : " + message);
    }

}
