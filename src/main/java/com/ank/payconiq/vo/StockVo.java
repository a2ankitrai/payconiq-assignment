package com.ank.payconiq.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StockVo {

	@JsonProperty("stock_id")
	private int id;
	
	@JsonProperty("stock_name")
	private String name;
	
	@JsonProperty("current_price")
	private String price;
	
	@JsonProperty("last_updated")
	private LocalDateTime lastUpdated;
	
}
