package com.aforo255.appdeposit.message;

import java.security.SecureRandom;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.aforo255.appdeposit.model.WeatherModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WeatherMessagePublish {


    @Value("${spring.kafka.template.default-topic}")
	private String topicName;    
    private Logger logger = LoggerFactory.getLogger(WeatherMessagePublish.class);
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;    
    
    public void sendWeatherEvent(WeatherModel weatherModel){
    	 SecureRandom secureRandom = new SecureRandom();
    	 int key = secureRandom.nextInt();     	
         String value = null;
         
         try {
			value = objectMapper.writeValueAsString(weatherModel);
		} catch (JsonProcessingException e) {
			logger.error("Error al pasar de objeto a json");
			value = null;
		}
         
         if(value != null) {
	         ProducerRecord<Integer, String> producerRecord = buildProducerRecord(key, value, topicName);
	         ListenableFuture<SendResult<Integer, String>> listenableFuture =kafkaTemplate.send(producerRecord);         
	         
	         listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
	
				@Override
				public void onSuccess(SendResult<Integer, String> result) {
					logger.info("Message Sent Successfully for the key :{} ", key);	              
				}
	
				@Override
				public void onFailure(Throwable ex) {
					logger.error("Error: send message and the error is {}", ex.getMessage());
				} 	        	 
	         });
         }                 
    }
    
    
    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader("weather-subscription-source", "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }
    
}