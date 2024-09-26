package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import jakarta.persistence.*;
import lombok.Getter;

@Embeddable
@Getter
public class FinancialStatementEmbeddable {
    @ManyToOne
    @JoinColumn(name = "corp_code", referencedColumnName = "corp_code")
    private CorpCode corpCode;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stmt_id")
    private int statementId;

}
