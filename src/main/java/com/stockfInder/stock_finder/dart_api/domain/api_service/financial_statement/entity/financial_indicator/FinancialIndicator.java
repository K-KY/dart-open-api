package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_indicator;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FinancialIndicator {
    @EmbeddedId
    private FinancialIndicatorEmbeddable financialStatementEmbed;//대체키 + FinancialStatement
    @Column(name = "idc_code")
    private String indicatorCode;
    @Column(name = "idc_name")
    private String indicatorName;
    @Column(name = "idc_value")
    private Double indicatorValue;
}
