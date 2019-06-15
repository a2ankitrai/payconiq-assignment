package com.ank.payconiq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ank.payconiq.dao.StockDao;
import com.ank.payconiq.model.Stock;

@Repository
public class StockDaoImpl implements StockDao {

	@Autowired
	List<Stock> stockList;

	@Override
	public List<Stock> getAllStocks() {
		return stockList;
	}

}
