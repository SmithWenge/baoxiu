package nanqu.djtu.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
public class QRCodeEvents {

    /**
     * @discription 生成二维码图片，返回Image对象
     * @author 李亚飞
     * @created 2016年2月3日 上午11:42:21
     * @param text 二维码文字
     * @return
     * @throws Exception
     */
    public Image writeQrCodeContent(String text, String path) throws Exception {
        int width = 100; // 二维码图片宽度
        int height = 100; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        File outputFile = new File(path + File.separator + "1.jpg");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

        File file=new File(path+"1.jpg");
        InputStream is = new FileInputStream(file);
        BufferedImage bi;
        bi = ImageIO.read(is);
        java.awt.Image im = (java.awt.Image)bi;

        return im;
    }
}
