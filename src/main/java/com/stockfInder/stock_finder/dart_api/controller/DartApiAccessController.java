package com.stockfInder.stock_finder.dart_api.controller;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.CorpCodeFileExtractor;
import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.service.CorpCodeService;
import com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto.request.FinancialStatementRequestDto;
import com.stockfInder.stock_finder.dart_api.domain.api_service.xmlreader.ReadCorpCodeXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class DartApiAccessController {
    private final String dartBaseUrl = "https://opendart.fss.or.kr/api/";
    @Value("${dart.openapi.apikey}")
    private String dartApiKey;
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CorpCodeService corpCodeService;
    @Autowired
    private CorpCodeFileExtractor corpCodeFileExtractor;

    @GetMapping("/download-corp-code")
    public String updateCorpCode() {
        String url = dartBaseUrl + "corpCode.xml?crtfc_key=" + dartApiKey;
        System.out.println("url = " + url);
        ResponseEntity<Resource> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Resource.class);
        corpCodeFileExtractor.downloadCorpCode(exchange);
        corpCodeService.insertCorpCode(ReadCorpCodeXml.readCorpCode());
        return dartApiKey;
    }

    @GetMapping("/read-finstmt")
    public String readFinstmt(@ModelAttribute FinancialStatementRequestDto financialStatementRequestDto) {
        System.out.println("financialStatementRequestDto = " + financialStatementRequestDto);
        String url = dartBaseUrl + "fnlttSinglIndx.json?crtfc_key=" + dartApiKey
                + "&corp_code=" + financialStatementRequestDto.getCorpCode()
                + "&idx_cl_code=" + financialStatementRequestDto.getIndicatorClassificationCode()
                + "&bsns_year=" + financialStatementRequestDto.getBusinessYear()
                + "&reprt_code=" + financialStatementRequestDto.getReportCode();
        System.out.println("url = " + url);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        System.out.println("exchange = " + exchange);
        return "OK";
    }
}

