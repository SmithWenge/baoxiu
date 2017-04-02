package nanqu.djtu.admin.place.building.service;

import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public interface PlaceBuildingServiceI {
    /**
     *
     * @return
     */
    List<PlaceBuilding> query4List();

    /**
     *
     * @return
     */
    List<PlaceDistinct> placeDistinctQuery4List();
}
