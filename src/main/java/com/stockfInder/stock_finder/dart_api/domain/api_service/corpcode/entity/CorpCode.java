package com.stockfInder.stock_finder.dart_api.domain.api_service.corpcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CorpCode {
    @Id
    @Column(name = "corp_code")
    private String corpCode;

    @Column(name = "corp_name")
    private String corpName;
    @Column(name = "scock_code")
    private String stockCode;
    @Column(name = "modify_date")
    private String modifyDate;
}
