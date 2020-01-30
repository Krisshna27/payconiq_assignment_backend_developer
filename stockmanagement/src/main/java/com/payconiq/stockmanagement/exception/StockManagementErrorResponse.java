package com.payconiq.stockmanagement.exception;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockManagementErrorResponse {

	private OffsetDateTime timestamp;
	private Integer errorCode;
	private String error;
	private List<String> errorDescription;
}
