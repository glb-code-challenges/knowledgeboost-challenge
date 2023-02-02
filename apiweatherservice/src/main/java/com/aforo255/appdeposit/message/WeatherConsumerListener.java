package com.aforo255.appdeposit.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.aforo255.appdeposit.model.WeatherModel;
import com.aforo255.appdeposit.repository.IWeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class WeatherConsumerListener  implements AcknowledgingMessageListener<Integer, String> {

	private Logger log = LoggerFactory.getLogger(WeatherConsumerListener.class);  
	
	@Autowired
	private IWeatherRepository weatherRepository;
	
	@Override
	@KafkaListener(topics = "${spring.kafka.template.default-topic}")
	public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {
		  log.info("Consumer Receives weather record");
          log.info("ConsumerRecord : {}", data.value());          
          try {
        	ObjectMapper om = new ObjectMapper();
        	om.registerModule(new JavaTimeModule());
			WeatherModel model = om.readValue(data.value(), WeatherModel.class);
			model = weatherRepository.insert(model);
			
          } catch (JsonMappingException e) {
  			log.error("Error al mappear model request");
  			e.printStackTrace();
  		  } catch (JsonProcessingException e) {
  			log.error("Error al procesar json");
  			e.printStackTrace();
  		  }
		acknowledgment.acknowledge();          
	}
}
