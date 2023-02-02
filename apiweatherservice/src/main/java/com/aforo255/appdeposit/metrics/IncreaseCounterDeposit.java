package com.aforo255.appdeposit.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class IncreaseCounterDeposit {
	
	private final Counter depositGetCounter;

	public IncreaseCounterDeposit(MeterRegistry meterRegistry) {
		depositGetCounter=meterRegistry.counter("deposit.post");
	}
	
	public void increaseCounter() {
		depositGetCounter.increment();
	}
	
	
}
