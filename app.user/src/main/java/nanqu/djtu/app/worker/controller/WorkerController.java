package nanqu.djtu.app.worker.controller;

import nanqu.djtu.app.worker.service.WorkerServiceI;
import nanqu.djtu.pojo.WorkerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/worker")
public class WorkerController {
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
     * 工人登陆后查看今天待办的工作
     *
     * @param workerInfo 工人登陆信息
     * @return 包含待办记录的列表信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView workerLogin(WorkerInfo workerInfo) {
        return new ModelAndView("app/worker/doingList");
    }
}
