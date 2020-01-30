package com.payconiq.stockmanagement.service.read;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payconiq.stockmanagement.model.StockDTO;

@SpringBootTest
public class ListStockServiceImplTest {
	
	
	@Autowired
	ListStocksService listStocksService;
	
	private static int MAX_COUNT_FROM_FILE=3;
	
	
	@Test
	public void testgetAllStocks() {
		assertEquals(MAX_COUNT_FROM_FILE,listStocksService.getStocks().size());
	}
	
	
	@Test
	public void testgetStocksById() {
		List<StockDTO> stocks= listStocksService.getStocks();
		for(StockDTO stock:stocks) {
			assertEquals(stock,listStocksService.getStocks(stock.getId()));

		}
	}
	

}
