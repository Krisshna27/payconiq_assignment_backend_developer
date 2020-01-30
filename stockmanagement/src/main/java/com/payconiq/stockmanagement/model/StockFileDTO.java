package com.payconiq.stockmanagement.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockFileDTO {

	private String stockName;
	
	private BigDecimal currentPrice;
}
