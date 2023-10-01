package com.napas.qr.qrrealtime.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.entity.*;
import com.napas.qr.qrrealtime.repository.MerchantCashierRepository;
import com.napas.qr.qrrealtime.repository.MerchantPersonalRepository;
import com.napas.qr.qrrealtime.utils.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huynx
 */
@Service
public class VietQR extends BaseService {

    private static final String outputDirectoryPath = "./report/image/outputImages";
    Resource reOutput = new FileSystemResource(outputDirectoryPath);

    @Autowired
    private MerchantPersonalRepository merchantPersonalRepository;

    @Autowired
    private MerchantCashierRepository merchantCashierRepository;

    @Value("${bankId}")
    private String bankId;

    public void generateQRCode(HttpServletResponse response) {
        try {
            cleanAndInitOutputDirectory(reOutput);
            BufferedImage qrCodeImage = generateQRCodeImage();
            int qrCodeSize = qrCodeImage.getWidth();

            BufferedImage LogoVietQR = getLogoImage("VietQRLogo.png");
            BufferedImage logoNPandPV = getLogoImage("NPandPV.png");
            BufferedImage borderImage = getLogoImage("VietQRBorder.png");

            int originalLogoWidthNP = LogoVietQR.getWidth();
            int originalLogoHeightNP = LogoVietQR.getHeight();
            int logoWidth1 = 200;
            int logoWidth2 = 240;
            int newLogoHeight1 = (int) ((double) logoWidth1 / originalLogoWidthNP * originalLogoHeightNP);
            int newLogoHeight2 = (int) ((double) logoWidth2 / originalLogoWidthNP * originalLogoHeightNP);
            int borderImageWidth = borderImage.getWidth();
            int borderImageHeight = borderImage.getHeight();
            int borderImageNewWidth = borderImageWidth - 500;
            int borderImageNewHeight = borderImageHeight - 650;

            BufferedImage resizedLogo1 = new BufferedImage(logoWidth1, newLogoHeight1, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedLogo2 = new BufferedImage(logoWidth2, newLogoHeight2, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedBorderImage = new BufferedImage(borderImageNewWidth, borderImageNewHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D resizedBorderGraphics = resizedBorderImage.createGraphics();


            resizedBorderGraphics.drawImage(borderImage, 0, 0, borderImageNewWidth, borderImageNewHeight, null);


            BufferedImage combinedImage = new BufferedImage(borderImageNewWidth, borderImageNewHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D combinedGraphics = combinedImage.createGraphics();

            combinedGraphics.drawImage(resizedBorderImage, 0, 0, null);
            Graphics2D logoGraphics1 = resizedLogo1.createGraphics();
            Graphics2D logoGraphics2 = resizedLogo2.createGraphics();
            logoGraphics1.drawImage(LogoVietQR, 0, 0, logoWidth1, newLogoHeight1, null);
            logoGraphics2.drawImage(logoNPandPV, 0, 0, logoWidth2, newLogoHeight2, null);
            logoGraphics1.dispose();
            logoGraphics2.dispose();
            int qrCodeX = (borderImageNewWidth - qrCodeSize) / 2;
            int qrCodeY = (borderImageNewHeight - qrCodeSize) / 2;
            int logoX1 = (borderImageNewWidth - logoWidth1) / 2;
            int logoY1 = qrCodeY - newLogoHeight1 ;
            int logoX2 = (borderImageNewWidth - logoWidth2) / 2;
            int logoY2 = qrCodeY + qrCodeSize;

            // Hiển thị STK
            FontMetrics fontMetricsAmount = combinedGraphics.getFontMetrics();
            int textYAmount = qrCodeY + qrCodeSize + fontMetricsAmount.getHeight() + 55;
            Font fontSTK = new Font("Times New Roman", Font.BOLD, 12);
            combinedGraphics.setFont(fontSTK);
            combinedGraphics.setColor(new Color(30, 66, 126));
            FontMetrics fontMetricsSTK = combinedGraphics.getFontMetrics();
            int textXSTK = (borderImageNewWidth - fontMetricsSTK.stringWidth(getAccountPersonal())) / 2;
            int textYSTK = textYAmount;
            combinedGraphics.drawString(getAccountPersonal(), textXSTK, textYSTK);

            combinedGraphics.setFont(fontSTK);
            combinedGraphics.setColor(new Color(30, 66, 126));
            FontMetrics fontMetricsTTK = combinedGraphics.getFontMetrics();
            int textXTTK = (borderImageNewWidth - fontMetricsTTK.stringWidth(getNamePersonal())) / 2;
            int textYTTK = textYAmount + 15;
            combinedGraphics.drawString(getNamePersonal(),textXTTK, textYTTK);

            combinedGraphics.drawImage(qrCodeImage, qrCodeX, qrCodeY, null);
            combinedGraphics.drawImage(resizedLogo1, logoX1, logoY1, null);
            combinedGraphics.drawImage(resizedLogo2, logoX2, logoY2, null);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "png", outputStream);
            response.setContentType("image/png");
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(outputStream.toByteArray());
            responseOutputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "Lỗi xử lý yêu cầu quyền");
        }
    }


    private BufferedImage generateQRCodeImage() throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(dataQr(),
                BarcodeFormat.QR_CODE, 230, 230, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());
    }

    private BufferedImage generateQRCodeImageCashier() throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(dataQrCashier(),
                BarcodeFormat.QR_CODE, 230, 230, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());
    }
    private MatrixToImageConfig getMatrixConfig() {
        return new MatrixToImageConfig(QrCodeColors.BLACK.getArgb(), QrCodeColors.WHITE.getArgb());
    }
    public enum QrCodeColors {
        BLUE(0xFF40BAD0),
        RED(0xFFE91C43),
        PURPLE(0xFF8A4F9E),
        ORANGE(0xFFF4B13D),
        WHITE(0xFFFFFFFF),
        BLACK(0xFF000000);

