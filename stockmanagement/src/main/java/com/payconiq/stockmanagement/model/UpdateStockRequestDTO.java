package com.payconiq.stockmanagement.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockRequestDTO {
	
	@NotNull(message = "Stock price cannot be null")
	private BigDecimal currentPrice;
}
