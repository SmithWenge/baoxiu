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

    @RequestMapping("/routeDoingList")
    public ModelAndView routeDoingList( HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo)session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/doingList");
            List<MaintenanceList> lists = workerService.queryMaintenanceListByState(2, sessionInfo);
            mav.addObject("lists", lists);

            return mav;
        } else {
            return new ModelAndView("app/worker/index");
        }
    }

    @RequestMapping("/routeWaitingList")
    public ModelAndView routeWaitingList( HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/waitingList");
            List<MaintenanceList> lists = workerService.queryMaintenanceListByState(1, sessionInfo);
            mav.addObject("lists", lists);

            return mav;
        } else {
            return new ModelAndView("app/worker/index");
        }
    }

    @RequestMapping("/routeLatestList")
    public ModelAndView routeLatestList( HttpSession session) {
        WorkerInfo sessionInfo = (WorkerInfo) session.getAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY);
        Optional<WorkerInfo> optional = Optional.fromNullable(sessionInfo);

        if (optional.isPresent()) {
            ModelAndView mav = new ModelAndView("app/worker/latestList");
            List<MaintenanceList> lists = workerService.queryLatest35MaintenanceList(sessionInfo);
            mav.addObject("lists", lists);

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
                ModelAndView mav = new ModelAndView("app/worker/details");
                mav.addObject("list", list);

                return mav;
            } else {

                return new ModelAndView("redirect:/app/worker/routeDoingList.action");
            }
        } else {
            return  new ModelAndView("redirect:/app/worker/index.action");
        }
    }

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
}
