package com.aforo255.appdeposit.utils;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class Messages {
	
	@Autowired
	private Environment env;
	
	public String get(Constants con, Object...params) {
		return MessageFormat.format(env.getProperty(con.getKey()),params);
	}
	
	public static enum Constants {
		SEARCH_SUCCESS_CODE("search.success.code"),
		SEARCH_SUCCESS_MESSAGE("search.success.message"),
		SEARCH_ERROR_CODE("search.error.code"),
		SEARCH_ERROR_MESSAGE("search.error.message"),
		OPERATION_SUCCESS("operation.success"),
		OPERATION_ERROR("operation.error"),
		RECORD_NOT_FOUND("record.not.found"),
		READ_RESPONSE_ERROR("read.response.error");
		
		private String key;
		
		public String getKey() {
			return key;
		}
		
		private Constants(String key) {
			this.key = key;
		}
				
	}
}
