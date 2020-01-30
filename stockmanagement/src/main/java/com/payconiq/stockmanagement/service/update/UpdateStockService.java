package com.payconiq.stockmanagement.service.update;

import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.UpdateStockRequestDTO;

public interface UpdateStockService {

	public StockDTO updateStock(UpdateStockRequestDTO updateStockInfo, Integer stockId);
}
