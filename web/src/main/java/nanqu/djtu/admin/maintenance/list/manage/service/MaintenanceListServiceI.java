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
    List<MaintenanceList> queryGroups();
    MaintenanceList query4details(String listNumber);
<<<<<<< HEAD
    boolean updatestate(String listNumber, AdminUser user);
    boolean done(String listNumber, AdminUser user);
=======
    Boolean editMaintenanceList(MaintenanceList list,AdminUser user);
>>>>>>> 20f506c83ee04d3d1bb0dbb24899fe1da86ee3f8
}
