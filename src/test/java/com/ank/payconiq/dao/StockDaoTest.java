package com.ank.payconiq.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ank.payconiq.dao.impl.StockDaoImpl;
import com.ank.payconiq.model.Stock;
import com.ank.payconiq.util.TestUtil;

@RunWith(SpringRunner.class)
public class StockDaoTest {

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public StockDao stockDao() {
			return new StockDaoImpl();
		}

		@Bean
		public List<Stock> stockList() {
			return new ArrayList<Stock>();
		}

		@Bean
		public AtomicLong stockIdentifier() {
			return new AtomicLong(0);
		}
	}

	@Autowired
	StockDao stockDao;

	@Before
	public void setup() {
		TestUtil.getStockList().forEach(s -> stockDao.getAllStocks().add(s));
	}
	
	@After
	public void clear() {
		stockDao.getAllStocks().clear();
	}

	@Test
	public void testGetAllStocks() {
		List<Stock> stockList = stockDao.getAllStocks();
		Assert.assertNotNull(stockList);
		Assert.assertTrue(stockList.size() == 4);
	}

	@Test
	public void testGetStockByIdPositive() { 
		Stock stock = stockDao.getStockById(1);
		Assert.assertNotNull(stock);
	}

	@Test
	public void testGetStockByIdNegative() {
		Stock stock = stockDao.getStockById(100);
		Assert.assertNull(stock);
	}

	@Test
	public void testCreateNewStock() {
		Stock stock = stockDao.createNewStock(TestUtil.getMockStock());
		Assert.assertNotNull(stock);
	}

	@Test
	public void testUpdateStockById() {
		Stock stock = stockDao.updateStockById(1, BigDecimal.valueOf(500.00));
		Assert.assertNotNull(stock);
		Assert.assertTrue(stock.getCurrentPrice().getAmount().compareTo(BigDecimal.valueOf(500.00)) == 0);
	}

}
