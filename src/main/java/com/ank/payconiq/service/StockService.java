package com.ank.payconiq.service;

import java.math.BigDecimal;
import java.util.List;

import com.ank.payconiq.vo.StockVo;

public interface StockService {

	List<StockVo> getAllStocks();

	StockVo getStockById(int id);
	
	StockVo updateStockById(int id, BigDecimal price);
}
