package com.ank.payconiq.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import com.ank.payconiq.util.StockUtil;
import com.ank.payconiq.vo.StockVo;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockDao stockDao;

	@Override
	public List<StockVo> getAllStocks() {

		return stockDao.getAllStocks()

				.stream()

				.map(s -> StockUtil.transformStock(s))

				.collect(Collectors.toList());

	}

	@Override
	public StockVo getStockById(long id) {

		return Optional.ofNullable(stockDao.getStockById(id))

				.map(s -> StockUtil.transformStock(s))

				.orElse(null);

	}

	@Override
	public StockVo updateStockById(long id, BigDecimal price) {

		return Optional.ofNullable(stockDao.updateStockById(id, price))

				.map(s -> StockUtil.transformStock(s))

				.orElse(null);

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

		stock = stockDao.createNewStock(stock);

		return StockUtil.transformStock(stock);
	}

	private boolean stockNameExists(StockVo stockVo) {

		return stockDao.getAllStocks()

				.stream()

				.anyMatch(s -> s.getName().equalsIgnoreCase(stockVo.getStockName()));
	}

}
