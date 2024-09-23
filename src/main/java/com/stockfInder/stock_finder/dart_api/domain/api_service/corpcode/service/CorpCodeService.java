package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.service;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.repository.CorpCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorpCodeService {
    private final CorpCodeRepository corpCodeRepository;

    public long countCorpCodeRows() {
        return corpCodeRepository.count();
    }
}
