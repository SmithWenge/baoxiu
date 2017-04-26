package nanqu.djtu.admin.home.controller;

import nanqu.djtu.admin.home.service.HomeServiceI;
import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/home")
public class HomeController {
    @Autowired
    private HomeServiceI homeService;

    @RequestMapping("/index")
    public ModelAndView homeIndex() {

        return new ModelAndView("admin/home/index");
    }

    /**
     * 查询保修单信息列表
     *
     * @return 报修单信息列表的页面地址和数据
     */
    @ResponseBody
    @RequestMapping("/route/page")
    public Map<String, Object> listFirstPage(MaintenanceList list, @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {

        Map<String, Object> mapData = new HashMap<>();

        Page<MaintenanceList> page = homeService.query4Page(list, pageable);
        mapData.put(ConstantFields.PAGE_KEY, page);

        mapData.put("condition", list);
        return mapData;
    }
}
