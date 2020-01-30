package com.payconiq.stockmanagement.service.update;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.UpdateStockRequestDTO;
import com.payconiq.stockmanagement.service.read.history.StockHistoricalsService;

@Service
public class UpdateStockServiceImpl implements UpdateStockService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UpdateStockServiceImpl.class);
	
	/**
	 * Service implementation to update stock information based on id
	 */
	
	@Autowired
	private Map<Integer, StockDTO> stocks;
	
	@Autowired
	private StockHistoricalsService historyService;

	@Override
	public StockDTO updateStock(UpdateStockRequestDTO updateStockInfo, Integer stockId) {
		
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : updateStock");
		
		StockDTO existingStock = stocks.containsKey( stockId ) ? stocks.get( stockId ) : null ;
		if(existingStock != null) {
			existingStock.setCurrentPrice( updateStockInfo.getCurrentPrice() );
			existingStock.setLastUpdate( OffsetDateTime.now( ZoneOffset.UTC ) );
			historyService.updateStockHistory(existingStock);
		}
		
		LOG.info("Updated stock information successfully !!");
		
		if(LOG.isDebugEnabled())
			LOG.debug("Exit : updateStock");
		
		return existingStock != null ? stocks.get( stockId ):null;
	}

}
