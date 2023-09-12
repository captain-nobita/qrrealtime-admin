package com.napas.qr.models;

import com.napas.qr.define.BankOperationCode;
import com.napas.qr.define.ParticipantRole;
import com.napas.qr.define.ReportOfflineParticipantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.PrimitiveIterator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportOfflineFilter {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateBegin;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateEnd;

    private String departmentName;
    private String merchantName;
    private String mgsType;

    private String transType;
    private String status;
    private String serviceCode;
    private String responseCode;
    private String amountFrom; // số tiền từ
    private String amountTo; // số tiền đến


}