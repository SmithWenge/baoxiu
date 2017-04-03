package nanqu.djtu.admin.place.building.repository;

import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public interface PlaceBuildingRepositoryI {
    /**
     * 查询地点信息列表
     *
     * @return 所有未删除的信息
     */
    List<PlaceBuilding> select4List();

    /**
     *
     * @return
     */
    List<PlaceDistinct> placeDistinctSelect4List();

    /**
     *
     * @return
     */

    List<EquipmentSet> equipmentSetSelect4List();

    /**
     *
     * @param building
     * @return
     */

    boolean insertNewPlaceDistinct(PlaceBuilding building);

    boolean select4PlaceBuildingNumberUnique(PlaceBuilding building);

    boolean deletePlaceBuilding(String buildingId);

    PlaceBuilding select4Edit(String buildingId);

    boolean updatePlaceBuilding(PlaceBuilding placeBuilding);

}
