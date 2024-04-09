/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.qr.qrrealtime.define;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author huynx
 */
@Data
public class AmountOfMoney implements Serializable {
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal value;
    
    @NotEmpty
    private String currencyCode;
}
