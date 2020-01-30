package com.payconiq.stockmanagement.util;

public interface Constants {

	String SWAGGER_GET_STOCKS_API_OPERATION_VALUE = "Get existing stocks";
	String SWAGGER_GET_STOCKS_API_OPERATION_NOTES = "Get list of all stocks in the system. Few of the stocks are preloaded on context initialization.";
	String SWAGGER_GET_STOCKS_API_RESPONSE_200 = "Stocks information fetched successfully";
	String SWAGGER_GET_STOCKS_API_RESPONSE_500 = "Server error while fetching the stock information";
	
	String SWAGGER_GET_STOCKS_BY_ID_API_OPERATION_VALUE = "Get existing stock by id";
	String SWAGGER_GET_STOCKS_BY_ID_API_OPERATION_NOTES = "Get a specific stock in the system by giving the id of the stock";
	String SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_200 = "Stock information fetched successfully for the given id";
	String SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_500 = "Server error while fetching the stock by id";
	String SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_400 = "Bad request. Cannot proceed with fetch";
	String SWAGGER_GET_STOCKS_BY_ID_API_RESPONSE_404 = "Not found stock information by the id";
	
	String SWAGGER_CREATE_STOCK_API_OPERATION_VALUE = "Create stock information";
	String SWAGGER_CREATE_STOCK_API_OPERATION_NOTES = "Create stock information.";
	String SWAGGER_CREATE_STOCK_API_RESPONSE_200 = "Stock created succussfully";
	String SWAGGER_CREATE_STOCK_API_RESPONSE_500 = "Internal error in creating the stock";
	String SWAGGER_CREATE_STOCK_API_RESPONSE_400 = "Bad request while creating the stock";
	
	String SWAGGER_UPDATE_STOCK_API_OPERATION_VALUE = "Update stock information";
	String SWAGGER_UPDATE_STOCK_API_OPERATION_NOTES = "Update stock information";
	String SWAGGER_UPDATE_STOCK_API_RESPONSE_200 = "Stock information updated successfully";
	String SWAGGER_UPDATE_STOCK_API_RESPONSE_500 = "Internal error in updating the stock information";
	String SWAGGER_UPDATE_STOCK_API_RESPONSE_400 = "Bad request while updating the stock information";
	String SWAGGER_UPDATE_STOCK_API_RESPONSE_404 = "Stock information not found for the given id";
	
	String SWAGGER_GET_STOCK_HISTORY_API_OPERATION_VALUE = "Get stock history";
	String SWAGGER_GET_STOCK_HISTORY_API_OPERATION_NOTES = "Get stock history by id";
	String SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_200 = "Stock history has been fetched successfully";
	String SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_500 = "Internal error in fetching stock history";
	String SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_400 = "Bad request in fetching stock history";
	String SWAGGER_GET_STOCK_HISTORY_API_RESPONSE_404 = "Stock history not found for the given id";
}