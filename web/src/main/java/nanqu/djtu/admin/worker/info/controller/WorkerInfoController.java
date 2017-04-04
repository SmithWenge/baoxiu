package nanqu.djtu.admin.worker.info.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.worker.info.service.WorkerInfoServiceI;
import nanqu.djtu.pojo.*;
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
 * @author zhangwenyue
 */
@Controller
@RequestMapping("/admin/worker/info")
public class WorkerInfoController {
    @Autowired
    private WorkerInfoServiceI workerInfoService;

    /**
     * 查询工人信息列表
     *
     * @return 工人信息列表的页面地址和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<WorkerInfo> workerInfos = workerInfoService.query4List();

        ModelAndView mav = new ModelAndView("admin/worker/info/list");

        mav.addObject("workerInfos", workerInfos);

        return mav;
    }

    /**
     * 路由到工人信息添加页面
     *
     * @return 工人信息添加页面地址
     */
    @RequestMapping("/add/route")
    public ModelAndView routeAdd() {
        List<RepairGroup> repairGroups = workerInfoService.repairGroupQuery4List();
        List<WorkerType> workerTypes = workerInfoService.workerTypeQuery4List();

        ModelAndView modelAndView = new ModelAndView("admin/worker/info/add");
        modelAndView.addObject("repairGroups",repairGroups);
        modelAndView.addObject("workerTypes",workerTypes);
        return  modelAndView;
    }

    /**
     * 保存添加新工人信息
     *
     * @param info 新工人信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(WorkerInfo info, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = workerInfoService.saveNewWorkerInfo(info, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/worker/info/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/worker/info/add/route.action";
        }
    }

    /**
     * 删除工人
     *
     * @param userId 校区Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{userId}")
    public String delete(@PathVariable String userId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = workerInfoService.deleteWorkerINfo(userId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/worker/info/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/worker/info/list.action";
        }
    }

    /**
     * 路由到工人编辑编辑页面
     *
     * @param userId 校区Id
     * @return 工人编辑页面和相工人信息
     */
    @RequestMapping("/edit/route/{userId}")
    public ModelAndView routeEdit(@PathVariable String userId) {
        WorkerInfo infos = workerInfoService.query4Edit(userId);

        if (Optional.fromNullable(infos).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/worker/info/edit");

            mav.addObject("infos", infos);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/worker/info/list.action");
        }
    }
    /**
     * 保存工人信息修改
     *
     * @param info 工人信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditPlaceDistinct(WorkerInfo info, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = workerInfoService.updateWorkerInfo(info, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/worker/info/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/worker/info/edit/route/" + info.getUserId() + ".action";
        }
    }


}
