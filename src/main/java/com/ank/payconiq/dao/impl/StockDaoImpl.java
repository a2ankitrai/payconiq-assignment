package com.ank.payconiq.dao.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ank.payconiq.dao.StockDao;
import com.ank.payconiq.model.Stock;

@Repository
public class StockDaoImpl implements StockDao {

	@Autowired
	List<Stock> stockList;

	@Autowired
	AtomicLong stockIdentifier;

	@Override
	public List<Stock> getAllStocks() {
		return stockList;
	}

	@Override
	public Stock createNewStock(Stock stock) {

		stock.setId(stockIdentifier.incrementAndGet());
		stockList.add(stock);

		return stock;
	}

	@Override
	public Stock getStockById(long id) {
		return stockList

				.stream()

				.filter(s -> s.getId() == id)

				.findFirst()

				.orElse(null);
	}

	public Stock updateStockById(long id, BigDecimal price) {

		return stockList

				.stream()

				.filter(s -> s.getId() == id)

				.findFirst()

				.map(s -> {
					s.setCurrentPrice(Money.of(CurrencyUnit.EUR, price));
					s.setLastUpdate(LocalDateTime.now());
					return s;
				})

				.orElse(null);
	}

}
