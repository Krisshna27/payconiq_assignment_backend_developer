package com.payconiq.stockmanagement.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.StockFileDTO;
import com.payconiq.stockmanagement.model.StockHistoryDTO;
import com.payconiq.stockmanagement.util.StockUtil;

@Configuration
public class StockManagementAppConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(StockManagementAppConfig.class);
	
	@Value("${stock.json.file}")
    String stockJson;
	
	Map<Integer, StockDTO> stocks = null;
	
	@Bean
	public StockUtil getStockUtil() {
		return new StockUtil();
	}
	
	/**
	 * Bean declaration to initialize historical stock tracking. 
	 * @return
	 */
	@Bean
	public Map<Integer, List<StockHistoryDTO>> getHistoricalInstance(){
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : getHistoricalInstance");
		
		Map<Integer, List<StockHistoryDTO>> stockHistoricals = new HashMap<>();		
		loadStockDetails().forEach((x,y)->stockHistoricals.put(x,initializeHistoricals(y.getId())));
		
		if(LOG.isDebugEnabled())
			LOG.debug("Exit : getHistoricalInstance");
		return  stockHistoricals;
	}
	
	private List<StockHistoryDTO> initializeHistoricals( Integer id ) {
		
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : initializeHistoricals");
		
		List<StockHistoryDTO> historicals = new ArrayList<>();
		historicals.add(StockHistoryDTO.builder()
				.id(id)
				.stockName(loadStockDetails().get(id).getStockName())
				.stockPrice(loadStockDetails().get(id).getCurrentPrice())
				.lastUpdated(loadStockDetails().get(id).getLastUpdate())
				.differenceFromPreviousValue(new BigDecimal(0.0))
				.build());
		
		if(LOG.isDebugEnabled())
			LOG.debug("Exit : initializeHistoricals");
		return historicals;
	}

	/**
	 * Bean declaration to load stock information on application context initialization
	 * @return
	 */
	@Bean
	@Lazy
	public Map<Integer, StockDTO> loadStockDetails(){
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : loadStockDetails");
		
		if(stocks == null) {
	      stocks = new TreeMap<>();
			List<StockFileDTO> rawStocks = null;
			 try {
				 rawStocks = new ObjectMapper().readValue(new ClassPathResource(stockJson).getFile(), new TypeReference<List<StockFileDTO>>() {});
		        } catch (IOException e) {
		        	LOG.error("Error while loading stock json file. Error Message {}", e.getMessage());
		        }
			 stocks = rawStocks.stream()
					 .map(x->constructStockObject(x))
					 .collect(Collectors.toMap(x->x.getId(), y->y));
		}
		
		if(LOG.isDebugEnabled())
			LOG.debug("Exit : loadStockDetails");
		
		return stocks;
	   }
	
	private StockDTO constructStockObject(StockFileDTO fileInformation) {
		
		return StockDTO.builder().stockName( fileInformation.getStockName() )
				.currentPrice( fileInformation.getCurrentPrice() )
				.lastUpdate( OffsetDateTime.now( ZoneOffset.UTC ) )
				.id( getStockUtil().getRandomNumberInts() )
				.build();
	}
	
	 @Bean
	public WebMvcConfigurer corsConfigurer() {
		 return new WebMvcConfigurer() {
			 @Override
			 public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
	
}
