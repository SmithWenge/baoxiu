package nanqu.djtu.admin.worker.type.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.place.distinct.service.PlaceDistinctServiceI;
import nanqu.djtu.admin.worker.type.service.WorkerTypeServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.WorkerType;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * author：yangguangze
 */
@Controller
@RequestMapping("/admin/worker/type")
public class WorkerTypeController {
    @Autowired
    private WorkerTypeServiceI workerTypeService;

    /**
     * 查询工种信息列表
     *
     * @return 工种信息列表的页面地址和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<WorkerType> types = workerTypeService.query4List();

        ModelAndView mav = new ModelAndView("admin/worker/workertype/list");

        mav.addObject("types", types);

        return mav;

    }

    /**
     * 路由到工种添加页面
     *
     * @return 工种添加页面地址
     */
    @RequestMapping("/add/route")
    public String routeAdd() {
        return "admin/worker/workertype/add";
    }
    /**
     * 保存添加新工种
     *
     * @param distinct 新工种信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save( WorkerType distinct, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = workerTypeService.saveWorkerType(distinct, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/worker/workertype/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/worker/workertype/add/route.action";
        }
    }
    /**
     * 删除工种
     *
     * @param typeId 工种Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{typeId}")
    public String delete(@PathVariable String typeId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = workerTypeService.deleteDistinct(typeId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/worker/workerType/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/worker/workerType/list.action";
        }
    }
    /**
     * 路由到工种编辑页面
     *
     * @param typeId 工种Id
     * @return 工种编辑页面和相校区信息
     */
    @RequestMapping("/edit/route/{workerTypeId}")
    public ModelAndView routeEdit(@PathVariable String  typeId ) {
       WorkerType distinct = workerTypeService.query4Edit(typeId);

        if (Optional.fromNullable(distinct).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/place/distinct/edit");

            mav.addObject("distinct", distinct);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/place/distinct/list.action");
        }
    }
    /**
     * 保存工种信息修改
     *
     * @param distinct 工种信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditWorkerType(WorkerType distinct, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = workerTypeService.updateWorkerType(distinct, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/worker/workerType/distinct/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/worker/workerType/distinct/edit/route/" + distinct.getTypeId() + ".action";
        }
    }


}
