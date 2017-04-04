package nanqu.djtu.admin.printer.repository;

import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.Printer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */

public interface PrinterRepositoryI {

    /**
     * 查询打印机信息列表
     *
     * @return 所有未删除打印机的信息
     */
    List<Printer> query4List();

    /**
     * 添加新的打印机
     *
     * @param printer 新打印机的信息
     * @return 如果添加成功返回true, else false
     */

    boolean insertNewPrinter(Printer printer);

    /**
     * 删除打印机
     *
     * @param printerId 打印机Id
     * @return 如果添加成功返回true, else false
     */

    boolean deletePrinter(String printerId);

    /**
     * 查询需要编辑的打印机的信息
     *
     * @param printerId 打印机Id
     * @return 校区信息对象
     */

    Printer query4Edit(String printerId);
    /**
     * 更新打印机信息
     *
     * @param printer 新的更改后的打印机信息
     * @return 更改成功返回true, else false
     */

    boolean updatePrinter(Printer printer);
    /**
     * 验证打印机编号的唯一
     *
     * @param printerNumber 打印机编号
     * @return 唯一返回true, else false
     */

    boolean select4PrinterNumberUnique(String printerNumber);

    /**
     * 验证打印机Ip的唯一
     *
     * @param printIp 打印机Ip
     * @return 唯一返回true, else false
     */

    boolean query4PrintIpUnique(String printIp);
}
