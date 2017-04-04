package nanqu.djtu.admin.maintenance.list.manage.service;

import nanqu.djtu.pojo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MaintenanceListServiceI {

    Page<MaintenanceList> query4Page(MaintenanceList list,Pageable pageable);
    List<MaintenanceList> queryDistincts();
    List<PlaceBuilding> queryBuildingsWithDistinctId(String distinctId);
    List<PlaceRoom> queryRoomWithBuildingId(String buildingId);
    List<Equipment> queryEquipmentsWithRoom(PlaceRoom room);
}
