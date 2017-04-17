package nanqu.djtu.app.user.controller;

import nanqu.djtu.app.user.service.UserAppServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 用户添加保修单
     * @return 用户添加页面地址
     */
    @RequestMapping("/maintenance/add/router")
    public ModelAndView list() {
        List<PlaceDistinct> placeDistincts = userAppService.query4ListPlaceDistinct();
        ModelAndView mav = new ModelAndView("app/user/addFirstStep");
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
        map.put("buildings", userAppService.queryBuildingsByDistinctId(placeDistinct.getDistinctId()));

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
        map.put("rooms", userAppService.queryPlaceRoomByBuildingId(placeBuilding.getBuildingId()));
        return map;
    }
    /**
     * 二级联动查设备信息
     * @param room
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/equipment", method = RequestMethod.POST)
    public Map<String, List<Equipment>> equipments(PlaceRoom room) {
        Map<String, List<Equipment>> map = new HashMap<>();
        map.put("equipments", userAppService.queryPlaceRoomByRoomId(room.getRoomId()));
        return map;
    }

    /**
     * 路由到手机添加页面
     * @param maintenanceList
     * @return
     */
    @RequestMapping(value = "/add/tel/router", method = RequestMethod.POST)
    public ModelAndView addTel(MaintenanceList maintenanceList){
        MaintenanceList list = userAppService.selectAllName(maintenanceList);
        maintenanceList.setDistinctName(list.getDistinctName());
        maintenanceList.setBuildingName(list.getBuildingName());
        maintenanceList.setRoomName(list.getRoomName());
        maintenanceList.setEquipmentName(list.getEquipmentName());
        ModelAndView modelAndView = new ModelAndView("app/user/addTwoStep");
        modelAndView.addObject("maintenance", maintenanceList);
        return modelAndView;
    }

    /**
     * 保修单添加
     * @param
     * @return true else false
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public ModelAndView addNewMaintenanceList(MaintenanceList maintenanceList, RedirectAttributes redirectAttributes) {
        if(maintenanceList.getUserTel() == null){
            ModelAndView modelAndView = new ModelAndView("app/user/addFirstStep");

            return modelAndView;
        }

        MaintenanceList  list = userAppService.saveNewMaintenanceList(maintenanceList);

        if (list == null){
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);
            ModelAndView modelAndView = new ModelAndView("app/user/addFirstStep");

            return modelAndView;
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);
            String listTime = userAppService.selectListStateTime(list.getListNumber());
            int sum = userAppService.sumOfMaintenance();
            maintenanceList.setSum(sum);
            maintenanceList.setListNumber(list.getListNumber());
            maintenanceList.setListstatetime(listTime);
            ModelAndView modelAndView = new ModelAndView("app/user/done");
            modelAndView.addObject("maintenance",maintenanceList);

            return modelAndView;
        }
    }

    /**
     * 根据保修单号查保修单
     * @param listNumber
     * @return
     */
    @RequestMapping(value = "/select/oneMaintenance/{listNumber}")
    public  ModelAndView selectOneMaintenance(@PathVariable String listNumber){
        MaintenanceList maintenanceList = userAppService.selectOneMaintenance(listNumber);
        List<MaintenanceList> allStates = userAppService.selectAllState(listNumber);
        MaintenanceList list = userAppService.selectAllName(maintenanceList);

        maintenanceList.setDistinctName(list.getDistinctName());
        maintenanceList.setBuildingName(list.getBuildingName());
        maintenanceList.setRoomName(list.getRoomName());
        maintenanceList.setEquipmentName(list.getEquipmentName());

        ModelAndView modelAndView = new ModelAndView("app/user/details");
        modelAndView.addObject("allStates", allStates);
        modelAndView.addObject("maintenanceList", maintenanceList);

        return modelAndView;
    }

    /**
     * 路由到通过电话查保修单的页面
     * @return
     */
    @RequestMapping(value = "/my/maintenance/router")
    public String myMaintenanceRouter(){
        return "app/user/track";
    }

    /**
     * 根据电话查所有的保修单
     * @param maintenanceList
     * @return
     */
    @RequestMapping(value = "/select/all/maintenance", method = RequestMethod.POST)
    public  ModelAndView selectMaintenance(MaintenanceList maintenanceList){
        List<MaintenanceList> maintenanceLists =userAppService.selectMaintenanceListByTel(maintenanceList.getUserTel());

        ModelAndView modelAndView = new ModelAndView("app/user/repairList");
        modelAndView.addObject("maintenanceLists",maintenanceLists);
        modelAndView.addObject("userTel",maintenanceList);

        return modelAndView;
    }

    @RequestMapping(value = "/turn/repairList/router/{userTel}")
    public ModelAndView turnToRepairList(@PathVariable String userTel){
        ModelAndView modelAndView = new ModelAndView("app/user/repairList");

        List<MaintenanceList> maintenanceLists = userAppService.selectMaintenanceListByTel(userTel);
        modelAndView.addObject("maintenanceLists",maintenanceLists);

        return modelAndView;
    }


}