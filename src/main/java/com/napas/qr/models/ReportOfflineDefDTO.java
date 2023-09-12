package com.napas.qr.models;

import com.napas.qr.define.ReportOfflineFunctionParameter;

import java.util.Map;

public class ReportOfflineDefDTO {

    private String reportCode;
    private String name;
    private Map<ReportOfflineFunctionParameter, Boolean> parameters;

    public ReportOfflineDefDTO() {
    }

    public ReportOfflineDefDTO(String reportCode, String name, Map<ReportOfflineFunctionParameter, Boolean> parameters) {
        this.reportCode = reportCode;
        this.name = name;
        this.parameters = parameters;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<ReportOfflineFunctionParameter, Boolean> getParameters() {
        return parameters;
    }

    public void setParameters(Map<ReportOfflineFunctionParameter, Boolean> parameters) {
        this.parameters = parameters;
    }

}
