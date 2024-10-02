package com.stockfInder.stock_finder.dart_api.controller;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.service.CorpCodeService;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.request.FinancialStatementRequestDto;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement.FinancialStatement;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.service.financial_statement.FinancialStatementService;
import com.stockfInder.stock_finder.dart_api.util.DartApiAccessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//https://api.finance.naver.com/siseJson.naver?symbol=005930&requestType=1&startTime=20241002&endTime=20241002&timeframe=day
//stockCode 동일
@RestController
public class DartApiAccessController {

    @Autowired
    private DartApiAccessManager dartApiAccessManager;
    @Autowired
    private CorpCodeService corpCodeService;
    @Autowired
    private FinancialStatementService statementService;

    @GetMapping("/download-corp-code")
    public int updateCorpCode() {
        List<CorpCode> corpCodes = dartApiAccessManager.updateCorpCode();

        return corpCodeService.insertCorpCode(corpCodes);
    }

    @GetMapping("/update-finstmt")
    public String updateStmt(@ModelAttribute FinancialStatementRequestDto financialStatementRequestDto) {
        List<List<FinancialStatement>> financialInfo = dartApiAccessManager.getFinancialInfo(financialStatementRequestDto);
        statementService.insertFinancialStatement(financialInfo);
        return "OK";
    }

    @GetMapping("/read-finstmt")
    public List<FinancialStatement> reatStmt(@ModelAttribute FinancialStatementRequestDto financialStatementRequestDto) {
        List<FinancialStatement> financialState = statementService.findFinancialState(financialStatementRequestDto);
        if (financialState.isEmpty()) {
            updateStmt(financialStatementRequestDto);
            financialState = statementService.findFinancialState(financialStatementRequestDto);
        }
        return financialState;
    }
}

