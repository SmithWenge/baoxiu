package nanqu.djtu.admin.repairgroup.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.place.building.service.PlaceBuildingServiceI;
import nanqu.djtu.admin.repairgroup.service.RepairGroupServiceI;
import nanqu.djtu.pojo.*;
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
import java.util.List;

/**
 * @author yangguangze
 */
@Controller
@RequestMapping("/admin/repairgroup")
public class RepairGroupController {
    @Autowired
    private RepairGroupServiceI repairGroupService;
    /**
     * 查询维修小组信息列表
     *
     * @return 维修小组信息列表的页面地址和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<RepairGroup> groups = repairGroupService.query4List();

        ModelAndView mav = new ModelAndView("admin/repairgroup/list");

        mav.addObject("groups", groups);

        return mav;

    }
    /**
     * 路由到维修小组添加页面
     *
     * @return 维修小组添加页面地址
     */
    @RequestMapping("/add/route")
    public ModelAndView routeAdd() {
        List<Printer> printers =repairGroupService.printerQuery4List();

        ModelAndView modelAndView = new ModelAndView("admin/repairgroup/add");
        modelAndView.addObject("printers",printers);
        return  modelAndView;
    }
    /**
     * 保存添加新维修小组
     *
     * @param group 新维修小组信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(RepairGroup group, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = repairGroupService.saveNewRepairGroup(group, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/repairgroup/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/repairgroup/add/route.action";
        }
    }
    /**
     * 删除维修小组
     *
     * @param repairGroupId 维修小组Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{repairGroupId}")
    public String delete(@PathVariable String repairGroupId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = repairGroupService.deleteDistinct(repairGroupId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/repairgroup/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/repairgroup/list.action";
        }
    }
    /**
     * 路由到维修小组编辑页面
     *
     * @param repairGroupId 校区Id
     * @return 校区编辑页面和相校区信息
     */
    @RequestMapping("/edit/route/{repairGroupId}")
    public ModelAndView routeEdit(@PathVariable String repairGroupId) {
        RepairGroup group = repairGroupService.query4Edit(repairGroupId);
        List<Printer> printers =repairGroupService.printerQuery4List();
        if (Optional.fromNullable(group).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/repairgroup/edit");
            mav.addObject("printers",printers);
            mav.addObject("group", group);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/repairgroup/list.action");
        }
    }
    /**
     * 保存维修小组信息修改
     *
     * @param group 维修小组信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditRepairGroup(RepairGroup group, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = repairGroupService.updateRepairGroup(group, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/repairgroup/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/repairgroup/edit/route/" + group.getRepairGroupId() + ".action";
        }
    }
    /**
     * 验证维修小组编号的唯一
     *
     * @param group 维修小组编号的维修小组对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/groupNumber", method = RequestMethod.POST)
    public boolean repairNumberUnique(RepairGroup group) {
        String groupNumber = group.getGroupNumber();
        String hiddenGroupNumber = group.getHiddenGroupNumber();

        return groupNumber.equalsIgnoreCase(hiddenGroupNumber) ||
                repairGroupService.query4RepairGroupNumberUnique(group.getGroupNumber());
    }



}
