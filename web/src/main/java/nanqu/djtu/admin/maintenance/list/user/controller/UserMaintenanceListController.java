package nanqu.djtu.admin.maintenance.list.user.controller;


import nanqu.djtu.admin.maintenance.list.user.service.UserMaintenanceListServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import nanqu.djtu.utils.PrinterUtils;
import nanqu.djtu.utils.PrintModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * @author zhangwenyue
 */
@Controller
@RequestMapping("/user/maintenance/list")
public class UserMaintenanceListController {
    @Autowired
    private UserMaintenanceListServiceI maintenanceListService;
    /**
     * 用户添加保修单
     * @return 用户添加页面地址
     */
    @RequestMapping("/router")
    public ModelAndView list() {
        List<PlaceDistinct> placeDistincts = maintenanceListService.query4ListPlaceDistinct();
        List<Equipment> equipments =  maintenanceListService.queryEquipment();

        ModelAndView mav = new ModelAndView("user/maintenance/list/add");

        mav.addObject("placeDistincts", placeDistincts);
        mav.addObject("equipments", equipments);

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
    /**
     * 二级联动查位置信息
     * @param placeBuilding
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/placeRoom", method = RequestMethod.POST)
    public Map<String, List<PlaceRoom>> rooms(PlaceBuilding placeBuilding) {
        Map<String, List<PlaceRoom>> map = new HashMap<>();
        map.put("rooms", maintenanceListService.queryPlaceRoomByBuildingId(placeBuilding.getBuildingId()));
        return map;
    }
    /**
     * 保修单添加
     * @param
     * @return true else false
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String addNewMaintenanceList(MaintenanceList maintenanceList, RedirectAttributes redirectAttributes) {

        boolean save = maintenanceListService.saveNewMaintenanceList(maintenanceList);

        if (save) {

            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/user/maintenance/list/success.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/user/maintenance/list/router.action";
        }
    }

    /**
     * 路由到添加成功页面
     * @return 页面地址
     */
    @RequestMapping("/success")
    public  ModelAndView success() {

        ModelAndView  modelAndView = new ModelAndView("user/maintenance/list/success");
        return  modelAndView;
    }


}
