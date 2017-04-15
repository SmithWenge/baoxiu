package nanqu.djtu.app.worker.controller;

import com.google.common.base.Optional;
import nanqu.djtu.app.worker.service.WorkerServiceI;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.utils.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("/routeList")
    public String routeList() {
        return "app/worker/doingList";
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

            return "redirect:/app/worker/routeList.action";
        } else {
            WorkerInfo info = workerService.workerLogin(workerInfo.getWorkerTel(), workerInfo.getWorkerPass());
            Optional<WorkerInfo> optional2 = Optional.fromNullable(info);
            if (optional2.isPresent()) {
                session.setAttribute(ConstantFields.SESSION_WORKER_LOGIN_KEY,optional2);

                return "redirect:/app/worker/routeList.action";
            } else {

                return "redirect:/app/worker/index.action";
            }
        }
    }
}
