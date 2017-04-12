package nanqu.djtu.admin.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/home")
public class HomeController {
    @RequestMapping("/index")
    public ModelAndView homeIndex() {
        return new ModelAndView("admin/home/index");
    }

    @RequestMapping("/app/index")
    public ModelAndView appIndex() {
        return new ModelAndView("user/maintenance/list/add");
    }
}
