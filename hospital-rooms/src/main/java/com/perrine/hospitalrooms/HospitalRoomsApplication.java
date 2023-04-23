package com.perrine.hospitalrooms;

import com.perrine.hospitalrooms.service.RoomDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HospitalRoomsApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public StringJsonMessageConverter jsonConverter() {
//		return new StringJsonMessageConverter();
//	}

	public static void main(String[] args) {
		SpringApplication.run(HospitalRoomsApplication.class, args);
	}

}
