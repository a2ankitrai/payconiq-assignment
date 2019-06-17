package com.ank.payconiq.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ank.payconiq.model.Stock;
import com.ank.payconiq.service.StockService;
import com.ank.payconiq.util.StockUtil;
import com.ank.payconiq.vo.StockVo;

@RunWith(SpringRunner.class)
public class StockControllerTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public StockController stockController() {
			return new StockController();
		}
	}

	@Autowired
	StockController stockController;

	@MockBean
	StockService stockService;

	@Before
	public void setup() {

		Stock s1 = new Stock(1, "GOOG", Money.of(CurrencyUnit.EUR, 1011.23), LocalDateTime.now());
		Stock s2 = new Stock(2, "AMZN", Money.of(CurrencyUnit.EUR, 2000.53), LocalDateTime.now());
		Stock s3 = new Stock(3, "NTFX", Money.of(CurrencyUnit.EUR, 468.19), LocalDateTime.now());
		Stock s4 = new Stock(4, "PYPL", Money.of(CurrencyUnit.EUR, 113.58), LocalDateTime.now());

		List<StockVo> stockList = new ArrayList<>();
		stockList.add(StockUtil.transformStock(s1));
		stockList.add(StockUtil.transformStock(s2));
		stockList.add(StockUtil.transformStock(s3));
		stockList.add(StockUtil.transformStock(s4));

		Mockito.when(stockService.getAllStocks()).thenReturn(stockList);
		Mockito.when(stockService.getStockById(Mockito.anyLong())).thenReturn(StockUtil.transformStock(s1));
		Mockito.when(stockService.createNewStock(Mockito.any())).thenReturn(getMockStock());
		Mockito.when(stockService.updateStockById(Mockito.anyLong(), Mockito.any())).thenReturn(getMockStock());

	}

	@Test
	public void testGetAllStocks() {
		ResponseEntity<List<StockVo>> responseEntity = null;
		responseEntity = stockController.getAllStocks();
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		Assert.assertTrue(responseEntity.getBody().size() == 4);

	}

	@Test
	public void testGetStockById() {

		ResponseEntity<StockVo> responseEntity = null;
		responseEntity = stockController.getStockById("1");
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		Assert.assertTrue(responseEntity.getBody().getId() == 1L);
		Assert.assertTrue(responseEntity.getBody().getStockName().equals("GOOG"));
	}

	@Test
	public void testCreateStock() {

		ResponseEntity<StockVo> responseEntity = null;
		responseEntity = stockController.createNewStock(getMockStock());
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		Assert.assertTrue(responseEntity.getBody() != null);

	}

	@Test
	public void testUpdateStockById() {
		ResponseEntity<StockVo> responseEntity = null;
		responseEntity = stockController.updateStockById("12", "429.03");
		Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		Assert.assertTrue(responseEntity.getBody() != null);
	}

	private StockVo getMockStock() {
		StockVo stockVo = new StockVo();
		stockVo.setStockName("ABC");
		stockVo.setPrice("23.65");
		return stockVo;
	}

}
