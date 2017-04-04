package nanqu.djtu.api.equipment.rest;

import nanqu.djtu.api.equipment.service.EquipmentApiServiceI;
import nanqu.djtu.pojo.Equipment;
import nanqu.djtu.pojo.PlaceRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/equipment")
public class RestEquipmentApiController {
    @Autowired
    private EquipmentApiServiceI equipmentApiService;

    /**
     * 查询这个位置下的所有设备
     *
     * @param room 包含位置Id的对象
     * @return 这个位置下的所有设备的List's json
     */
    @RequestMapping("/get/room.action")
    public Map<String, List<Equipment>> getEquipmentsByRoomId(PlaceRoom room) {
        Map<String, List<Equipment>> mapData = new HashMap<>();

        List<Equipment> equipments = equipmentApiService.queryEquipmentByRoomId(room.getRoomId());

        mapData.put("equipments", equipments);

        return mapData;
    }
}
