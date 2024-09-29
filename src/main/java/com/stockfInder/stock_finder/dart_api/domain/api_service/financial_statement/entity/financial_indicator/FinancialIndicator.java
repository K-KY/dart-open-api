package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_indicator;

import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement.FinancialStatement;
import jakarta.persistence.*;
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
    @Id
    @Column(name = "fin_idc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int financialIndicatorId;

    @Column(name = "idc_code")
    private String indicatorCode;

    @Column(name = "idc_name")
    private String indicatorName;

    @Column(name = "idc_value")
    private Double indicatorValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fin_stmt_id")
    private FinancialStatement financialStatement;
}
