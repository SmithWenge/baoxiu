package nanqu.djtu.api.room.rest;

import nanqu.djtu.api.room.service.RoomApiServiceI;
import nanqu.djtu.pojo.PlaceRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/room")
public class RestRoomApiController {
    @Autowired
    private RoomApiServiceI roomApiService;

    /**
     * 查询这个地点下的位置
     *
     * @param room 包含地点Id的对象
     * @return 返回地点下的位置List's json
     */
    @RequestMapping("/get/building")
    public Map<String, List<PlaceRoom>> getRoomByBuildingId(PlaceRoom room) {
        Map<String, List<PlaceRoom>> mapData = new HashMap<>();

        List<PlaceRoom> rooms = roomApiService.queryRoomByBuildingId(room.getBuildingId());

        mapData.put("rooms", rooms);

        return mapData;
    }
}
