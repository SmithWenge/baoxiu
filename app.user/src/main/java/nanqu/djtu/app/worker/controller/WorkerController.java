package nanqu.djtu.app.worker.controller;

import com.google.common.base.Optional;
import nanqu.djtu.app.worker.service.WorkerServiceI;
import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.utils.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/app/worker")
public class WorkerController {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerController.class);
    @Autowired
    private WorkerServiceI workerService;


    /**
     * 路由到维修工人登陆页面
     *
     * @return 工人登陆地址
     */
    @RequestMapping("/index")
    public String index() {
        return "app/worker/index";
    }

    /**
     * 路由到在办工作页面
     * @param session
     * @return 在办工作页面
     */
    @RequestMapping("/routeDoingList")
    public ModelAndView routeDoingList( HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo)session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/home/index");

            List<MaintenanceList> lists = workerService.queryDoingMaintenanceListByState(sessionInfo.getUserId());
            int todoMaintenanceNumber  = workerService.queryTodoMaintenanceNumber(sessionInfo.getUserId());
            int allDoMaintenanceSum = workerService.queryallDoMaintenanceSum(sessionInfo.getUserId());

            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setWaitToDoMaintenanceSum(todoMaintenanceNumber);
            maintenanceList.setAllDoMaintenanceSum(allDoMaintenanceSum);

            mav.addObject("lists", lists);
            mav.addObject("maintenanceList",maintenanceList);

            return mav;
        } else {
            return new ModelAndView("app/worker/index");
        }
    }

    /**
     * 路由到待办工作页面
     * @param session
     * @return 待办工作页面
     */
    @RequestMapping("/routeWaitingList")
    public ModelAndView routeWaitingList( HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/accept/todo");

            List<MaintenanceList> lists = workerService.queryWaitingMaintenanceList(sessionInfo.getUserId());
            int onDoMaintenanceSum = workerService.queryOnDoMaintenanceSum(sessionInfo.getUserId());
            int allDoMaintenanceSum = workerService.queryallDoMaintenanceSum(sessionInfo.getUserId());

            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setOnDoMaintenanceSum(onDoMaintenanceSum);
            maintenanceList.setAllDoMaintenanceSum(allDoMaintenanceSum);

            mav.addObject("lists", lists);
            mav.addObject("maintenanceList", maintenanceList);

            return mav;
        } else {
            return new ModelAndView("app/worker/index");
        }
    }

    /**
     * 路由到最近操作页面
     * @param session
     * @return 最近操作页面
     */
    @RequestMapping("/routeLatestList")
    public ModelAndView routeLatestList( HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/all/index");
            List<MaintenanceList> lists = workerService.queryLatest35MaintenanceList(sessionInfo);

            int todoMaintenanceNumber  = workerService.queryTodoMaintenanceNumber(sessionInfo.getUserId());
            int allDoMaintenanceSum = workerService.queryallDoMaintenanceSum(sessionInfo.getUserId());
            int onDoMaintenanceSum = workerService.queryOnDoMaintenanceSum(sessionInfo.getUserId());

            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setOnDoMaintenanceSum(onDoMaintenanceSum);
            maintenanceList.setAllDoMaintenanceSum(allDoMaintenanceSum);
            maintenanceList.setWaitToDoMaintenanceSum(todoMaintenanceNumber);

            mav.addObject("lists", lists);
            mav.addObject("maintenanceList",maintenanceList);

            return mav;
        } else {
            return new ModelAndView("app/worker/index");
        }
    }

    /**
     * 工人登陆后查看今天待办的工作
     *
     * @param workerInfo 工人登陆信息
     * @return 包含待办记录的列表信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String workerLogin(WorkerInfo workerInfo,HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo)session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {

            return "redirect:/app/worker/routeDoingList.action";
        } else {
            WorkerInfo info = workerService.workerLogin(workerInfo.getWorkerTel(), workerInfo.getWorkerPass());
            Optional<WorkerInfo> optional2 = Optional.fromNullable(info);
            if (optional2.isPresent()) {
                session.setAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY,info);

                return "redirect:/app/worker/routeDoingList.action";
            } else {

                return "redirect:/app/worker/index.action";
            }
        }
    }

    /**
     * 路由到查看详情页面
     *
     * @param listNumber 位置Id
     * @return 位置编辑页面和相位置信息
     */
    @RequestMapping("/routeDetails/{listNumber}")
    public ModelAndView routeDetail(@PathVariable String listNumber,HttpSession session) {
        WorkerInfo workerInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(workerInfo);

        if (optional.isPresent()) {
            MaintenanceList list = workerService.query4details(listNumber);
            if (Optional.fromNullable(list).isPresent()) {
                ModelAndView mav = new ModelAndView();
                if (list.getListState() != 4) {
                    mav.setViewName("app/worker/accept/index");
                } else {
                    mav.setViewName("app/worker/all/details");
                }
                int todoMaintenanceNumber  = workerService.queryTodoMaintenanceNumber(workerInfo.getUserId());
                int onDoMaintenanceSum = workerService.queryOnDoMaintenanceSum(workerInfo.getUserId());
                int allDoMaintenanceSum = workerService.queryallDoMaintenanceSum(workerInfo.getUserId());
                MaintenanceList maintenanceList = new MaintenanceList();
                maintenanceList.setOnDoMaintenanceSum(onDoMaintenanceSum);
                maintenanceList.setWaitToDoMaintenanceSum(todoMaintenanceNumber);
                maintenanceList.setAllDoMaintenanceSum(allDoMaintenanceSum);

                mav.addObject("list", list);
                mav.addObject("maintenanceList",maintenanceList);

                return mav;
            } else {

                return new ModelAndView("redirect:/app/worker/routeDoingList.action");
            }
        } else {
            return  new ModelAndView("redirect:/app/worker/index.action");
        }
    }

    /**
     *  编辑状态
     * @param list
     * @param session
     * @return 在办工作页面
     */
    @RequestMapping("/edit")
    public String edit(MaintenanceList list, HttpSession session) {
        WorkerInfo workerInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(workerInfo);

        if (optional.isPresent()) {
            Boolean update = workerService.edit(list, workerInfo);
            if (update) {
                return "redirect:/app/worker/routeDoingList.action";
            } else {
                return "redirect:/app/worker/routeDetails/" + list.getListNumber() + ".action";
            }
        } else {
            return "redirect:/app/worker/index.action";
        }
    }

    /**
     * 路由到查看工人信息页面
     *
     * @param
     * @return 工人信息页面
     */
    @RequestMapping("/routeWorkerInfo")
    public ModelAndView routeDetail(HttpSession session) {
        WorkerInfo workerInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(workerInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/userInfo");
            WorkerInfo info = workerService.queryWorkerInfo(workerInfo.getUserId());
            int todoMaintenanceNumber  = workerService.queryTodoMaintenanceNumber(workerInfo.getUserId());
            int allDoMaintenanceSum = workerService.queryallDoMaintenanceSum(workerInfo.getUserId());
            int onDoMaintenanceSum = workerService.queryOnDoMaintenanceSum(workerInfo.getUserId());
            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setOnDoMaintenanceSum(onDoMaintenanceSum);
            maintenanceList.setAllDoMaintenanceSum(allDoMaintenanceSum);
            maintenanceList.setWaitToDoMaintenanceSum(todoMaintenanceNumber);

            mav.addObject("maintenanceList",maintenanceList);

            mav.addObject("info", info);

            return mav;
        } else {
            return  new ModelAndView("redirect:/app/worker/index.action");
        }
    }

    /**
     * 更改工人密码
     *
     * @param session
     * @return 工人信息页面
     */
    @RequestMapping("/changePass")
    public String changePass(WorkerInfo info , HttpSession session,  RedirectAttributes redirectAttributes) {
        WorkerInfo workerInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(workerInfo);

        if (optional.isPresent()) {
            WorkerInfo loginInfo = workerService.workerLogin(info.getWorkerTel(), info.getWorkerPass());
            Optional<WorkerInfo> optional2 = Optional.fromNullable(loginInfo);
            if (optional2.isPresent()) {
                info.setUserId(loginInfo.getUserId());
                Boolean change = workerService.changePass(info,workerInfo);
                if (change) {
                    return "redirect:/app/worker/index.action";
                } else {
                    return "redirect:/app/worker/routeWorkerInfo.action";
                }
            } else {
                redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE, "密码不正确，请重新输入");

                return "redirect:/app/worker/routeWorkerInfo.action";
            }
        } else {

            return  "redirect:/app/worker/index.action";
        }
    }

    /**
     * 退出登录页面
     * @param session
     * @return 登录页面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("ConstantFields.SESSION_WORKER_LOGIN_KEY");

        return "app/worker/index";
    }
}
