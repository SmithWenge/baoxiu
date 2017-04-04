package nanqu.djtu.admin.printer.service.impl;

import nanqu.djtu.admin.printer.repository.PrinterRepositoryI;
import nanqu.djtu.admin.printer.service.PrinterServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
@Service
public class PrinterServiceImpl implements PrinterServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(PrinterServiceImpl.class);
    @Autowired
    private PrinterRepositoryI printerRepository;

    @Override
    public List<Printer> query4List() {
        return printerRepository.query4List();
    }

    @Override
    public boolean saveNewPrinter(Printer printer, AdminUser user) {
        boolean insert = printerRepository.insertNewPrinter(printer);

        if (insert) {
            LOG.info("[Printer] add new place distinct success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[Printer] add new place distinct failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public boolean deletePrinter(String printerId, AdminUser user) {
        boolean delete = printerRepository.deletePrinter(printerId);

        if (delete) {
            LOG.info("[Printer] delete place distinct {} success with user {}.", printerId, user.getAdminName());
        } else {
            LOG.warn("[Printer] delete place distinct {} failure with user {}.", printerId, user.getAdminName());
        }

        return delete;
    }

    @Override
    public Printer query4Edit(String printerId) {
        return printerRepository.query4Edit(printerId);
    }

    @Override
    public boolean updatePrinter(Printer printer, AdminUser user) {
        boolean update = printerRepository.updatePrinter(printer);

        if (update) {
            LOG.info("[Printer] update place distinct {} success with user {}.", printer.getPrinterId(), user.getAdminName());
        } else {
            LOG.warn("[Printer] update place distinct {} failure with user {}.", printer.getPrinterId(), user.getAdminName());
        }

        return update;
    }

    @Override
    public boolean query4PlacePrinterNumberUnique(String printerNumber) {

            return printerRepository.select4PrinterNumberUnique(printerNumber);

    }

    @Override
    public boolean query4PrintIpUnique(String printIp) {
        return printerRepository.query4PrintIpUnique(printIp);
    }
}
