package com.perrine.patientqueue.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic patientTopic() {
        return TopicBuilder.name("patients")
                .build();
    }

    @Bean
    public ErrorHandlingDeserializer<String> errorHandlingDeserializer() {
        JsonDeserializer<String> jsonDeserializer = new JsonDeserializer<>(String.class);

        return new ErrorHandlingDeserializer<>(jsonDeserializer);
    }
}
