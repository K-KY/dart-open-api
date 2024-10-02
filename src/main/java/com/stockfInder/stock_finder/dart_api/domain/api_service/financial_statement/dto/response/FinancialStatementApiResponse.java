package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.FinancialStatementDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class FinancialStatementApiResponse {
    private String status;
    private String message;

    @JsonProperty("list")
    private List<FinancialStatementDto> data;
}
