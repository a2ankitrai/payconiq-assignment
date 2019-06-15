package com.ank.payconiq.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ank.payconiq.service.StockService;
import com.ank.payconiq.vo.StockVo;

@RequestMapping("/api/stocks")
@RestController
public class StockController {

	@Autowired
	StockService stockService;

	@GetMapping
	public ResponseEntity<List<StockVo>> getAllStocks() {

		List<StockVo> stockList = stockService.getAllStocks();

		return new ResponseEntity<List<StockVo>>(stockList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StockVo> getStockById(@PathVariable(value = "id") int stockId) {

		StockVo stockVo = null;
		ResponseEntity<StockVo> response = null;

		stockVo = stockService.getStockById(stockId);

		HttpStatus status = stockVo != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		response = new ResponseEntity<>(stockVo, status);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<StockVo> updateStockById(@PathVariable(value = "id") int stockId,
			@RequestBody BigDecimal updatedPrice) {

		StockVo stockVo = null;
		ResponseEntity<StockVo> response = null;

		stockVo = stockService.updateStockById(stockId, updatedPrice);

		HttpStatus status = stockVo != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		response = new ResponseEntity<>(stockVo, status);
		return response;
	}

}
