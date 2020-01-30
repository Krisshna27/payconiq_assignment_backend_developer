package com.payconiq.stockmanagement.exception;

public class StockNotFoundAppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1913793167108938134L;

	public StockNotFoundAppException(Integer id) {
        super("Stock id not found : " + id);
    }
}
