package com.ewallet.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class KafkaProducer {
    private static final String TOPIC = "wallet_notifications";

    private static final Logger LOGGER = Logger.getLogger(KafkaProducer.class.getName());

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotification(Object message) {
        LOGGER.info("Message sent to Kafka : " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}
