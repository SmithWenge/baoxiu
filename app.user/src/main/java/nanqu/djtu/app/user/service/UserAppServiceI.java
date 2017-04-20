package nanqu.djtu.app.user.service;

import nanqu.djtu.pojo.*;

import java.util.List;

public interface UserAppServiceI {

    List<PlaceDistinct> query4ListPlaceDistinct();

    List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId);


    List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId);

   MaintenanceList saveNewMaintenanceList(MaintenanceList maintenance);


    List<Equipment> queryPlaceRoomByRoomId(String roomId);

    MaintenanceList queryAllName(MaintenanceList maintenanceList);

    List<MaintenanceList> queryMaintenanceListByTel(String userTel);

    MaintenanceList queryOneMaintenance(String listNumber);

    int sumOfMaintenance();

    String queryListStateTime(String listNumber);

    List<MaintenanceList> queryAllState(String listNumber);

    List<PlaceDistinct> queryDistincts();

    int queryEachDistinctMaintenanceNumber(String distinctId);
}
