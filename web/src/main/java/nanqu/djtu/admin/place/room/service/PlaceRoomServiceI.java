package nanqu.djtu.admin.place.room.service;

import nanqu.djtu.pojo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PlaceRoomServiceI {
    Page<PlaceRoom> query4Page(PlaceRoom room,Pageable pageable);
    boolean saveNewPlaceRoom(PlaceRoom room, AdminUser user);
    boolean deleteRoom(String roomId, AdminUser user);
    PlaceRoom query4Edit(String roomId);
    boolean updatePlaceRoom(PlaceRoom room, AdminUser user);
    boolean query4PlaceRoomNumberUnique(String roomNumber);
    List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId);
    List<PlaceDistinct> queryDistincts();
    List<EquipmentSet> querySets();
    List<PlaceBuilding> queryBuildings4Edit();

}
