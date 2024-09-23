package com.stockfInder.stock_finder.dart_api.controller;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.CorpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DartApiAccessController {

    @Value("${dart.openapi.apikey}")
    private String dartApiKey;
    private final String dartBaseUrl = "https://opendart.fss.or.kr/api/";
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private CorpCodeService corpCodeService;

    @GetMapping("/download-corp-code")
    public String updateCorpCode() {
        String url = dartBaseUrl + "corpCode.xml?crtfc_key=" + dartApiKey;
        System.out.println("url = " + url);
        ResponseEntity<Resource> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Resource.class);
        corpCodeService.downloadCorpCode(exchange);
        return dartApiKey;
    }
}

