package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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
    private LocalDate businessYear;
    //사업 년도
    @Column(name = "stock_code")
    private String stockCode;
    //주식 코드
    @Column(name = "idc_class_code")
    private String indicatorClassificationCode;

    @Column(name = "idc_class_name")
    private String classificationName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_code")
    private CorpCode corpCode;

    }