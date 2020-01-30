package com.payconiq.stockmanagement.service.read;

import java.util.List;

import com.payconiq.stockmanagement.model.StockDTO;

public interface ListStocksService {

	public  StockDTO getStocks(Integer id);
	
	public List<StockDTO> getStocks();
}
