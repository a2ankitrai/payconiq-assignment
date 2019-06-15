package com.ank.payconiq.model;

import java.time.LocalDateTime;

import org.joda.money.Money;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {

	private int id;
	private String name;
	private Money currentPrice;
	private LocalDateTime lastUpdate;	
}
