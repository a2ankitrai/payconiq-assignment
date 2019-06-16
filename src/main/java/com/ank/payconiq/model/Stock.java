package com.ank.payconiq.model;

import java.time.LocalDateTime;

import org.joda.money.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Stock {

	private long id;
	private String name;
	private Money currentPrice;
	private LocalDateTime lastUpdate;	
}
