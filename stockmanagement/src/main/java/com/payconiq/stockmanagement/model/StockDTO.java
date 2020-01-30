package com.payconiq.stockmanagement.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

	private Integer id;
	
	private String stockName;
	
	private BigDecimal currentPrice;
	
	private OffsetDateTime lastUpdate;
}
