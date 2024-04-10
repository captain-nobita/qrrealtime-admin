/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.napas.qr.qrrealtime.define;

/**
 *
 * @author huynx
 */
public enum SystemDirectionDef {
    IBFT20_IBFT, IBFT_IBFT20, IBFT20_IBFT20, IBFT20_ACH, ACH_IBFT20;
    
    public static SystemDirectionDef inverseDirection(SystemDirectionDef input) {
        SystemDirectionDef output;
        switch(input) {
            case IBFT20_IBFT -> output = IBFT_IBFT20;
            case IBFT_IBFT20 -> output = IBFT20_IBFT;
            case IBFT20_IBFT20 -> output = IBFT20_IBFT20;
            case IBFT20_ACH -> output = ACH_IBFT20;
            case ACH_IBFT20 -> output = IBFT20_ACH;
            default -> output = IBFT20_IBFT20;
        }
        
        return output;
    }
}
