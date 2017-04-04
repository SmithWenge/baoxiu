package nanqu.djtu.admin.maintenance.list.user.controller;


import nanqu.djtu.admin.maintenance.list.user.service.UserMaintenanceListServiceI;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/4.
 */
@Controller
@RequestMapping("/user/maintenance/list")
public class UserMaintenanceListController {
    @Autowired
    private UserMaintenanceListServiceI maintenanceListService;
    /**
     * 用户添加保修单
     *
     * @return 用户添加页面地址
     */
    @RequestMapping("/router")
    public ModelAndView list() {
        List<PlaceDistinct> placeDistincts = maintenanceListService.query4ListPlaceDistinct();

        ModelAndView mav = new ModelAndView("user/maintenance/list/add");

        mav.addObject("placeDistincts", placeDistincts);

        return mav;
    }
    /**
     * 二级联动根据校区查询地点
     * @param placeDistinct
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buildings", method = RequestMethod.POST)
    public Map<String, List<PlaceBuilding>> buildings(PlaceDistinct placeDistinct) {
        Map<String, List<PlaceBuilding>> map = new HashMap<>();
        map.put("buildings", maintenanceListService.queryBuildingsByDistinctId(placeDistinct.getDistinctId()));

        return map;
    }

}
