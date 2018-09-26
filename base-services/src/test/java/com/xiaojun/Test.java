package com.xiaojun;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author xiaojun
 * @date 2018/9/26 22:42
 */
public class Test {


    public static void main(String[] args) throws Exception {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("D:\\test1.pdf")));

        OutputStream outputStream = new ByteArrayOutputStream();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        OutputStream outputStream1 = setWatermark(outputStream, "D:/test.pdf", format.format(cal.getTime())
                + "  下载使用人：" + "测试user", 16);

        ByteArrayOutputStream out = (ByteArrayOutputStream) outputStream1;
        InputStream in = new ByteArrayInputStream(out.toByteArray());

        inputstreamtofile(in,new File("E:\\水印123.pdf"));

    }

    public static OutputStream setWatermark(OutputStream bos, String input,
                                    String waterMarkName, int permission) throws DocumentException, IOException {

        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            content = stamper.getOverContent(i);// 在内容上方加水印  
            //content = stamper.getUnderContent(i);//在内容下方加水印  
            gs.setFillOpacity(0.2f);
            // content.setGState(gs);  
            content.beginText();
            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(base, 50);
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, "公司内部文件，请注意保密！", 300, 350, 55);

            Image image = Image.getInstance("D:\\0.png");

            /*img.setAlignment(Image.LEFT | Image.TEXTWRAP);
            img.setBorder(Image.BOX); 
            img.setBorderWidth(10); 
            img.setBorderColor(BaseColor.WHITE); 
            img.scaleToFit(1000, 72);//大小 
            img.setRotationDegrees(-30);//旋转 */
            image.setAbsolutePosition(200, 206);// set the first background image of the absolute   
            image.scaleToFit(200, 200);
            content.addImage(image);

            content.setColorFill(BaseColor.BLACK);
            content.setFontAndSize(base, 8);
            content.showTextAligned(Element.ALIGN_CENTER, "下载时间：" + waterMarkName + "", 300, 10, 0);
            content.endText();

        }
        stamper.close();
        return bos;
    }

    public static void inputstreamtofile(InputStream ins,File file) throws Exception{
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }
}
