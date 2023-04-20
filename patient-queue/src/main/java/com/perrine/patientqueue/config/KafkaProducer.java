package com.perrine.patientqueue.config;

import com.perrine.patientqueue.models.Patient;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, Patient> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Patient> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Patient data) {
        logger.info(String.format("Message sent %s", data.toString()));

        Message<Patient> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "patients")
                .build();

        kafkaTemplate.send(message);
    }

//    public void sendMessage(String message) {
//        logger.info(String.format("Message sent %s", message));
//        kafkaTemplate.send("patients", message);
//    }
}
