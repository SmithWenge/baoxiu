package nanqu.djtu.admin.maintenance.list.user.repository;

import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public interface MaintenanceListRepositoryI {
    /**
     * 查询校区信息列表
     * @return 所有未删除的校区信息
     */
    List<PlaceDistinct> query4ListPlaceDistinct();
    /**
     * 查询地点
     * @return
     */

    List<PlaceBuilding> selectBuildingsByDistinctId(String distinctId);
}
