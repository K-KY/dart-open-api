package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class FinancialIndicatorRequestDto {
    private String indicatorClassificationCode;//지표 분류 코드 수익성지표 : M210000 안정성지표 : M220000 성장성지표 : M230000 활동성지표 : M240000
    private String indicatorClassificationName;//지표 분류 이름
    private String indexCode;//지표 코드
    private String indexName;//지표 이름
    private Double indexValue;//지표 수치

}
