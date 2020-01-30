package com.payconiq.stockmanagement.service.read;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payconiq.stockmanagement.exception.StockNotFoundAppException;
import com.payconiq.stockmanagement.model.StockDTO;

@Component
public class ListStockServiceImpl implements ListStocksService {
	
	/**
	 * Service implementation to fetch all the stocks 
	 * or to fetch a specific stock by id.
	 */
	
	@Autowired
	private Map<Integer, StockDTO> stocks;
	
	
	@Override
	public List<StockDTO> getStocks() {
		return stocks.values().stream().collect(Collectors.toList());
	}

	@Override
	public StockDTO getStocks(Integer id) {
		return stocks.values().stream()
				.filter(x->x.getId()==id)
				.findAny().orElseThrow(()-> new StockNotFoundAppException(id));
	}

	
}
