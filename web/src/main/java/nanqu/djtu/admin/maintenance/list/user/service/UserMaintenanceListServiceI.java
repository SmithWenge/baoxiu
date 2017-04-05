package nanqu.djtu.admin.maintenance.list.user.service;

import nanqu.djtu.pojo.*;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public interface UserMaintenanceListServiceI {
    List<PlaceDistinct> query4ListPlaceDistinct();

    List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId);

    List<EquipmentSet> querySets();

    List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId);

    List<Equipment> queryEquipment();

    boolean saveNewMaintenanceList(MaintenanceList maintenance);

    MaintenanceList selectRepairGroupId(MaintenanceList maintenance);
}
