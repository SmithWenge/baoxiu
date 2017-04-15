package nanqu.djtu.app.user.controller;

import nanqu.djtu.app.user.service.UserAppServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/user")
public class UserAppController {
    @Autowired
    private UserAppServiceI userAppService;

    /**
     * 路由到用户使用手机首页
     *
     * @return 手机首页地址
     */
    @RequestMapping("/index")
    public String userAppIndex() {
        return "app/user/index";
    }
}