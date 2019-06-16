package com.ank.payconiq.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.payconiq.dao.StockDao;
import com.ank.payconiq.exception.StockException;
import com.ank.payconiq.model.Stock;
import com.ank.payconiq.service.StockService;
import com.ank.payconiq.vo.StockVo;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockDao stockDao;

	@Override
	public List<StockVo> getAllStocks() {

		return stockDao.getAllStocks()

				.stream()

				.map(s -> transformStock(s))

				.collect(Collectors.toList());

	}

	@Override
	public StockVo getStockById(long id) {

		return Optional.ofNullable(stockDao.getStockById(id))

				.map(s -> transformStock(s))

				.orElseThrow(() -> new StockException("No Stock found by entered Id"));

	}

	@Override
	public StockVo updateStockById(long id, BigDecimal price) {

		return Optional.ofNullable(stockDao.updateStockById(id, price))

				.map(s -> transformStock(s))

				.orElseThrow(() -> new StockException("No Stock found by entered Id"));

	}

	@Override
	public StockVo createNewStock(StockVo stockVo) {

		if (stockNameExists(stockVo)) {
			throw new StockException("Stock Name already exists in the system.");
		}

		Stock stock = Stock.builder()

				.name(stockVo.getStockName())

				.currentPrice(Money.of(CurrencyUnit.EUR, new BigDecimal(stockVo.getPrice())))

				.lastUpdate(LocalDateTime.now())

				.build();

		stock = stockDao.addNewStock(stock);

		return transformStock(stock);
	}

	private boolean stockNameExists(StockVo stockVo) {

		return stockDao.getAllStocks()

				.stream()

				.anyMatch(s -> s.getName().equalsIgnoreCase(stockVo.getStockName()));
	}

	private StockVo transformStock(Stock s) {

		if (s == null)
			return null;
		StockVo sv = new StockVo();
		sv.setId(s.getId());
		sv.setStockName(s.getName());
		sv.setPrice(s.getCurrentPrice().toString());
		sv.setLastUpdated(s.getLastUpdate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

		return sv;

	}

}
