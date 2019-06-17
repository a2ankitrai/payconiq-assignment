package com.ank.payconiq.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ank.payconiq.model.Stock;

public interface StockDao {

	List<Stock> getAllStocks();

	Stock createNewStock(Stock stock);
	
	Stock getStockById(long id);
	
	Stock updateStockById(long id, BigDecimal price);
}
