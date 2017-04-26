package nanqu.djtu.app.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/nanqu")
public class NanquIndexController {

    @RequestMapping("/index")
    public String homeIndex() {
        return "app/index/presence";
    }
}
