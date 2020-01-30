package com.payconiq.stockmanagement.resource;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.stockmanagement.model.CreateStockRequestDTO;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.StockHistoryDTO;
import com.payconiq.stockmanagement.model.UpdateStockRequestDTO;
import com.payconiq.stockmanagement.service.create.CreateStockService;
import com.payconiq.stockmanagement.service.read.ListStocksService;
import com.payconiq.stockmanagement.service.read.history.StockHistoricalsService;
import com.payconiq.stockmanagement.service.update.UpdateStockService;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class StockResourceTest {

	
	@Mock	ListStocksService listStockService;
	
	@Mock	CreateStockService createStockService;
	
	@Mock	UpdateStockService updateStockService;
	
	@Mock	StockHistoricalsService stockHistoryService;
	
	

	@InjectMocks
	StockResource stockResource;
	
	private MockMvc mvc;

	@Before
	public void setUp() {
		 
		mvc = MockMvcBuilders.standaloneSetup(stockResource).build();
		
	}
	
	
	@Test
	public void getStocksTest() throws Exception {
		
	    List<StockDTO> stocks = new ArrayList<>();
	    StockDTO stock1 = StockDTO.builder().id(1).stockName("Rice").currentPrice(new BigDecimal("1.5")).lastUpdate(OffsetDateTime.now( ZoneOffset.UTC )).build();
	    StockDTO stock2 = StockDTO.builder().id(2).stockName("Milk").currentPrice(new BigDecimal("2.5")).lastUpdate(OffsetDateTime.now( ZoneOffset.UTC )).build();
	    stocks.add(stock1);
	    stocks.add(stock2);
		when(listStockService.getStocks()).thenReturn(stocks);
		mvc.perform(MockMvcRequestBuilders.get("/api/stocks")).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	

	
	@Test
	public void getStocksByIdTest() throws Exception {
		
	    StockDTO stock = StockDTO.builder()
	    		.id(1)
	    		.stockName("Rice")
	    		.currentPrice(new BigDecimal("1.5"))
	    		.lastUpdate(OffsetDateTime.now( ZoneOffset.UTC ))
	    		.build();
		when(listStockService.getStocks(1)).thenReturn(stock);
		mvc.perform(MockMvcRequestBuilders.get("/api/stocks/1")).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	
	@Test
	public void getStocksByIdForBadRequest() throws Exception {
		
	    StockDTO stock = StockDTO.builder()
	    		.id(1)
	    		.stockName("Rice")
	    		.currentPrice(new BigDecimal("1.5"))
	    		.lastUpdate(OffsetDateTime.now( ZoneOffset.UTC ))
	    		.build();
		when(listStockService.getStocks(1)).thenReturn(stock);
		mvc.perform(MockMvcRequestBuilders.get("/api/stocks/String")).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	
	
	@Test
	public void createStockTest() throws JsonProcessingException, Exception {
	    CreateStockRequestDTO stock = CreateStockRequestDTO.builder().stockName("Oats").currentPrice(new BigDecimal("0.90")).build();
	    StockDTO mockedstock = StockDTO.builder()
	    		.stockName("Oats")
	    		.id(1)
	    		.currentPrice(new BigDecimal("0.90"))
	    		.lastUpdate(OffsetDateTime.now( ZoneOffset.UTC ))
	    		.build();
		when(createStockService.createStock(stock)).thenReturn(mockedstock);		  
		
		mvc.perform(MockMvcRequestBuilders.post("/api/stocks")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(stock)))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	

	@Test
	public void updateStockTest() throws JsonProcessingException, Exception {
		
	    UpdateStockRequestDTO testStock = UpdateStockRequestDTO.builder().currentPrice(new BigDecimal("0.90")).build();
	    StockDTO mockedData = StockDTO.builder()
	    		.stockName("Oats")
	    		.id(1)
	    		.currentPrice(new BigDecimal("0.90"))
	    		.lastUpdate(OffsetDateTime.now( ZoneOffset.UTC ))
	    		.build();
		when(updateStockService.updateStock(testStock, 1)).thenReturn(mockedData);
		
		mvc.perform(MockMvcRequestBuilders.put("/api/stocks/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(testStock)))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@Test
	public void updateStockBadRequestTest() throws JsonProcessingException, Exception {
		
	    UpdateStockRequestDTO testStock = UpdateStockRequestDTO.builder().currentPrice(null).build();
	    StockDTO mockedData = StockDTO.builder()
	    		.stockName("Oats")
	    		.id(1)
	    		.currentPrice(new BigDecimal("0.90"))
	    		.lastUpdate(OffsetDateTime.now( ZoneOffset.UTC ))
	    		.build();
		when(updateStockService.updateStock(testStock, 1)).thenReturn(mockedData);
		
		mvc.perform(MockMvcRequestBuilders.put("/api/stocks/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(testStock)))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	public void getHistoryTest() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	    StockHistoryDTO stockHistory1 = StockHistoryDTO.builder().id(1).lastUpdated(OffsetDateTime.now( ZoneOffset.UTC )).stockName("Rice").stockPrice(new BigDecimal("1.5")).build();
	    StockHistoryDTO stockHistory2= StockHistoryDTO.builder().id(1).lastUpdated(OffsetDateTime.now( ZoneOffset.UTC )).stockName("Rice").stockPrice(new BigDecimal("2.5")).build();
	    StockHistoryDTO stockHistory3 = StockHistoryDTO.builder().id(1).lastUpdated(OffsetDateTime.now( ZoneOffset.UTC )).stockName("Rice").stockPrice(new BigDecimal("3.5")).build();
	    List<StockHistoryDTO>  history = new ArrayList<>();
	    history.add(stockHistory1);
	    history.add(stockHistory2);
	    history.add(stockHistory3);
		when(stockHistoryService.getStockHistoricals(1)).thenReturn(history);
		mvc.perform(MockMvcRequestBuilders.get("/api/stocks/1/history")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getHistoryBadRequestTest() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
	    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	    StockHistoryDTO stockHistory1 = StockHistoryDTO.builder().id(1).lastUpdated(OffsetDateTime.now( ZoneOffset.UTC )).stockName("Rice").stockPrice(new BigDecimal("1.5")).build();
	    StockHistoryDTO stockHistory2= StockHistoryDTO.builder().id(1).lastUpdated(OffsetDateTime.now( ZoneOffset.UTC )).stockName("Rice").stockPrice(new BigDecimal("2.5")).build();
	    StockHistoryDTO stockHistory3 = StockHistoryDTO.builder().id(1).lastUpdated(OffsetDateTime.now( ZoneOffset.UTC )).stockName("Rice").stockPrice(new BigDecimal("3.5")).build();
	    List<StockHistoryDTO>  history = new ArrayList<>();
	    history.add(stockHistory1);
	    history.add(stockHistory2);
	    history.add(stockHistory3);
		when(stockHistoryService.getStockHistoricals(1)).thenReturn(history);
		mvc.perform(MockMvcRequestBuilders.get("/api/stocks/String/history")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
