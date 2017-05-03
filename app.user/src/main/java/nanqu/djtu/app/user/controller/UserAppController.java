package nanqu.djtu.app.user.controller;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
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

import java.util.ArrayList;
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
     * 重定向到index页面
     * @return 用户手机首页
     */
    @RequestMapping("/redirect/index")
    public String userRedirectAppIndex() {
        return "redirect:/app/user/index.action";
    }

    /**
     * 用户添加保修单
     * @return 用户添加页面地址
     */
    @RequestMapping("/maintenance/add/router")
    public ModelAndView list() {
        List<PlaceDistinct> placeDistincts = userAppService.query4ListPlaceDistinct();

        ModelAndView mav = new ModelAndView("app/user/report/index");

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
    @RequestMapping(value = "/add/tel/router")
    public ModelAndView addTel(MaintenanceList maintenanceList){
        MaintenanceList list = userAppService.queryAllName(maintenanceList);

        maintenanceList.setDistinctName(list.getDistinctName());
        maintenanceList.setBuildingName(list.getBuildingName());
        maintenanceList.setRoomName(list.getRoomName());
        maintenanceList.setEquipmentName(list.getEquipmentName());

        ModelAndView modelAndView = new ModelAndView("app/user/report/confirm");

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
        if(Strings.isNullOrEmpty(maintenanceList.getUserTel())){
            MaintenanceList list = userAppService.queryAllName(maintenanceList);

            maintenanceList.setDistinctName(list.getDistinctName());
            maintenanceList.setBuildingName(list.getBuildingName());
            maintenanceList.setRoomName(list.getRoomName());
            maintenanceList.setEquipmentName(list.getEquipmentName());

            ModelAndView modelAndView = new ModelAndView("app/user/report/confirm");

            modelAndView.addObject("maintenance", maintenanceList);

            return modelAndView;
        }

        MaintenanceList  list = userAppService.saveNewMaintenanceList(maintenanceList);

        if (!Optional.fromNullable(list).isPresent()) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            MaintenanceList list1 = userAppService.queryAllName(maintenanceList);

            maintenanceList.setDistinctName(list1.getDistinctName());
            maintenanceList.setBuildingName(list1.getBuildingName());
            maintenanceList.setRoomName(list1.getRoomName());
            maintenanceList.setEquipmentName(list1.getEquipmentName());

            ModelAndView modelAndView = new ModelAndView("app/user/report/confirm");

            modelAndView.addObject("maintenance", maintenanceList);

            return modelAndView;
        } else {
            if(list.isMaintenanceExit()){
                redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

                String listTime = userAppService.queryListStateTime(list.getListNumber());
                int sum = userAppService.sumOfMaintenance();

                maintenanceList.setSum(sum);
                maintenanceList.setListNumber(list.getListNumber());
                maintenanceList.setListstatetime(listTime);

                ModelAndView modelAndView = new ModelAndView("app/user/report/undone");
                modelAndView.addObject("maintenance",maintenanceList);

                return modelAndView;
            }else{
                redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

                String listTime = userAppService.queryListStateTime(list.getListNumber());
                int sum = userAppService.sumOfMaintenance();

                maintenanceList.setSum(sum);
                maintenanceList.setListNumber(list.getListNumber());
                maintenanceList.setListstatetime(listTime);

                ModelAndView modelAndView = new ModelAndView("app/user/report/done");
                modelAndView.addObject("maintenance",maintenanceList);

                return modelAndView;
            }

        }
    }
    /**
     * 根据保修单号查保修单历史记录
     * @param listNumber
     * @return
     */
    @RequestMapping(value = "/select/oneMaintenance/{listNumber}")
    public  ModelAndView selectOneMaintenance(@PathVariable String listNumber){
        MaintenanceList maintenanceList = userAppService.queryOneMaintenance(listNumber);
        List<MaintenanceList> allStates = userAppService.queryAllState(listNumber);
        MaintenanceList list = userAppService.queryAllName(maintenanceList);

        String distinctName = list.getDistinctName();

        if (Strings.isNullOrEmpty(distinctName)) {
            maintenanceList.setDistinctName(ConstantFields.NO_DISTINCT_NAME);
        } else {
            maintenanceList.setDistinctName(distinctName);
        }

        String buildName = list.getBuildingName();
        if (Strings.isNullOrEmpty(buildName)) {
            maintenanceList.setBuildingName(ConstantFields.NO_BUILD_NAME);
        } else {
            maintenanceList.setBuildingName(buildName);
        }

        String roomName = list.getRoomName();
        if (Strings.isNullOrEmpty(roomName)) {
            maintenanceList.setRoomName(ConstantFields.NO_ROOM_NAME);
        } else {
            maintenanceList.setRoomName(roomName);
        }

        String equipmentName = list.getEquipmentName();
        if (Strings.isNullOrEmpty(equipmentName)) {
            maintenanceList.setEquipmentName(ConstantFields.NO_EQUIPMENT_NAME);
        } else {
            maintenanceList.setEquipmentName(equipmentName);
        }

        ModelAndView modelAndView = new ModelAndView("app/user/track/detail");
        modelAndView.addObject("allStates", allStates);
        modelAndView.addObject("maintenanceList", maintenanceList);

        return modelAndView;
    }
    /**
     * 路由到通过电话查保修单的页面
     * @return
     */
    @RequestMapping(value = "/my/maintenance/router")
    public ModelAndView myMaintenanceRouter(){
        List<PlaceDistinct> placeDistincts = userAppService.queryDistincts();
        ArrayList<MaintenanceList> maintenanceLists = new ArrayList<>();
        for(PlaceDistinct item: placeDistincts){
            int maitenanceSum = userAppService.queryEachDistinctMaintenanceNumber(item.getDistinctId());
            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setEachDistinctMaintenanceListNumber(maitenanceSum);
            maintenanceList.setDistinctName(item.getDistinctName());
            maintenanceLists.add(maintenanceList);
        }

        ModelAndView modelAndView = new ModelAndView("app/user/track/index");

        modelAndView.addObject("maintenanceLists",maintenanceLists);

        return modelAndView ;
    }

    /**
     * 根据电话查所有的保修单
     * @param maintenanceList
     * @return
     */
    @RequestMapping(value = "/select/all/maintenance", method = RequestMethod.POST)
    public  ModelAndView selectMaintenance(MaintenanceList maintenanceList){
        List<MaintenanceList> maintenanceLists =userAppService.queryMaintenanceListByTel(maintenanceList.getUserTel());

        ModelAndView modelAndView = new ModelAndView("app/user/track/list");
        modelAndView.addObject("maintenanceLists",maintenanceLists);
        modelAndView.addObject("userTel",maintenanceList);

        return modelAndView;
    }

