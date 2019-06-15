package com.ank.payconiq.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ank.payconiq.dao.StockDao;
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
	public StockVo getStockById(int id) {

		return stockDao.getAllStocks()

				.stream()

				.filter(s -> s.getId() == id)

				.findFirst()

				.map(s -> transformStock(s))

				.orElse(null);
	}

	@Override
	public StockVo updateStockById(int id, BigDecimal price) {

		return stockDao.getAllStocks()

				.stream()

				.filter(s -> s.getId() == id)
				
				.findFirst()

				.map(s -> {
					s.setCurrentPrice(Money.of(CurrencyUnit.EUR, price));
					return s;
				})

				.map(s -> transformStock(s))
				
				.orElse(null);
	

	}

	public StockVo transformStock(Stock s) {

		if (s == null)
			return null;
		StockVo sv = new StockVo();
		sv.setId(s.getId());
		sv.setName(s.getName());
		sv.setPrice(s.getCurrentPrice().toString());
		sv.setLastUpdated(s.getLastUpdate());

		return sv;

	}

}
