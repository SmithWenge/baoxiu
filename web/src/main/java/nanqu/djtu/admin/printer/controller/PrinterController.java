package nanqu.djtu.admin.printer.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.place.distinct.service.PlaceDistinctServiceI;
import nanqu.djtu.admin.printer.service.PrinterServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.Printer;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.awt.event.HierarchyBoundsAdapter;
import java.util.List;

/**
 * @author zhangwenyue
 */
@Controller
@RequestMapping("/admin/printer")
public class PrinterController {
    @Autowired
    private PrinterServiceI printerService;

    /**
     * 查询打印机信息列表
     *
     * @return 打印机信息列表的页面地址和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<Printer> printers = printerService.query4List();

        ModelAndView mav = new ModelAndView("admin/printer/list");

        mav.addObject("printers", printers);

        return mav;
    }

    /**
     * 路由到打印机添加页面
     *
     * @return 打印机添加页面地址
     */
    @RequestMapping("/add/route")
    public String routeAdd() {
        return "admin/printer/add";
    }
    /**
     * 保存添加新打印机
     *
     * @param printer 新打印机信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(Printer printer, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = printerService.saveNewPrinter(printer, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/printer/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/printer/add/route.action";
        }
    }

    /**
     * 删除打印机
     *
     * @param printerId 打印机Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */

    @RequestMapping("/delete/{printerId}")
    public String delete(@PathVariable String printerId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = printerService.deletePrinter(printerId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/printer/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/printer/list.action";
        }
    }

    /**
     * 路由到打印机编辑页面
     *
     * @param printerId 打印机Id
     * @return 打印机编辑页面和相打印机信息
     */
    @RequestMapping("/edit/route/{printerId}")
    public ModelAndView routeEdit(@PathVariable String printerId ) {
         Printer printer = printerService.query4Edit(printerId);

        if (Optional.fromNullable(printer).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/printer/edit");

            mav.addObject("printer", printer);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/printer/list.action");
        }
    }
    /**
     * 保存打印机信息修改
     *
     * @param printer 打印机信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */

    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditPlaceDistinct(Printer printer, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update =printerService.updatePrinter(printer, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/printer/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/printer/edit/route/" + printer.getPrinterId() + ".action";
        }
    }
    /**
     * 验证打印机编号的唯一
     *
     * @param printer   打印机编号对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/printerNumber", method = RequestMethod.POST)
    public boolean PrinterNumberUnique(Printer printer) {
        String printerNumber = printer.getPrinterNumber();
        String hiddenPrinterNumber = printer.getHiddenPrinterNumber();

        return printerNumber.equalsIgnoreCase(hiddenPrinterNumber) ||
                printerService.query4PlacePrinterNumberUnique(printer.getPrinterNumber());
    }
    /**
     * 验证打印机Ip的唯一
     *
     * @param printer   打印机对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/printIp", method = RequestMethod.POST)
    public boolean PrinterIpUnique(Printer printer) {
        String printIp = printer.getPrintIp();
        String hiddenPrintIp = printer.getHiddenPrintIp();

        return printIp.equalsIgnoreCase(hiddenPrintIp) ||
                printerService.query4PrintIpUnique(printer.getPrintIp());
    }


}
