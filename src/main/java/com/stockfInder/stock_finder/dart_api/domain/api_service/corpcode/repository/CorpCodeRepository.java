package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.repository;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpCodeRepository extends JpaRepository<CorpCode, String> {
}
