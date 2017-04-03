package nanqu.djtu.admin.equipment.service;

import nanqu.djtu.pojo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EquipmentServiceI {
    Page<Equipment> query4Page(Equipment equipment, Pageable pageable);
    List<PlaceDistinct> queryAllPlaceDistincts();
    List<PlaceBuilding> queryBuildingWithDistinctId(String distinctId);
    List<PlaceRoom> queryRoomWithBuildingId(String buildingId);
    List<EquipmentSet> querySetsWithRoomId(String roomId);
}
