package nanqu.djtu.admin.printer.service;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.Printer;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public interface PrinterServiceI {
    List<Printer> query4List();

    boolean saveNewPrinter(Printer printer, AdminUser user);

    boolean deletePrinter(String printerId, AdminUser user);

    Printer query4Edit(String printerId);

    boolean updatePrinter(Printer printer, AdminUser user);

    boolean query4PlacePrinterNumberUnique(String printerNumber);

    boolean query4PrintIpUnique(String printIp);
}
