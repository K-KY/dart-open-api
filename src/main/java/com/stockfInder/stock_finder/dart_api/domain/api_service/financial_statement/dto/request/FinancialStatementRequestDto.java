package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FinancialStatementRequestDto {
    private String corpCode;//기업 번호
    private String indicatorClassificationCode;//지표 분류 코드
    private int businessYear;//사업 연도
    private int reportCode;//분기 코드
}
