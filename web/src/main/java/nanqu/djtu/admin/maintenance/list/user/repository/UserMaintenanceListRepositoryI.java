package nanqu.djtu.admin.maintenance.list.user.repository;

import com.mysql.jdbc.MiniAdmin;
import nanqu.djtu.pojo.*;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
public interface UserMaintenanceListRepositoryI {
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

    /**
     * 查询位置信息
     * @return 未删除的位置信息列表
     */

    List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId);

    /**
     * 查询设备信息
     *
     * @return 未删除的设备信息
     */

    List<Equipment> queryEquipment();


    /**
     * 查询所有校区编号和地点编号
     * @param maintenance
     */
    MaintenanceList queryDistinctNumberAndBuildingNumber(MaintenanceList maintenance);
    /**
     * 查询所有设备编号
     * @param maintenance
     */
    MaintenanceList queryEquipmentNumber(MaintenanceList maintenance);
    /**
     * 添加保修单
     * @param maintenance
     */

    boolean saveNewMaintenanceList(MaintenanceList maintenance);
}
