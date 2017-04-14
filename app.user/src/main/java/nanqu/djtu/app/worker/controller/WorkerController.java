package nanqu.djtu.app.worker.controller;

import nanqu.djtu.app.worker.service.WorkerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
