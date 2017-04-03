package nanqu.djtu.admin.equipment.controller;

import nanqu.djtu.admin.equipment.service.EquipmentServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentServiceI equipmentService;

    /**
     * 设备页面首页
     *
     * @return 设备页面地址
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/equipment/list";
    }

    /**
     * 分页查询设备
     *
     * @param equipment 设备查询条件的对象信息
     * @param pageable 分页信息
     * @return 返回分页信息包含条件信息和查询结果
     */
    @ResponseBody
    @RequestMapping("/route/page")
    public Map<String, Object> routePageEquipment(Equipment equipment,
                                                  @PageableDefault(ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Map<String, Object> mapData = new HashMap<>();

        Page<Equipment> page = equipmentService.query4Page(equipment, pageable);
        mapData.put(ConstantFields.PAGE_KEY, page);

        mapData.put("condition", equipment);

        List<PlaceDistinct> distincts = equipmentService.queryAllPlaceDistincts();
        mapData.put("distincts", distincts);

        return mapData;
    }

    /**
     * 校区查询地点的二级联动
     *
     * @param distinct 校区对象
     * @return 这个校区下地点的List
     */
    @ResponseBody
    @RequestMapping("/distinct/buildings")
    public Map<String, List<PlaceBuilding>> listBuildingWithDistinct(PlaceDistinct distinct) {
        Map<String, List<PlaceBuilding>> mapData = new HashMap<>();

        List<PlaceBuilding> buildings = equipmentService.queryBuildingWithDistinctId(distinct.getDistinctId());

        mapData.put("buildings", buildings);

        return mapData;
    }

    /**
     * 地点查询位置的二级联动
     *
     * @param building 地点对象
     * @return 这个地点下位置的List
     */
    @ResponseBody
    @RequestMapping("/building/rooms")
    public Map<String, List<PlaceRoom>> listRoomWithBuildingId(PlaceBuilding building) {
        Map<String, List<PlaceRoom>> mapData = new HashMap<>();

        List<PlaceRoom> rooms = equipmentService.queryRoomWithBuildingId(building.getBuildingId());

        mapData.put("rooms", rooms);

        return mapData;
    }

    /**
     * 位置查询设备组的二级联动
     *
     * @param placeRoom 位置对象
     * @return 这个位置下设备组的List
     */
    @ResponseBody
    @RequestMapping("/room/sets")
    public Map<String, List<EquipmentSet>> listRoomEquipmentSets(PlaceRoom placeRoom) {
        Map<String, List<EquipmentSet>> mapData = new HashMap<>();

        List<EquipmentSet> sets = equipmentService.querySetsWithRoomId(placeRoom.getRoomId());

        mapData.put("sets", sets);

        return mapData;
    }
}
