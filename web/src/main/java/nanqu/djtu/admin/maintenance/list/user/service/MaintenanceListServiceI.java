package nanqu.djtu.admin.maintenance.list.user.service;

import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public interface MaintenanceListServiceI {
    List<PlaceDistinct> query4ListPlaceDistinct();

    List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId);
}
