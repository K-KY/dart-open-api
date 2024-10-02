package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CorpCodeServiceTest {
    @Autowired
    private CorpCodeService corpCodeService;
    @Test
    void CorpCodeCountService() {
        assertThat(corpCodeService.countCorpCodeRows()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findAllCorpCode() {
        assertThat(corpCodeService.findAllCorpCode().size()).isEqualTo(corpCodeService.countCorpCodeRows());
    }
}