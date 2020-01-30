package com.payconiq.stockmanagement.service.create;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payconiq.stockmanagement.model.CreateStockRequestDTO;

@SpringBootTest
public class CreateStockServiceImplTest {
	
	
	@Autowired
	CreateStockService createStockService;
	
	@Test
	public void testCreateStockForSuccess() {
		
		assertEquals(new BigDecimal("1.5"),
				createStockService.createStock(CreateStockRequestDTO.builder()
						.stockName("Rice").currentPrice(new BigDecimal("1.5")).build()).getCurrentPrice());
	}
	
	
	

	
	

}
