package com.stockfInder.stock_finder.dart_api.util.fin_class_enum;


import java.util.Arrays;
import java.util.List;

//수익성지표 : M210000 안정성지표 : M220000 성장성지표 : M230000 활동성지표 : M240000
public enum FinancialClassEnum {
    M210000("수익성지표"),
    M220000("안정성지표"),
    M230000("성장성지표"),
    M240000("활동성지표");

    private final String className;
    FinancialClassEnum(String className) {
        this.className = className;
    }


    // className이 동일한 경우 value 반환
    public static String getFinancialClassName(String className) {
        for (FinancialClassEnum financialClass : FinancialClassEnum.values()) {
            if (financialClass.className.equals(className)) {
                return financialClass.name(); // Enum의 이름(M210000 등)을 반환
            }
        }
        return null; // 일치하는 값이 없을 경우 null 반환
    }

    public static String getFinancialClassKey(FinancialClassEnum financialClassEnum) {
        return financialClassEnum.className;
    }

    public static List<FinancialClassEnum> getFinancialClassEnums() {
        return Arrays.stream(FinancialClassEnum.values()).toList();
    }
}
