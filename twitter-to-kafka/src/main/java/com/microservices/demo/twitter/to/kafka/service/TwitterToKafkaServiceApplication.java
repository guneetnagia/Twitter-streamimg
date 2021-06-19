package com.microservices.demo.twitter.to.kafka.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;

@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner{

	private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TwitterToKafkaServiceApplication.class, args);	
	}
	
	public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData configData) {
		this.twitterToKafkaServiceConfigData = configData;
	}
	

	@Override
	public void run(String... args) throws Exception {
		LOG.info("App started");
		LOG.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[] {})));
		LOG.info(twitterToKafkaServiceConfigData.getWelcomeMessage());
	}

}
