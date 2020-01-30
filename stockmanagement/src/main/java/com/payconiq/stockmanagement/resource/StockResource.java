package com.payconiq.stockmanagement.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.stockmanagement.model.CreateStockRequestDTO;
import com.payconiq.stockmanagement.model.StockDTO;
import com.payconiq.stockmanagement.model.StockHistoryDTO;
import com.payconiq.stockmanagement.model.UpdateStockRequestDTO;
import com.payconiq.stockmanagement.service.create.CreateStockService;
import com.payconiq.stockmanagement.service.read.ListStocksService;
import com.payconiq.stockmanagement.service.read.history.StockHistoricalsService;
import com.payconiq.stockmanagement.service.update.UpdateStockService;
import com.payconiq.stockmanagement.util.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class StockResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(StockResource.class);
	/**
	 * This class exposes REST API endpoints for stock management
	 */

	@Autowired
	ListStocksService listStockService;
	
	@Autowired
	CreateStockService createStockService;
	
	@Autowired
	UpdateStockService updateStockService;
	
	@Autowired
	StockHistoricalsService stockHistoryService;
	
	@GetMapping
	@ApiOperation(value = Constants.SWAGGER_GET_STOCKS_API_OPERATION_VALUE, notes = Constants.SWAGGER_GET_STOCKS_API_OPERATION_NOTES)
	@ApiResponses(value ={ 
			@ApiResponse(code=200,message=Constants.SWAGGER_GET_STOCKS_API_RESPONSE_200),
			@ApiResponse(code=500,message=Constants.SWAGGER_GET_STOCKS_API_RESPONSE_500)
		})
	public List<StockDTO> getStocks() {
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : getStocks");
		
		return listStockService.getStocks();	
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = Constants.SWAGGER_GET_STOCKS_BY_ID_API_OPERATION_VALUE, notes = Constants.SWAGGER_GET_STOCKS_BY_ID_API_OPERATION_NOTES)
	@ApiResponses(value ={ 
			@ApiResponse(code=200,message=Constants.SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_200),
			@ApiResponse(code=500,message=Constants.SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_500),
			@ApiResponse(code=404,message=Constants.SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_404),
			@ApiResponse(code=400,message=Constants.SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_400)
		})
	public StockDTO getStockById( @PathVariable( name= "id", required = true ) @NonNull Integer id) {
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : getStockById");
		return listStockService.getStocks(id);
	}
	
	@PostMapping
	@ApiOperation(value = Constants.SWAGGER_CREATE_STOCK_API_OPERATION_VALUE, notes = Constants.SWAGGER_CREATE_STOCK_API_OPERATION_NOTES)
	@ApiResponses(value ={ 
			@ApiResponse(code=200, message=Constants.SWAGGER_CREATE_STOCK_API_RESPONSE_200),
			@ApiResponse(code=500, message=Constants.SWAGGER_CREATE_STOCK_API_RESPONSE_500),
			@ApiResponse(code=400, message=Constants.SWAGGER_CREATE_STOCK_API_RESPONSE_400)
		})
	public StockDTO createStock( @Valid @RequestBody CreateStockRequestDTO newStockDTO ) {
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : createStock");
		return createStockService.createStock(newStockDTO);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = Constants.SWAGGER_UPDATE_STOCK_API_OPERATION_VALUE, notes = Constants.SWAGGER_UPDATE_STOCK_API_OPERATION_NOTES)
	@ApiResponses(value ={ 
			@ApiResponse(code=200, message=Constants.SWAGGER_UPDATE_STOCK_API_RESPONSE_200),
			@ApiResponse(code=500, message=Constants.SWAGGER_UPDATE_STOCK_API_RESPONSE_500),
			@ApiResponse(code=400, message=Constants.SWAGGER_UPDATE_STOCK_API_RESPONSE_400),
			@ApiResponse(code=404, message=Constants.SWAGGER_UPDATE_STOCK_API_RESPONSE_404)
		})
	public StockDTO updateStock(@PathVariable( name= "id", required = true ) Integer id, @Valid @RequestBody UpdateStockRequestDTO updateStockInfo ) {
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : updateStock");
		return updateStockService.updateStock(updateStockInfo, id);
	}
	
	@GetMapping(value = "/{id}/history")
	@ApiOperation(value = Constants.SWAGGER_GET_STOCK_HISTORY_API_OPERATION_VALUE, notes = Constants.SWAGGER_GET_STOCK_HISTORY_API_OPERATION_NOTES)
	@ApiResponses(value ={ 
			@ApiResponse(code=200, message=Constants.SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_200),
			@ApiResponse(code=500, message=Constants.SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_500),
			@ApiResponse(code=400, message=Constants.SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_400),
			@ApiResponse(code=404, message=Constants.SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_404)
		})
	public List<StockHistoryDTO> getHistory(@PathVariable( name= "id", required = true ) Integer id) {
		if(LOG.isDebugEnabled())
			LOG.debug("Entered : getHistory");
		return stockHistoryService.getStockHistoricals(id);
	}
	
}
