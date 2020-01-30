package com.payconiq.stockmanagement.service.update;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payconiq.stockmanagement.model.CreateStockRequestDTO;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.UpdateStockRequestDTO;
import com.payconiq.stockmanagement.service.create.CreateStockService;

@SpringBootTest
public class UpdateStockServiceImplTest {

	@Autowired
	UpdateStockService updateStockService;
	
	@Autowired
	CreateStockService createStockService;
	
	
	@Test
	public void testUpdateStock() {
		
		StockDTO createdStock = createStockService.createStock(CreateStockRequestDTO.builder().currentPrice(new BigDecimal("2.5")).stockName("Orange").build());
		assertEquals(new BigDecimal("1.5"), updateStockService.updateStock(UpdateStockRequestDTO.builder().currentPrice(new BigDecimal("1.5")).build(), createdStock.getId()).getCurrentPrice());
	}
	
}
