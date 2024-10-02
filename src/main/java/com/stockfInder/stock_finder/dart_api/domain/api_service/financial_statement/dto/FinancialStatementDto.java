package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FinancialStatementDto {
    @JsonProperty("reprt_code")
    private String reportCode;
    @JsonProperty("bsns_year")
    private Long businessYear;
    @JsonProperty("corp_code")
    private String corpCode;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("idx_cl_code")
    private String indicatorClassCode;
    @JsonProperty("idx_cl_nm")
    private String indicatorClassName;
    @JsonProperty("idx_code")
    private String indicatorCode;
    @JsonProperty("idx_nm")
    private String indicatorName;
    @JsonProperty("idx_val")
    private Double indicatorValue;

}
