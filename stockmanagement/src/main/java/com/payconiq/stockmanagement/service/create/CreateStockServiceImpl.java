package com.payconiq.stockmanagement.service.create;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.stockmanagement.model.CreateStockRequestDTO;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.service.read.ListStocksService;
import com.payconiq.stockmanagement.service.read.history.StockHistoricalsService;
import com.payconiq.stockmanagement.util.StockUtil;

@Service
public class CreateStockServiceImpl implements CreateStockService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CreateStockServiceImpl.class);
	
	/**
	 * Create stock information. 
	 * Id will be generated randomly from util.
	 */
	
	@Autowired
	private StockUtil util;
	
	@Autowired
	private Map<Integer, StockDTO> stocks;
	
	@Autowired
	private ListStocksService getStockService;
	
	@Autowired
	private StockHistoricalsService historyService;

	@Override
	public StockDTO createStock( CreateStockRequestDTO createStockInfo ) {
		
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : createStock");
		
		StockDTO newStock = StockDTO.builder()
				.stockName( createStockInfo.getStockName() )
				.currentPrice( createStockInfo.getCurrentPrice() )
				.lastUpdate( OffsetDateTime.now( ZoneOffset.UTC ) )
				.id( util.getRandomNumberInts() )
				.build();
		
		stocks.put(newStock.getId(), newStock);
		historyService.updateStockHistory(newStock);
		
		LOG.info("Stock information stored successfully !!");
		
		if(LOG.isDebugEnabled())
			LOG.debug("Exit : createStock");
		return getStockService.getStocks(newStock.getId());
	}

}
