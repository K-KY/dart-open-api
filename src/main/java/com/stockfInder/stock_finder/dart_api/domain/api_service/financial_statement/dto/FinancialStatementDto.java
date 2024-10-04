package com.stockfInder.stock_finder.dart_api.domain.api_service.financial_statement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@ToString
public class FinancialStatementDto {
    @JsonProperty("reprt_code")
    private String reportCode;
    @JsonProperty("bsns_year")
    private Long businessYear;
    @JsonProperty("corp_code")
    private String corpCode;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("idx_cl_code")
    private String indicatorClassCode;
    @JsonProperty("idx_cl_nm")
    private String indicatorClassName;
    @JsonProperty("idx_code")
    private String indicatorCode;
    @JsonProperty("idx_nm")
    private String indicatorName;
    @JsonProperty("idx_val")
    @JsonDeserialize(using = CustomDoubleDeserializer.class)
    private Double indicatorValue;

    public static class CustomDoubleDeserializer extends JsonDeserializer<Double> {
        public static final Pattern PATTERN = Pattern.compile("[0-9\\.]*");
        @Override
        public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            try {
                String value = p.getText();
                if (!value.matches(PATTERN.pattern())) {
                    return null; // 또는 0.0으로 기본값 설정
                }
                return Double.valueOf(value);
            } catch (NumberFormatException e) {
                return null; // 예외 발생 시 기본값 처리
            }
        }
    }


}
