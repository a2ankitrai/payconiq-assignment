package com.ank.payconiq.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.ank.payconiq.model.Stock;

public class TestUtil {

	public static List<Stock> getStockList() {
		Stock s1 = new Stock(1, "GOOG", Money.of(CurrencyUnit.EUR, 1011.23), LocalDateTime.now());
		Stock s2 = new Stock(2, "AMZN", Money.of(CurrencyUnit.EUR, 2000.53), LocalDateTime.now());
		Stock s3 = new Stock(3, "NTFX", Money.of(CurrencyUnit.EUR, 468.19), LocalDateTime.now());
		Stock s4 = new Stock(4, "PYPL", Money.of(CurrencyUnit.EUR, 113.58), LocalDateTime.now());

		List<Stock> stockList = new ArrayList<>();
		stockList.add(s1);
		stockList.add(s2);
		stockList.add(s3);
		stockList.add(s4);

		return stockList;
	}

	public static Stock getMockStock() {
		Stock stock = Stock.builder()

				.id(5)

				.name("ABC")

				.currentPrice(Money.of(CurrencyUnit.EUR, 120))

				.lastUpdate(LocalDateTime.now())

				.build();

		return stock;
	}
}
