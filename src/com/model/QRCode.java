package com.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class QRCode {
    /**
     * 生成二维码
     */
    public static BufferedImage QREncode(String content,int oncolor){
        int width = 250; // 图像宽度
        int height = 250; // 图像高度
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //内容编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = null;
        BufferedImage bufferedImage = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(oncolor, 0xFFFFFFFF);

            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }
}
