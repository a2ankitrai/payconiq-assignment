package com.ank.payconiq.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ank.payconiq.validator.annotation.Alphabetic;
import com.ank.payconiq.validator.annotation.Price;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StockVo {

	@JsonProperty("stock_id")
	private long id;

	@NotNull
	@NotEmpty(message = "Stock Name cannot be empty")
	@Size(min = 3, max = 10, message = "Stock Name length should be between 3 and 10 characters")
	@Alphabetic
	@JsonProperty("stock_name")
	private String stockName;

	@NotNull
	@NotEmpty(message = "Stock Price cannot be empty")
	@JsonProperty("current_price")
	@Price
	private String price;

	@JsonProperty("last_updated")
	private String lastUpdated;

}
