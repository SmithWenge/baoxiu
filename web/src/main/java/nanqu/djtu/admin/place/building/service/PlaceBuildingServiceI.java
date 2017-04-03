package nanqu.djtu.admin.place.building.service;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public interface PlaceBuildingServiceI {

    List<PlaceBuilding> query4List();

    List<PlaceDistinct> placeDistinctQuery4List();

    List<EquipmentSet> equipmentSetQuery4List();

    boolean saveNewPlaceDistinct(PlaceBuilding building, AdminUser user);

    boolean query4PlaceBuildingNumberUnique(PlaceBuilding building);

    boolean deleteDistinct(String buildingId, AdminUser user);

    PlaceBuilding query4Edit(String buildingId);

    boolean updatePlaceBuilding(PlaceBuilding placeBuilding, AdminUser user);


}
