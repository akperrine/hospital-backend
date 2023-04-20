package com.perrine.patientqueue.controllers;

import com.perrine.patientqueue.config.KafkaProducer;
import com.perrine.patientqueue.models.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class MessageController {
    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Patient patient) {
        kafkaProducer.sendMessage(patient);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
