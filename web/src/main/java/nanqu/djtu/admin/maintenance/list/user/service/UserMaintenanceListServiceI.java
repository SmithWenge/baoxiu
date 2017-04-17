package nanqu.djtu.admin.maintenance.list.user.service;

import nanqu.djtu.pojo.*;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public interface UserMaintenanceListServiceI {
    List<PlaceDistinct> query4ListPlaceDistinct();
    List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId);
    List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId);
    boolean saveNewMaintenanceList(MaintenanceList maintenance);
    List<Equipment> queryEquipmentByRoomId(String equipmentId);
}
