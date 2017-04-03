package nanqu.djtu.admin.set.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.set.service.SetServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * pingfan 设备组
 */

@Controller
@RequestMapping("/admin/set")
public class SetController {
    @Autowired
    private SetServiceI setService;

    /**
     * 分页查询设备组
     *
     * @param equipmentSet 设备住信息
     * @param pageable 分页数据信息
     * @return 返回到设备组分页数据页面
     */
    @ResponseBody
    @RequestMapping("/route/page")
    public Map<String, Page<EquipmentSet>> pageSets(EquipmentSet equipmentSet,
                                                             @PageableDefault(ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Map<String, Page<EquipmentSet>> mapData = new HashMap<>();

        Page<EquipmentSet> page = setService.query4Page(equipmentSet, pageable);

        mapData.put(ConstantFields.PAGE_KEY, page);

        return mapData;
    }

    /**
     * 设备组首页
     *
     * @return 设备组首页地址
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/set/list";
    }

    /**
     * 路由到设备组添加页面
     *
     * @return 设备组添加页面地址
     */
    @RequestMapping("/add/route")
    public String routeAdd() {
        return "admin/set/add";
    }

    /**
     * 保存新添加的设备组信息
     *
     * @param set 添加的设备组信息对象
     * @param redirectAttributes 提示信息对象
     * @return 添加成功返回index, else add page
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String addNewSet(EquipmentSet set, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = setService.saveNewSet(set, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/set/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/set/add/route.action";
        }
    }

    /**
     * 路由到设备组编辑页面
     *
     * @param setId 设备组Id
     * @return 设备组编辑页面地址和设备组数据对象,对象不存在返回List
     */
    @RequestMapping("/edit/route/{setId}")
    public ModelAndView routeEditSet(@PathVariable String setId) {
        EquipmentSet set = setService.query4Edit(setId);

        if (Optional.fromNullable(set).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/set/edit");

            mav.addObject("set", set);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/set/index.action");
        }
    }

    /**
     * 保存设备组编辑的新数据对象
     *
     * @param set 编辑后的数据对象
     * @param redirectAttributes 提示信息
     * @return 如果成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEdit(EquipmentSet set, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = setService.updateSet(set, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/set/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/set/edit/route/" + set.getSetId() + ".action";
        }
    }

    /**
     * 删除设备组
     *
     * @param setId 设备组Id
     * @param redirectAttributes 提示信息
     * @return 返回list page
     */
    @RequestMapping(value = "/delete/{setId}", method = RequestMethod.POST)
    public String deleteSet(@PathVariable String setId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = setService.deleteSet(setId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/set/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/set/index.action";
        }
    }
}
