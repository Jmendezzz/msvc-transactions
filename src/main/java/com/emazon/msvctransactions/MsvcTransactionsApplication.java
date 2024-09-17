package com.emazon.msvctransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcTransactionsApplication.class, args);
	}

}
