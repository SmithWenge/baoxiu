package nanqu.djtu.admin.place.building.repository;

import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

public interface PlaceBuildingRepositoryI {
    /**
     * 查询地点信息列表
     *
     * @return 所有未删除的信息
     */
    List<PlaceBuilding> select4List();

    /**
     * 查询校区信息
     *
     * @return 所有未删除的校区信息
     */
    List<PlaceDistinct> placeDistinctSelect4List();

    /**
     *查询设备组信息
     *
     * @return 所有未删除的设备组信息
     */
    List<EquipmentSet> equipmentSetSelect4List();

    /**
     *添加新的校区信息
     * @param building 地点对象
     * @return true false
     */
    boolean insertNewPlaceDistinct(PlaceBuilding building);

    /**
     * 地点编号唯一性判断
     * @param building 地点对象
     * @return true or false
     */
    boolean select4PlaceBuildingNumberUnique(PlaceBuilding building);

    /**
     * 逻辑删除地点
     * @param buildingId 地点Id
     * @return true or false
     */
    boolean deletePlaceBuilding(String buildingId);

    /**
     * 查询地点信息
     * @param buildingId 地点id
     * @return 地点对象
     */
    PlaceBuilding select4Edit(String buildingId);

    /**
     * 更新地点信息
     * @param placeBuilding 地点对象
     * @return true or false
     */
    boolean updatePlaceBuilding(PlaceBuilding placeBuilding);

    /**
     * 查询这个校区下地点的总数
     *
     * @param distinctId 校区Id
     * @return 返回这个校区下地点的总数
     */
    int select4BuildingCount(String distinctId);
}
