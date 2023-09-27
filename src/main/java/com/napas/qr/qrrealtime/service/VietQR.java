package com.napas.qr.qrrealtime.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.napas.qr.qrrealtime.entity.TblMerchantCashier;
import com.napas.qr.qrrealtime.entity.TblMerchantPersonal;
import com.napas.qr.qrrealtime.repository.MerchantCashierRepository;
import com.napas.qr.qrrealtime.repository.MerchantPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huynx
 */
@Service
public class VietQR extends BaseService {

    @Autowired
    private MerchantPersonalRepository merchantPersonalRepository;

    @Autowired
    private MerchantCashierRepository merchantCashierRepository;

    @Value("${bankId}")
    private String bankId;

    public void generateQRCode(HttpServletResponse response) {
        try {
            String CONTENT = "Hello word";
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(dataQr(),BarcodeFormat.QR_CODE, 200, 200, hints);
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            outputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public void generateQRCodeCashier(HttpServletResponse response) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(dataQrCashier(), BarcodeFormat.QR_CODE, 200, 200, hints);
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            outputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public String  dataQr(){
        Long merchantId = getUserDetails().getMerchantPersonal().getId();
        TblMerchantPersonal merchantPersonal = merchantPersonalRepository.findById(merchantId).orElse(null);
        String data = merchantPersonal.getTblSettleBank().getBankReceiveCode()+merchantPersonal.getTblMasterMerchant().getMmCode()+"000"+merchantPersonal.getMerchantCode() ;
        String qrCodeData = getVietQrNotAmount(bankId, data);
        return qrCodeData;
    }

    public String dataQrCashier(){
        Long cashierId= getUserDetails().getCashier().getId();
        TblMerchantCashier merchantCashier = merchantCashierRepository.findById(cashierId).orElse(null);
        String data= merchantCashier.getTblMerchantBranch().getTblSettleBank().getBankReceiveCode() +merchantCashier.getTblMerchantBranch().getTblMerchantCorporate().getTblMasterMerchant().getMmCode()+
                merchantCashier.getTblMerchantBranch().getTblMerchantCorporate().getMerchantCode()+merchantCashier.getTblMerchantBranch().getBranchCode()+
                merchantCashier.getCashierCode();
        String qrCodeData = getVietQrNotAmount(bankId, data);
        return qrCodeData;
    }

    public static int crc16(final byte[] buffer) {
        int crc = 0xFFFF;

        for (int j = 0; j < buffer.length; j++) {
            crc = ((crc >>> 8) | (crc << 8)) & 0xffff;
            crc ^= (buffer[j] & 0xff);//byte to int, trunc sign
            crc ^= ((crc & 0xff) >> 4);
            crc ^= (crc << 12) & 0xffff;
            crc ^= ((crc & 0xFF) << 5) & 0xffff;
        }
        crc &= 0xffff;
        return crc;
    }

    private static String getCrc16Valid(String vietQRCode) {
        int crc = crc16(vietQRCode.getBytes());
        String crcCode = Integer.toHexString(crc);

        if (crcCode.length() == 4) {
            return crcCode;
        }
        if (crcCode.length() == 3) {
            return "0" + crcCode;
        }
        if (crcCode.length() == 2) {
            return "00" + crcCode;
        }
        if (crcCode.length() == 1) {
            return "000" + crcCode;
        }
        return crcCode;

    }

    public static String getVietQrNotAmount(String bankid, String bankacc) {
        if (bankid.isEmpty() || bankacc.isEmpty() || bankid.length() < 6 || bankacc.length() < 6 || bankacc.length() > 19) {
            return "";//not valid
        }
        String vietQRCode = "000201010212";
        String dvcntt = "0010A000000727";
        String subBenOrg = "00" + String.format("%02d", bankid.length()) + bankid + "01" + String.format("%02d", bankacc.length()) + bankacc;
        String BenOrg = "01" + String.format("%02d", subBenOrg.length()) + subBenOrg;
        dvcntt += BenOrg + "0208QRIBFTTA";
        vietQRCode += "38" + String.format("%02d", dvcntt.length()) + dvcntt;

        vietQRCode += "6304";
        String crcCode = getCrc16Valid(vietQRCode);

        vietQRCode += crcCode.toUpperCase();

        return vietQRCode;

    }

    public static String getVietQr(String bankid, String bankacc, String amount, String description) {
        if (bankid.isEmpty() || bankacc.isEmpty() || bankid.length() < 6) {
            return "";//not valid
        }

        if (amount.isEmpty() || amount.length() > 13) {
            return getVietQrNotAmount(bankid, bankacc);
        }
        String vietQRCode = "000201010212";
        String dvcntt = "0010A000000727";
        String subBenOrg = "00" + String.format("%02d", bankid.length()) + bankid
                + "01" + String.format("%02d", bankacc.length()) + bankacc;
        String BenOrg = "01" + String.format("%02d", subBenOrg.length()) + subBenOrg;
        dvcntt += BenOrg + "0208QRIBFTTA";
        vietQRCode += "38" + String.format("%02d", dvcntt.length()) + dvcntt;
        vietQRCode += "530370454" + String.format("%02d", amount.length()) + amount + "5802VN";
        if (!description.isEmpty()) {

            String desc = "08" + String.format("%02d", description.length()) + description;
            vietQRCode += "62" + String.format("%02d", desc.length()) + desc;
        }
        vietQRCode += "6304";
        String crcCode = getCrc16Valid(vietQRCode);
        vietQRCode += crcCode.toUpperCase();
        //Log.d(TAG,"vietQR "+vietQRCode);
        return vietQRCode;
    }

    public static String fm02Leng(int leng) {
        if (leng > 10) {
            return "" + leng;
        }
        return "0" + leng;
    }
}
