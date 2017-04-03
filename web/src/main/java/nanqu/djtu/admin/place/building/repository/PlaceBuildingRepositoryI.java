package nanqu.djtu.admin.place.building.repository;

import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public interface PlaceBuildingRepositoryI {
    List<PlaceBuilding> select4List();

    List<PlaceDistinct> placeDistinctSelect4List();

    List<EquipmentSet> equipmentSetSelect4List();

    boolean insertNewPlaceDistinct(PlaceBuilding building);

    boolean select4PlaceBuildingNumberUnique(PlaceBuilding building);

    boolean deletePlaceBuilding(String buildingId);

    PlaceBuilding select4Edit(String buildingId);

    boolean updatePlaceBuilding(PlaceBuilding placeBuilding);
}
