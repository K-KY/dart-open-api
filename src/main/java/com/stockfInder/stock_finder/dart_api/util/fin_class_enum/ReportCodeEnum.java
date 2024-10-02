package com.stockfInder.stock_finder.dart_api.util.fin_class_enum;

import java.util.Arrays;
import java.util.List;

/*

1분기보고서 : 11013
반기보고서 : 11012
3분기보고서 : 11014
사업보고서 : 11011
*/
public enum ReportCodeEnum {
    _11013("1분기 보고서"),
    _11012("반기 보고서"),
    _11014("3분기 보고서"),
    _11011("사업 보고서");

    private final String reportCode;
    ReportCodeEnum(String reportCode) {
        this.reportCode = reportCode;
    }

    public static List<String> getReportCodes() {
        return Arrays
                .stream(ReportCodeEnum.values())
                .map(r -> r.name().replace("_", "")).toList();
    }
}
