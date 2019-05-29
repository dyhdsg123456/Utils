import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Author: dyh
 * Date:   2019/5/29
 * Description:
 */
public class PdfTest {
    public static void main(String[] args) throws Exception{
        // 要输出的pdf文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("E:\\opt\\pdf\\abcde.pdf")));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 将pdf文件先加水印然后输出
        setWatermark(bos, "E:\\opt\\pdf\\b.pdf", format.format(cal.getTime()) + "  下载使用人：" + "测试user", 16);
    }

    public static void setWatermark(BufferedOutputStream bos, String input, String waterMarkName, int permission)
            throws DocumentException, IOException {
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            content = stamper.getOverContent(i);// 在内容上方加水印
            // content = stamper.getUnderContent(i);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            // content.setGState(gs);
            content.beginText();
            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(base, 20);
            content.setTextMatrix(70, 200);
            content.showTextAligned(Element.ALIGN_CENTER, "韩洋", 100, 350, 55);
            content.showTextAligned(Element.ALIGN_CENTER, "韩洋", 400, 350, 55);
            content.showTextAligned(Element.ALIGN_CENTER, "韩洋", 700, 350, 55);
            content.setColorFill(BaseColor.BLACK);
            content.setFontAndSize(base, 2);
//            content.showTextAligned(Element.ALIGN_CENTER, "下载时间：" + waterMarkName + "", 300, 10, 0);
            content.endText();

        }
        stamper.close();
    }
}
