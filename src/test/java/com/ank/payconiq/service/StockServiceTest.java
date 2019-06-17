package com.ank.payconiq.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ank.payconiq.dao.StockDao;
import com.ank.payconiq.exception.StockException;
import com.ank.payconiq.model.Stock;
import com.ank.payconiq.service.impl.StockServiceImpl;
import com.ank.payconiq.util.TestUtil;
import com.ank.payconiq.vo.StockVo;

@RunWith(SpringRunner.class)
public class StockServiceTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public StockService stockService() {
			return new StockServiceImpl();
		}
	}

	@Autowired
	StockService stockService;

	@MockBean
	StockDao stockDao;

	@Before
	public void setup() {

		List<Stock> stockList = TestUtil.getStockList();

		Mockito.when(stockDao.getAllStocks()).thenReturn(stockList);
		Mockito.when(stockDao.getStockById(Mockito.anyLong())).thenReturn(stockList.get(0));
		Mockito.when(stockDao.createNewStock(Mockito.any())).thenReturn(TestUtil.getMockStock());
		Mockito.when(stockDao.updateStockById(Mockito.anyLong(), Mockito.any())).thenReturn(TestUtil.getMockStock());

	}

	@Test
	public void testGetAllStocks() {

		List<StockVo> stockVoList = stockService.getAllStocks();
		Assert.assertNotNull(stockVoList);
		Assert.assertTrue(stockVoList.size() == 4);
	}

	@Test
	public void testGetStockById() {

		StockVo stockVo = stockService.getStockById(1);
		Assert.assertNotNull(stockVo);

	}

	@Test
	public void testUpdateStockById() {
		StockVo stockVo = stockService.updateStockById(123, BigDecimal.TEN);
		Assert.assertNotNull(stockVo);
	}

	@Test
	public void testCreateNewStockSuccess() {

		StockVo stockVo = new StockVo();
		stockVo.setStockName("ABE");
		stockVo.setPrice("15.23");

		StockVo stockVoRes = stockService.createNewStock(stockVo);

		Assert.assertNotNull(stockVoRes);
	}

	@Test(expected = StockException.class)
	public void testCreateNewStockDuplicateFailure() {

		StockVo stockVo = new StockVo();
		stockVo.setStockName("GOOG");
		stockVo.setPrice("15.23");

		stockService.createNewStock(stockVo);

	}

}
