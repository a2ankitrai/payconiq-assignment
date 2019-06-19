package com.ank.payconiq.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ank.payconiq.exception.StockException;
import com.ank.payconiq.service.StockService;
import com.ank.payconiq.validator.annotation.Numeric;
import com.ank.payconiq.validator.annotation.Price;
import com.ank.payconiq.vo.StockVo;

@CrossOrigin(origins = "*")
@RequestMapping("/api/stocks")
@RestController
@Validated
public class StockController {

	@Autowired
	StockService stockService;

	@GetMapping
	public ResponseEntity<List<StockVo>> getAllStocks() {

		List<StockVo> stockList = stockService.getAllStocks();

		return new ResponseEntity<List<StockVo>>(stockList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StockVo> getStockById(@PathVariable(value = "id") @Numeric String stockId) {

		StockVo stockVo = null;
		ResponseEntity<StockVo> response = null;

		stockVo = stockService.getStockById(Integer.valueOf(stockId));

		if (stockVo == null) {
			throw new StockException("No Stock found by entered Id", HttpStatus.NOT_FOUND);
		}
		response = new ResponseEntity<>(stockVo, HttpStatus.OK);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<StockVo> updateStockById(@PathVariable(value = "id") @Numeric String stockId,
			@RequestParam("updated_price") @Price @NotBlank String updatedPrice) {

		StockVo stockVo = null;
		ResponseEntity<StockVo> response = null;

		stockVo = stockService.updateStockById(Long.parseLong(stockId), new BigDecimal(updatedPrice));

		if (stockVo == null) {
			throw new StockException("No Stock found by entered Id", HttpStatus.NOT_FOUND);
		}
		response = new ResponseEntity<>(stockVo, HttpStatus.OK);
		return response;
	}

	@PostMapping
	public ResponseEntity<StockVo> createNewStock(@RequestBody @Valid StockVo stockVo) {

		StockVo stockVoResponse = stockService.createNewStock(stockVo);
		ResponseEntity<StockVo> response = null;

		HttpStatus status = stockVoResponse != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		response = new ResponseEntity<>(stockVoResponse, status);
		return response;
	}
}
