package com.wupeng.demo.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    private final  static Log log = LogFactory.getLog(QRCodeUtil.class);

    /* 图片格式 */
    public static final String QRCODE_PNG = "png";
    public static final String QRCODE_JPG = "jpg";
    public static final String QRCODE_GIF = "gif";
    public static final String QRCODE_BMP = "bmp";
    public static final String QRCODE_JPEG = "jpeg";
    public static final String QRCODE_PNM = "pnm";

    /* 图片宽高度 */
    private static final Integer QRCODE_WIDTH_80PX=80,QRCODE_WIDTH_200PX=200,QRCODE_WIDTH_400PX=400;
    private static final Integer QRCODE_HEIGHT_80PX=80,QRCODE_HEIGHT_200PX=200,QRCODE_HEIGHT_400PX=400;

    private static final int BLACK = 0xFF000000;// 默认是黑色
    private static final int WHITE = 0xFFFFFFFF;// 背景颜色

    public static String createQrCode(String url, String path, String fileName) {
        try {
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 180, 180, hints);
            File file = new File(path, fileName);
            if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile())) {
                writeToFile(bitMatrix, "jpg", file);
/*                OutputStream out = new FileOutputStream(file);
                writeToStream(bitMatrix,"jpg",out);*/
                log.info("二维码保存路径="+file);
                return file.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }



    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }



}
