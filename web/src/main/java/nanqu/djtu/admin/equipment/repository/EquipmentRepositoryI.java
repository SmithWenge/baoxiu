package nanqu.djtu.admin.equipment.repository;

import nanqu.djtu.pojo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EquipmentRepositoryI {
    /**
     * 分页查询设备信息
     *
     * @param equipment 查询条件的信息
     * @param pageable 分页信息
     * @return 分页查询的数据
     */
    Page<Equipment> select4Page(Equipment equipment, Pageable pageable);

    /**
     * 查询所有校区
     *
     * @return 所有校区数据
     */
    List<PlaceDistinct> selectAllPlaceDistincts();

    /**
     * 查询这个校区下的地点
     *
     * @param distinctId 校区Id
     * @return 校区的所有地点
     */
    List<PlaceBuilding> selectBuildingWithDistinctId(String distinctId);

    /**
     * 查询这个地点下的所有位置
     *
     * @param buildingId 地点Id
     * @return 这个地点下位置数据
     */
    List<PlaceRoom> selectRoomWithBuildingId(String buildingId);

    /**
     * 查询这个位置下的所有设备组
     *
     * @param roomId 位置Id
     * @return 这个位置下设备组数据
     */
    List<EquipmentSet> selectSetsWithRoomId(String roomId);
}
