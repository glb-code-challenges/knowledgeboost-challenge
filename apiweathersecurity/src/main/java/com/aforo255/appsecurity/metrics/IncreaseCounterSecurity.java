package com.aforo255.appsecurity.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class IncreaseCounterSecurity {
	
	private final Counter securityGetCounter;

	public IncreaseCounterSecurity(MeterRegistry meterRegistry) {
		securityGetCounter=meterRegistry.counter("security.get");
	}
	
	public void increaseCounter() {
		securityGetCounter.increment();
	}
	
	
}
