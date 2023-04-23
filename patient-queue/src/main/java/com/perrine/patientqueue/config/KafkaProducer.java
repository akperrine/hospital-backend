package com.perrine.patientqueue.config;

import com.perrine.patientqueue.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, Patient> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Patient> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendData(Patient data) {
        logger.info(String.format("Message sent %s", data.toString()));

        Message<Patient> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "patients")
                .build();

        kafkaTemplate.send(message);
    }

}
