package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.repository.financial_statement;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement.FinancialStatement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM FinancialStatement f " +
            "WHERE f.corpCode IN :corpCode " +
            "AND f.businessYear = :businessYear " +
            "AND f.indicatorClassCode = :indicatorClassCode " +
            "AND f.reportCode = :reportCode" )
    void deleteFromIfExists(@Param("corpCode") CorpCode corpCode,
                            @Param("businessYear") long businessYear,
                            @Param("indicatorClassCode") String indicatorClassCode,
                            @Param("reportCode") String reportCode);

    @Query(value = "SELECT * FROM financial_statement f " +
            "WHERE f.corp_code = :corpCode " +
            "AND f.business_year = :businessYear " +
            "AND f.report_cpde = :reportCode", nativeQuery = true)
    List<FinancialStatement> findStatement(@Param("corpCode") String corpCode,
                                           @Param("businessYear") int businessYear,
                                           @Param("reportCode") int reportCode);


}
