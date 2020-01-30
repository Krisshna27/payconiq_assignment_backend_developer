package com.payconiq.stockmanagement.service.create;

import com.payconiq.stockmanagement.model.CreateStockRequestDTO;
import com.payconiq.stockmanagement.model.StockDTO;

public interface CreateStockService {

	public StockDTO createStock(CreateStockRequestDTO createStockInfo);
}
