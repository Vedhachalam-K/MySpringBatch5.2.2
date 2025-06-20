package com.vedha.mybatchdemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableBatchProcessing(dataSourceRef = "batchDataSource", transactionManagerRef = "batchTransactionManager")
@ComponentScan({"com.vedha.mybatchdemo"})
public class MybatchdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatchdemoApplication.class, args);
	}

}
