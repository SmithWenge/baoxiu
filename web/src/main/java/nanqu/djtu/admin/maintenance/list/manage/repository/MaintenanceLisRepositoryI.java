package nanqu.djtu.admin.maintenance.list.manage.repository;

import nanqu.djtu.pojo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MaintenanceLisRepositoryI {

    /**
     * 分页查询维修单信息列表
     * @param list
     * @param pageable
     * @return
     */
    Page<MaintenanceList> select4Page(MaintenanceList list,Pageable pageable);

    /**
     * 页面初始化填充下拉框
     * @return
     */
    List<MaintenanceList> selectDistincts();

    /**
     * 二级联动根据distinctId查询PlaceBuilding
     * @param distinctId
     * @return
     */
    List<PlaceBuilding> selectBuildingsWithDistinctId(String distinctId);

    /**
     * 二级联动根据buildingId查询PlaceRoom
     * @param buildingId
     * @return
     */
    List<PlaceRoom> selectRoomWithBuildingId(String buildingId);

    /**
     * 二级联动根据building查询Equipment
     * @param room
     * @return
     */
    List<Equipment> selectEquipmentsWithBuildingId(PlaceRoom room);

    /**
     * 二级联动根据roomId查询Equipment
     * @param room
     * @return
     */
    List<Equipment> selectEquipmentsWithRoomId(PlaceRoom room);

    /**
     * 页面初始化填充下拉框
     * @return
     */
    List<MaintenanceList> selectGroups();

    /**
     * 查看详情
     * @param listNumber
     * @return
     */
    MaintenanceList select4details(String listNumber);

    /**
     * 查看详情中的状态列表
     * @param listNumber
     * @return
     */
    List<MaintenanceList> selectStatusWithListNum(String listNumber);
    /**
     * 更改维修状态为已派单
     * @param listNumber
     * @return
     */
    boolean updateliststate(String listNumber);
    boolean insertliststate(String listNumber);
    /**
     * 更改维修状态为待评价
     * @param listNumber
     * @return
     */
    boolean updatestate(String listNumber);
    boolean insertstate(String listNumber);

    /**
     * 编辑报修单
     * @param list
     * @return
     */
    Boolean updateMaintenanceList(MaintenanceList list);
}
