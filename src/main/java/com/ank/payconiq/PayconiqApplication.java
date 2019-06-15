package com.ank.payconiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.ank.payconiq"})
@SpringBootApplication
public class PayconiqApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayconiqApplication.class, args);
	}

}
