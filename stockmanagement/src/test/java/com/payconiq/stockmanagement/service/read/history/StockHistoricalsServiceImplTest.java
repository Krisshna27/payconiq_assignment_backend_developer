package com.payconiq.stockmanagement.service.read.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payconiq.stockmanagement.model.CreateStockRequestDTO;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.UpdateStockRequestDTO;
import com.payconiq.stockmanagement.service.create.CreateStockService;
import com.payconiq.stockmanagement.service.update.UpdateStockService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class StockHistoricalsServiceImplTest {

	
	@Autowired
	StockHistoricalsService stockHistoricalsService;
	
	@Autowired
	UpdateStockService updateStockService;
	
	
	@Autowired
	CreateStockService createStockService;
	
	
	private Integer stockId;
	
	
	
	@BeforeAll
	public void initialize() throws Exception
	{
		
		StockDTO createdStock = createStockService.createStock(CreateStockRequestDTO.builder().currentPrice(new BigDecimal("2.5")).stockName("Orange").build());
		Thread.sleep(1000);
		updateStockService.updateStock(UpdateStockRequestDTO.builder().currentPrice(new BigDecimal("1.5")).build(), createdStock.getId());
		Thread.sleep(1000);
		updateStockService.updateStock(UpdateStockRequestDTO.builder().currentPrice(new BigDecimal("4.5")).build(), createdStock.getId());
		stockId = createdStock.getId();
	}
	
	
	
	@Test
	public void getStockHistoricalsTest() {
		
		stockHistoricalsService.getStockHistoricals(stockId);
		assertEquals(3, stockHistoricalsService.getStockHistoricals(stockId).size());
		
		
	}
}
