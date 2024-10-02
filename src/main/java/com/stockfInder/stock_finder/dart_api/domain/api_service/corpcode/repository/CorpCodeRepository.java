package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.repository;

import com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity.CorpCode;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorpCodeRepository extends JpaRepository<CorpCode, String> {
    List<CorpCode> findAll();
}
