package com.microservices.demo.twitter.to.kafka.service.runner;

import java.util.Arrays;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;

import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class TwitterKafkaStreamRunner implements StreamRunner{

	private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
	private final TwitterKafkaStatusListener twitterKafkaStatusListener;
	private TwitterStream twitterStream;
	private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);
	
	public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData configData,TwitterKafkaStatusListener listener) {
		this.twitterToKafkaServiceConfigData = configData;
		this.twitterKafkaStatusListener = listener;
	}
	@Override
	public void start() throws TwitterException {
		twitterStream = new TwitterStreamFactory().getInstance();
		twitterStream.addListener(twitterKafkaStatusListener);
		
		addFilter();
	}
	
	public void addFilter() {
		String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
		FilterQuery filterQuery = new FilterQuery(keywords);
		twitterStream.filter(filterQuery);
		LOG.info("starting filtering stream for keywords {}",Arrays.toString(keywords));
	}
	
	@PreDestroy
	public void shutdown() {
		if(twitterStream != null) {
			LOG.info("Closing twitter stream");
			twitterStream.shutdown();
		}
	}
}
