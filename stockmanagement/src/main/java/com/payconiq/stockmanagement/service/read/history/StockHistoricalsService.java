package com.payconiq.stockmanagement.service.read.history;

import java.util.List;

import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.StockHistoryDTO;

public interface StockHistoricalsService {

	public List<StockHistoryDTO> getStockHistoricals(Integer stockId);
	
	public void updateStockHistory(StockDTO stockInfo);
}
