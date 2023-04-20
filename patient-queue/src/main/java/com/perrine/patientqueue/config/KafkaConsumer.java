package com.perrine.patientqueue.config;

import com.perrine.patientqueue.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
//public class KafkaConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(topics = "patients", groupId = "myGroup")
//    public void consume(Patient patient) {
//        logger.info(String.format("Message: %s", patient.getEnterMessage()));
//    }
//}