        private final int argb;

        QrCodeColors(final int argb) {
            this.argb = argb;
        }

        public int getArgb() {
            return argb;
        }
    }
    private void cleanAndInitOutputDirectory( Resource reOutput) throws IOException {
        Path outputDirectory = Paths.get(reOutput.getURI());
        if (Files.exists(outputDirectory)) {
            Files.walk(outputDirectory)
                    .sorted((p1, p2) -> -p1.compareTo(p2))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } else {
            Files.createDirectories(outputDirectory);
        }
    }
    private BufferedImage getLogoImage(String logo) throws IOException {
        Resource resource = new ClassPathResource("./report/image/QRimages/" + logo);
        try (InputStream inputStream = resource.getInputStream()) {
            return ImageIO.read(inputStream);
        }
    }
    public void generateQRCodeCashier(HttpServletResponse response) {
        try {
            cleanAndInitOutputDirectory(reOutput);
            BufferedImage qrCodeImage = generateQRCodeImageCashier();
            int qrCodeSize = qrCodeImage.getWidth();

            BufferedImage LogoVietQR = getLogoImage("VietQRLogo.png");
            BufferedImage logoNPandPV = getLogoImage("NPandPV.png");
            BufferedImage borderImage = getLogoImage("VietQRBorder.png");

            int originalLogoWidthNP = LogoVietQR.getWidth();
            int originalLogoHeightNP = LogoVietQR.getHeight();
            int logoWidth1 = 200;
            int logoWidth2 = 240;
            int newLogoHeight1 = (int) ((double) logoWidth1 / originalLogoWidthNP * originalLogoHeightNP);
            int newLogoHeight2 = (int) ((double) logoWidth2 / originalLogoWidthNP * originalLogoHeightNP);
            int borderImageWidth = borderImage.getWidth();
            int borderImageHeight = borderImage.getHeight();
            int borderImageNewWidth = borderImageWidth - 500;
            int borderImageNewHeight = borderImageHeight - 650;

            BufferedImage resizedLogo1 = new BufferedImage(logoWidth1, newLogoHeight1, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedLogo2 = new BufferedImage(logoWidth2, newLogoHeight2, BufferedImage.TYPE_INT_ARGB);
            BufferedImage resizedBorderImage = new BufferedImage(borderImageNewWidth, borderImageNewHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D resizedBorderGraphics = resizedBorderImage.createGraphics();


            resizedBorderGraphics.drawImage(borderImage, 0, 0, borderImageNewWidth, borderImageNewHeight, null);


            BufferedImage combinedImage = new BufferedImage(borderImageNewWidth, borderImageNewHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D combinedGraphics = combinedImage.createGraphics();



            combinedGraphics.drawImage(resizedBorderImage, 0, 0, null);
            Graphics2D logoGraphics1 = resizedLogo1.createGraphics();
            Graphics2D logoGraphics2 = resizedLogo2.createGraphics();
            logoGraphics1.drawImage(LogoVietQR, 0, 0, logoWidth1, newLogoHeight1, null);
            logoGraphics2.drawImage(logoNPandPV, 0, 0, logoWidth2, newLogoHeight2, null);
            logoGraphics1.dispose();
            logoGraphics2.dispose();
            int qrCodeX = (borderImageNewWidth - qrCodeSize) / 2;
            int qrCodeY = (borderImageNewHeight - qrCodeSize) / 2;
            int logoX1 = (borderImageNewWidth - logoWidth1) / 2;
            int logoY1 = qrCodeY - newLogoHeight1 ;
            int logoX2 = (borderImageNewWidth - logoWidth2) / 2;
            int logoY2 = qrCodeY + qrCodeSize;


            // Hiển thị STK
            FontMetrics fontMetricsAmount = combinedGraphics.getFontMetrics();
            int textYAmount = qrCodeY + qrCodeSize + fontMetricsAmount.getHeight() + 55;
            Font fontSTK = new Font("Times New Roman", Font.BOLD, 12);
            combinedGraphics.setFont(fontSTK);
            combinedGraphics.setColor(new Color(30, 66, 126));
            FontMetrics fontMetricsSTK = combinedGraphics.getFontMetrics();
            int textXSTK = (borderImageNewWidth - fontMetricsSTK.stringWidth(getAccountCashier())) / 2;
            int textYSTK = textYAmount;
            combinedGraphics.drawString(getAccountCashier(), textXSTK, textYSTK);


            combinedGraphics.setFont(fontSTK);
            combinedGraphics.setColor(new Color(30, 66, 126));
            FontMetrics fontMetricsTTK = combinedGraphics.getFontMetrics();
            int textXTTK = (borderImageNewWidth - fontMetricsTTK.stringWidth(getNameCashier())) / 2;
            int textYTTK = textYAmount + 15;
            combinedGraphics.drawString(getNameCashier(),textXTTK, textYTTK);


            combinedGraphics.drawImage(qrCodeImage, qrCodeX, qrCodeY, null);
            combinedGraphics.drawImage(resizedLogo1, logoX1, logoY1, null);
            combinedGraphics.drawImage(resizedLogo2, logoX2, logoY2, null);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "png", outputStream);
            response.setContentType("image/png");
            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(outputStream.toByteArray());
            responseOutputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED, "Lỗi xử lý yêu cầu quyền");
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

    public String getAccountCashier() {
        if (getUserDetails().getTargetType() == ETargetType.CASHIER) {
            Long cashierId = getUserDetails().getCashier().getId();
            TblMerchantCashier merchantCashier = merchantCashierRepository.findById(cashierId).orElse(null);
            String data= merchantCashier.getTblMerchantBranch().getTblSettleBank().getBankReceiveCode() +merchantCashier.getTblMerchantBranch().getTblMerchantCorporate().getTblMasterMerchant().getMmCode()+
                    merchantCashier.getTblMerchantBranch().getTblMerchantCorporate().getMerchantCode()+merchantCashier.getTblMerchantBranch().getBranchCode()+
                    merchantCashier.getCashierCode();
            return "Số TK: " + data;
        }
        return null;
    }



    public String getAccountPersonal() {
        if (getUserDetails().getTargetType() == ETargetType.PERSONAL) {
            Long merchantId = getUserDetails().getMerchantPersonal().getId();
            TblMerchantPersonal merchantPersonal = merchantPersonalRepository.findById(merchantId).orElse(null);
            String data = merchantPersonal.getTblSettleBank().getBankReceiveCode()+merchantPersonal.getTblMasterMerchant().getMmCode()+"000"+merchantPersonal.getMerchantCode() ;
            return "Số TK: " + data;
        }
        return null;
    }

    public String getNameCashier() {
        if (getUserDetails().getTargetType() == ETargetType.CASHIER) {
            TblMerchantBranch branch = getUserDetails().getBranch();
            TblMerchantCorporate merchantCorporate = getUserDetails().getMerchant();
            String data=VNCharacterUtils.removeAccent(merchantCorporate.getName() +" - "+branch.getName()) ;
            return "Tên Tài Khoản: " + data;
        }
        return null;
    }



    public String getNamePersonal() {
        if (getUserDetails().getTargetType() == ETargetType.PERSONAL) {
            TblMasterMerchant masterMerchant = getUserDetails().getMasterMerchant();
            TblMerchantPersonal merchantPersonal = getUserDetails().getMerchantPersonal();
            String data =VNCharacterUtils.removeAccent(masterMerchant.getName() + " - " + merchantPersonal.getName());
            return "Tên Tài Khoản: " + data;
        }
        return null;
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

}
