package nanqu.djtu.admin.place.building.controller;

import nanqu.djtu.admin.place.building.service.PlaceBuildingServiceI;

import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author zhangwenyue
 */
@Controller
@RequestMapping("/admin/place/building")
public class PlaceBuildingController {
    @Autowired
    private PlaceBuildingServiceI placeBuildingService;

    /**
     *查询地点信息
     *
     * @return地点信息的列表和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<PlaceBuilding> building = placeBuildingService.query4List();

        ModelAndView mav = new ModelAndView("admin/place/building/list");

        mav.addObject("building",building);

        return  mav;
    }

    /**
     * 路由到地点添加页面
     *
     * @return 地点添加页面地址
     */
    @RequestMapping("/add/route")
    public ModelAndView routeAdd() {
        List<PlaceDistinct>  placeDistincts = placeBuildingService.placeDistinctQuery4List();



        ModelAndView modelAndView = new ModelAndView("admin/place/building/add");

        modelAndView.addObject("placeDistincts",placeDistincts);

        return modelAndView;
    }

}
