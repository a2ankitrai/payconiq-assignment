package com.ank.payconiq.service;

import java.math.BigDecimal;
import java.util.List;

import com.ank.payconiq.vo.StockVo;

public interface StockService {

	List<StockVo> getAllStocks();

	StockVo getStockById(long id);
	
	StockVo updateStockById(long id, BigDecimal price);
	
	StockVo createNewStock(StockVo stockVo);
}