//    /**
//     * 成功后到按钮路由到报修单列表页面
//     * @param userTel
//     * @return
//     */
//    @RequestMapping(value = "/select/button/all/maintenance/{userTel}")
//    public  ModelAndView selectAllMaintenance(@PathVariable String userTel){
//        List<MaintenanceList> maintenanceLists =userAppService.queryMaintenanceListByTel(userTel);
//        MaintenanceList maintenanceList = new MaintenanceList();
//        maintenanceList.setUserTel(userTel);
//        ModelAndView modelAndView = new ModelAndView("app/user/track/list");
//        modelAndView.addObject("maintenanceLists",maintenanceLists);
//        modelAndView.addObject("userTel",maintenanceList);
//
//        return modelAndView;
//    }
    /**
     * 路由到我的保修
     * @param userTel
     * @return
     */
    @RequestMapping(value = "/turn/repairList/router/{userTel}")
    public ModelAndView turnToRepairList(@PathVariable String userTel){
        ModelAndView modelAndView = new ModelAndView("app/user/track/list");

        MaintenanceList maintenanceList = new MaintenanceList();
        maintenanceList.setUserTel(userTel);
        List<MaintenanceList> maintenanceLists = userAppService.queryMaintenanceListByTel(userTel);
        modelAndView.addObject("maintenanceLists",maintenanceLists);
        modelAndView.addObject("userTel",maintenanceList);

        return modelAndView;
    }

}