package com.stockfInder.stock_finder.dart_api.util;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.CorpCodeFileExtractor;
import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.FinancialStatementDto;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.request.FinancialStatementRequestDto;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.response.FinancialStatementApiResponse;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.entity.financial_statement.FinancialStatement;
import com.stockfInder.stock_finder.dart_api.domain.api_service.xmlreader.ReadCorpCodeXml;
import com.stockfInder.stock_finder.dart_api.util.fin_class_enum.FinancialClassEnum;
import com.stockfInder.stock_finder.dart_api.util.fin_class_enum.ReportCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DartApiAccessManager {
    private final String dartBaseUrl = "https://opendart.fss.or.kr/api/";
    @Value("${dart.openapi.apikey}")
    private String dartApiKey;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private CorpCodeFileExtractor corpCodeFileExtractor;


    public List<CorpCode> updateCorpCode() {
        String url = dartBaseUrl + "corpCode.xml?crtfc_key=" + dartApiKey;
        System.out.println("url = " + url);
        ResponseEntity<Resource> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Resource.class);
        corpCodeFileExtractor.downloadCorpCode(exchange);
        return ReadCorpCodeXml.readCorpCode();
    }

    public List<List<FinancialStatement>> getFinancialInfo(FinancialStatementRequestDto financialStatementRequestDto) {
        System.out.println("financialStatementRequestDto = " + financialStatementRequestDto);
        return loadFinancialStatement(financialStatementRequestDto).stream().map(stmts -> stmts
                .stream()
                .map(stmt -> new FinancialStatement(stmt, new CorpCode(stmt.getCorpCode())))
                .toList()).toList();
    }

    private List<List<FinancialStatementDto>> loadFinancialStatement(FinancialStatementRequestDto financialStatementRequestDto) {
        List<List<FinancialStatementDto>> responseContainer = new ArrayList<>();
        List<FinancialClassEnum> financialClassEnums = FinancialClassEnum.getFinancialClassEnums();
        List<String> reportCodes = ReportCodeEnum.getReportCodes();
        for (FinancialClassEnum financialClassEnum : financialClassEnums) {
            String url = dartBaseUrl + "fnlttSinglIndx.json?crtfc_key=" + dartApiKey
                    + "&corp_code=" + financialStatementRequestDto.getCorpCode()
                    + "&idx_cl_code=" + financialClassEnum.name()
                    + "&bsns_year=" + financialStatementRequestDto.getBusinessYear();
            responseContainer.addAll(loadByReportCode(url, reportCodes));
        }
        return responseContainer;
    }

    private List<List<FinancialStatementDto>> loadByReportCode(String url, List<String> reportCodes) {
        List<List<FinancialStatementDto>> responseContainer = new ArrayList<>();
        for (String reportCode : reportCodes) {
            System.out.println("url = " + url + "&reprt_code=" + reportCode);
            System.out.println("reportCode = " + reportCode);
            ResponseEntity<FinancialStatementApiResponse> exchange
                    = restTemplate
                    .exchange(url + "&reprt_code=" + reportCode,
                            HttpMethod.GET, null, FinancialStatementApiResponse.class);
            responseContainer.add(Objects.requireNonNull(exchange.getBody()).getData());
        }
        System.out.println("responseContainer = " + responseContainer);
        removeNullValue(responseContainer);
        return responseContainer;
    }

    private void removeNullValue(List<?> list) {
        list.removeIf(Objects::isNull);
    }

}
