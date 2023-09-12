package com.napas.qr.models;


import com.napas.qr.define.ReportOfflineFunctionParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportOfflineDef {

    private String reportCode;
    private String jasperFile;
    private String name;
    private String functionName;
    private Boolean enabled = true;
    private Map<ReportOfflineFunctionParameter, Boolean> parameters;
}
