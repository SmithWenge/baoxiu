package nanqu.djtu.admin.maintenance.list.manage.controller;

import nanqu.djtu.admin.maintenance.list.manage.service.MaintenanceListServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/maintenance/list/manage")
public class MaintenanceListController {

    @Autowired
    private MaintenanceListServiceI maintenanceListService;

    /**
     * 位置首页
     *
     * @return 保修单管理首页地址
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/maintenance/list/manage/list";
    }

    /**
     * 查询保修单信息列表
     *
     * @return 保修单信息列表的页面地址和数据
     */
    @ResponseBody
    @RequestMapping("/route/page")
    public Map<String, Object> listFirstPage(MaintenanceList list,@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {

        Map<String, Object> mapData = new HashMap<>();

        Page<MaintenanceList> page = maintenanceListService.query4Page(list, pageable);
        mapData.put(ConstantFields.PAGE_KEY, page);

        mapData.put("condition",list);

        List<MaintenanceList> distincts = maintenanceListService.queryDistincts();
        mapData.put("distincts", distincts);

        return mapData;
    }

    /**
     * 二级联动根据校区查询地点
     * @param distinct
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buildings", method = RequestMethod.POST)
    public Map<String, List<PlaceBuilding>> buildings(PlaceDistinct distinct) {
        Map<String, List<PlaceBuilding>> map = new HashMap<>();
        map.put("buildings", maintenanceListService.queryBuildingsWithDistinctId(distinct.getDistinctId()));

        return map;
    }

    /**
     * 地点查询位置的二级联动
     *
     * @param building 地点对象
     * @return 这个地点下位置的List
     */
    @ResponseBody
    @RequestMapping("/rooms")
    public Map<String, List<PlaceRoom>> listRoomWithBuildingId(PlaceBuilding building) {
        Map<String, List<PlaceRoom>> mapData = new HashMap<>();

        List<PlaceRoom> rooms = maintenanceListService.queryRoomWithBuildingId(building.getBuildingId());

        mapData.put("rooms", rooms);

        return mapData;
    }

    /**
     * 位置或地点查询设备组的二级联动
     *
     * @param room 位置对象
     * @return 这个位置下设备组的List
     */
    @ResponseBody
    @RequestMapping("/sets")
    public Map<String, List<Equipment>> listRoomEquipmentSets(PlaceRoom room) {
        Map<String, List<Equipment>> mapData = new HashMap<>();

        List<Equipment> equipments = maintenanceListService.queryEquipmentsWithRoom(room);

        mapData.put("equipments", equipments);

        return mapData;
    }
}
