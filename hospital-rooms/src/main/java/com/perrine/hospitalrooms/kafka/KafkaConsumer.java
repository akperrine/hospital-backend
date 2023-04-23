package com.perrine.hospitalrooms.kafka;

import com.perrine.hospitalrooms.models.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

//@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Bean
    public ErrorHandlingDeserializer<String> errorHandlingDeserializer() {
        JsonDeserializer<String> jsonDeserializer = new JsonDeserializer<>(String.class);

        return new ErrorHandlingDeserializer<>(jsonDeserializer);
    }

    @KafkaListener(topics = "patients", groupId = "myGroup")
    public void consume(Patient patient) {
        logger.info(String.format("Message: %s", patient.getEnteredOrExitMessage("entered")));
    }
}