package nanqu.djtu.utils;

import org.apache.commons.lang3.StringUtils;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

/**
 * Created by zwz on 2017/4/9.
 */
public class PrinterUtils implements Printable {
    /**
     * 打印内容主题
     */
    private List<PrintModel> listModel;
    /**
     * 小票标题
     */
    private String title;
    /**
     * 打印纸高度
     */
    private Integer height;
    /**
     * 标题距左距离
     */
    private Integer marginLeft = 50;
    /**
     * 标题距上距离
     */
    private Integer marginTop = 10;

    public int commonPrint(){

        int height = 0 + listModel.size() * 20 + this.height;

        // 通俗理解就是书、文档
        Book book = new Book();

        // 打印格式
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(230, height);
        p.setImageableArea(5, -20, 230, height + 20);
        pf.setPaper(p);

        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new PrinterUtils(this.listModel, this.title), pf);

        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(book);

        try {
            job.print();
        } catch (PrinterException e) {
            System.out.println("================打印出现异常");
        }

        return 0;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font(title, Font.PLAIN, 12));
        g2d.drawString(title, marginLeft, marginTop);
        g2d.setFont(new Font(title, Font.PLAIN, 8));
        int i = 1;
        g2d.drawString("--------------------------------------------", 7, 20);
        for (PrintModel m : listModel) {
            if(!m.getIsWrap()){
                if(StringUtils.isNotBlank(m.getName())){
                    g2d.drawString(m.getName() + m.getVal(), 7, 30+i*12);
                }else if (null != m.getImage()) {
                    g2d.drawImage(m.getImage(), 15, 20+i*12, null);
                    i+=8;
                }
            }else {
                g2d.drawString(m.getName(), 7, 30+i*12);
                i++;
                g2d.drawString(m.getVal(), 7, 30+i*12);
            }
            i++;
        }
        return PAGE_EXISTS;
    }


    public PrinterUtils(List<PrintModel> listModel, String title, Integer height, Integer marginLeft, Integer marginTop) {
        this.listModel = listModel;
        this.title = title;
        this.height = height;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
    }

    public PrinterUtils(List<PrintModel> listModel, String title, Integer height) {
        this.listModel = listModel;
        this.title = title;
        this.height = height;
    }

    public PrinterUtils(List<PrintModel> listModel, String title) {
        this.listModel = listModel;
        this.title = title;
    }

}

