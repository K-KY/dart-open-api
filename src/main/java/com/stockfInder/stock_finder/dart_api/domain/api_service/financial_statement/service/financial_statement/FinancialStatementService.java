package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.service.financial_statement;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.FinancialStatementDto;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.request.FinancialStatementRequestDto;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.response.FinancialStatementApiResponse;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement.FinancialStatement;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.repository.financial_statement.FinancialStatementRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
@AllArgsConstructor
public class FinancialStatementService {
    private final FinancialStatementRepository financialStatementRepository;

    public void insertFinancialStatement(ResponseEntity<FinancialStatementApiResponse> exchange) {
        List<FinancialStatementDto> data = exchange.getBody().getData();
        if (data != null) {
            List<FinancialStatement> list = data.stream().map(d -> new FinancialStatement(d, new CorpCode(d.getCorpCode()))).toList();
            financialStatementRepository.saveAll(list);
        }
    }

    public void insertFinancialStatement(List<List<FinancialStatement>> financialStatements) {
        //영업 년도
        //분류 코드
        //보고서 코드
        //주식 코드
        financialStatements.forEach(finStmt -> {
            deleteIfExist(finStmt);
            financialStatementRepository.saveAll(finStmt);
        });
    }

    private void deleteIfExist(List<FinancialStatement> financialStatements) {
        for (FinancialStatement finStmt : financialStatements) {
            financialStatementRepository.deleteFromIfExists(finStmt.getCorpCode(),
                    finStmt.getBusinessYear(),
                    finStmt.getIndicatorClassCode(),
                    finStmt.getReportCode());
            break;
        }
    }

    public List<FinancialStatement> findFinancialState(String corpCode, int businessYear, int reportCode) {
        List<FinancialStatement> statement = financialStatementRepository.findStatement(corpCode, businessYear, reportCode);
        if (statement.isEmpty()) {
            return new ArrayList<>();
        }
        return statement;
    }

    public List<FinancialStatement> findFinancialState(FinancialStatementRequestDto finStmtDto) {
        List<FinancialStatement> statement = financialStatementRepository.findStatement(
                finStmtDto.getCorpCode(), finStmtDto.getBusinessYear(), finStmtDto.getReportCode());
        if (statement.isEmpty()) {
            return new ArrayList<>();
        }
        return statement;
    }
}
