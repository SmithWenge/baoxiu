package nanqu.djtu.admin.place.distinct.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.place.distinct.service.PlaceDistinctServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author pingfan
 */

@Controller
@RequestMapping("/admin/place/distinct")
public class PlaceDistinctController {
    @Autowired
    private PlaceDistinctServiceI placeDistinctService;

    /**
     * 查询校区信息列表
     *
     * @return 校区信息列表的页面地址和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<PlaceDistinct> distincts = placeDistinctService.query4List();

        ModelAndView mav = new ModelAndView("admin/place/distinct/list");

        mav.addObject("distincts", distincts);

        return mav;
    }

    /**
     * 路由到校区添加页面
     *
     * @return 校区添加页面地址
     */
    @RequestMapping("/add/route")
    public String routeAdd() {
        return "admin/place/distinct/add";
    }

    /**
     * 保存添加新校区
     *
     * @param distinct 新校区信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(PlaceDistinct distinct, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = placeDistinctService.saveNewPlaceDistinct(distinct, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/distinct/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/distinct/add/route.action";
        }
    }

    /**
     * 删除校区
     *
     * @param distinctId 校区Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{distinctId}")
    public String delete(@PathVariable String distinctId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = placeDistinctService.deleteDistinct(distinctId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/distinct/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/distinct/list.action";
        }
    }

    /**
     * 路由到校区编辑页面
     *
     * @param distinctId 校区Id
     * @return 校区编辑页面和相校区信息
     */
    @RequestMapping("/edit/route/{distinctId}")
    public ModelAndView routeEdit(@PathVariable String distinctId) {
        PlaceDistinct distinct = placeDistinctService.query4Edit(distinctId);

        if (Optional.fromNullable(distinct).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/place/distinct/edit");

            mav.addObject("distinct", distinct);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/place/distinct/list.action");
        }
    }

    /**
     * 保存校区信息修改
     *
     * @param distinct 校区信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditPlaceDistinct(PlaceDistinct distinct, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = placeDistinctService.updatePlaceDistinct(distinct, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/distinct/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/distinct/edit/route/" + distinct.getDistinctId() + ".action";
        }
    }

    /**
     * 验证校区编号的唯一
     *
     * @param distinct 校区编号的校区对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/distinctNumber", method = RequestMethod.POST)
    public boolean distinctNumberUnique(PlaceDistinct distinct) {
        String distinctNumber = distinct.getDistinctNumber();
        String hiddenDistinctNumber = distinct.getHiddenDistinctNumber();

        return distinctNumber.equalsIgnoreCase(hiddenDistinctNumber) ||
                placeDistinctService.query4PlaceDistinctNumberUnique(distinct.getDistinctNumber());
    }
}
