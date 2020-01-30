package com.payconiq.stockmanagement.service.read.history;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.stockmanagement.exception.StockNotFoundAppException;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.StockHistoryDTO;

@Service
public class StockHistoricalsServiceImpl implements StockHistoricalsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(StockHistoricalsServiceImpl.class);
	
	/**
	 * Service implementation to track changes to the stock
	 * and to fetch history of changes to the specific stock by id
	 */
	
	@Autowired
	private Map<Integer, List<StockHistoryDTO>> historicals;

	@Override
	public List<StockHistoryDTO> getStockHistoricals(Integer stockId) {
		
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : getStockHistoricals, stockId : {}", stockId);
		
		if (historicals.containsKey(stockId))
			return historicals.get(stockId);
		else 
			throw new StockNotFoundAppException(stockId) ;
	}

	@Override
	public void updateStockHistory(StockDTO stockInfo) {
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : updateStockHistory");
		
			if(historicals.containsKey(stockInfo.getId())) {
				historicals.get(stockInfo.getId()).add(StockHistoryDTO.builder().id(stockInfo.getId())
				.stockName(stockInfo.getStockName())
				.stockPrice(stockInfo.getCurrentPrice())
				.lastUpdated(stockInfo.getLastUpdate())
				.differenceFromPreviousValue(stockInfo.getCurrentPrice().subtract((historicals.get(stockInfo.getId()).get(historicals.get(stockInfo.getId()).size()-1).getStockPrice())))
				.build());
			} else {
				historicals.put(stockInfo.getId(), new ArrayList<>(Arrays.asList(StockHistoryDTO.builder().id(stockInfo.getId())
				.stockName(stockInfo.getStockName())
				.stockPrice(stockInfo.getCurrentPrice())
				.lastUpdated(stockInfo.getLastUpdate())
				.differenceFromPreviousValue(new BigDecimal(0.0))
				.build())));
			}
			
			LOG.info("Stock history captured successfully");
			
			if(LOG.isDebugEnabled())
				LOG.debug("Exit : updateStockHistory");	
			
	}

}
