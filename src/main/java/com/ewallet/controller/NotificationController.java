package com.ewallet.controller;

import com.ewallet.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kafka")
public class NotificationController {

    @Autowired
    private KafkaProducer kafkaProducer;

    public NotificationController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<Object> publish(@RequestParam("message") Object message) {
        kafkaProducer.sendNotification(message);
        return ResponseEntity.ok("Response sent to topic");
    }

}
