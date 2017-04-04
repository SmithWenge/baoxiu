package nanqu.djtu.api.building.rest;

import nanqu.djtu.api.building.service.BuildingApiServiceI;
import nanqu.djtu.pojo.PlaceBuilding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pingfan
 */

@RestController
@RequestMapping("/api/v1/building")
public class RestBuildingApiController {
    @Autowired
    private BuildingApiServiceI buildingApiService;

    /**
     * 根据校区Id查询这个校区下的地点
     *
     * @param building 包含校区Id的对象
     * @return 这个校区下的地点List's json
     */
    @RequestMapping("/get/distinct")
    public Map<String, List<PlaceBuilding>> distinctBuilding(PlaceBuilding building) {
        Map<String, List<PlaceBuilding>> mapData = new HashMap<>();

        List<PlaceBuilding> buildings = buildingApiService.queryDistinctBuilding(building.getDistinctId());

        mapData.put("buildings", buildings);

        return mapData;
    }
}
