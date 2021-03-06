package com.microservices.demo.twitter.to.kafka.service.listener;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaStatusListener extends StatusAdapter{
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitterKafkaStatusListener.class);
	
	@Override
	public void onStatus(Status status) {
		LOG.info("Twitter status with text {}" + status.getText());
	}
}
