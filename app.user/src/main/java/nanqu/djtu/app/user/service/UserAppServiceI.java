package nanqu.djtu.app.user.service;

import nanqu.djtu.pojo.*;

import java.util.List;

public interface UserAppServiceI {

    List<PlaceDistinct> query4ListPlaceDistinct();

    List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId);


    List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId);

   MaintenanceList saveNewMaintenanceList(MaintenanceList maintenance);


    List<Equipment> queryPlaceRoomByRoomId(String roomId);

    MaintenanceList selectAllName(MaintenanceList maintenanceList);

    List<MaintenanceList> selectMaintenanceListByTel(String userTel);

    MaintenanceList selectOneMaintenance(String listNumber);

    int sumOfMaintenance();

    String selectListStateTime(String listNumber);

    List<MaintenanceList> selectAllState(String listNumber);
}
