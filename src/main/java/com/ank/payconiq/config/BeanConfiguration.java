package com.ank.payconiq.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ank.payconiq.model.Stock;

@Configuration
public class BeanConfiguration {

	@Bean(name = "stockList")
	public List<Stock> stockList() {

		// load from a file
		Stock s1 = new Stock(stockIdentifier().incrementAndGet(), "GOOG", Money.of(CurrencyUnit.EUR, 1011.23),
				LocalDateTime.now());
		Stock s2 = new Stock(stockIdentifier().incrementAndGet(), "AMZN", Money.of(CurrencyUnit.EUR, 2000.53),
				LocalDateTime.now());
		Stock s3 = new Stock(stockIdentifier().incrementAndGet(), "NTFX", Money.of(CurrencyUnit.EUR, 468.19),
				LocalDateTime.now());
		Stock s4 = new Stock(stockIdentifier().incrementAndGet(), "PYPL", Money.of(CurrencyUnit.EUR, 113.58),
				LocalDateTime.now());

		List<Stock> stockList = new ArrayList<>();

		stockList.add(s1);
		stockList.add(s2);
		stockList.add(s3);
		stockList.add(s4);

		return stockList;
	}

	@Bean(name = "stockIdentifier")
	public AtomicLong stockIdentifier() {
		return new AtomicLong(0);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
