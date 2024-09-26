package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_indicator;

import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement.FinancialStatement;
import jakarta.persistence.*;

@Embeddable
public class FinancialIndicatorEmbeddable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fin_idc_id")
    private int financialIndicatorId;

    @ManyToOne
    private FinancialStatement financialStatement;

}
