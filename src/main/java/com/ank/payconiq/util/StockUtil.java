package com.ank.payconiq.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.ank.payconiq.model.Stock;
import com.ank.payconiq.vo.StockVo;

public class StockUtil {

	/**
	 * Utility method to transform stock model to stock value object
	 * */
	public static StockVo transformStock(Stock s) {

		if (s == null)
			return null;
		StockVo sv = new StockVo();
		sv.setId(s.getId());
		sv.setStockName(s.getName());
		sv.setPrice(s.getCurrentPrice().toString());
		sv.setLastUpdated(s.getLastUpdate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

		return sv;

	}
}
