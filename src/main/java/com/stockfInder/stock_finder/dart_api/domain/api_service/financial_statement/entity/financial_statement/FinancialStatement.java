package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.FinancialStatementDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FinancialStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fin_stmt_id")
    private Long financialStatementId;

    @Column(name = "report_cpde")
    private String reportCode;
    //분기 코드
    @Column(name = "business_year")
    private Long businessYear;
    //사업 년도
    @Column(name = "stock_code")
    private String stockCode;
    //주식 코드
    @Column(name = "idc_class_code")
    private String indicatorClassCode;

    @Column(name = "idc_class_name")
    private String indicatorClassName;

    @Column(name = "idc_code")
    private String indicatorCode;

    @Column(name = "idc_name")
    private String indicatorName;

    @Column(name = "idc_value")
    private Double indicatorValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "corp_code")
    private CorpCode corpCode;


    public FinancialStatement(FinancialStatementDto d, CorpCode corpCode) {
        reportCode = d.getReportCode();
        businessYear = d.getBusinessYear();
        this.corpCode = corpCode;
        stockCode = d.getStockCode();
        indicatorClassCode = d.getIndicatorClassCode();
        indicatorClassName = d.getIndicatorClassName();
        indicatorCode = d.getIndicatorCode();
        indicatorName = d.getIndicatorName();
        indicatorValue = d.getIndicatorValue();
    }
}