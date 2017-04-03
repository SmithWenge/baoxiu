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

    /**
     * 查询所有的设备组
     *
     * @return 返回设备组对象List
     */
    List<EquipmentSet> selectAllEquipmentSets();

    /**
     * 查询所有的维修小组
     *
     * @return 返回维修小组对象List
     */
    List<RepairGroup> selectAllRepairGroup();

    /**
     * 查询判断设备编号的唯一
     *
     * @param equipmentNumber 设备编号
     * @return 如果不重复返回true, else false
     */
    boolean selectUniqueEquipmentNumber(String equipmentNumber);

    /**
     * 保存新添加的设备
     *
     * @param equipment 设备对象
     * @return 添加成功返回true, else falase
     */
    boolean insertNewEquipment(Equipment equipment);

    /**
     * 添加设备与设备组关联表数据
     *
     * @param equipment 包含两个Id的设备对象
     * @return 如果添加成功返回true, else false
     */
    boolean insertNewEquipmentWithSet(Equipment equipment);

    /**
     * 通过地点Id查询这个地点下的setId
     *
     * @param buildingId 地点Id
     * @return 如果有setId返回setId, else null
     */
    String selectSetIdWithBuilding(String buildingId);

    /**
     * 通过位置Id查询这个位置下的setId
     *
     * @param roomId 位置Id
     * @return 如果有setId返回setId, else null
     */
    String selectSetIdWithRoom(String roomId);

    /**
     * 查询编辑的设备对象
     *
     * @param equipmentId 设备Id
     * @return 如果存在返回对象, else null
     */
    Equipment select4Edit(String equipmentId);
}
