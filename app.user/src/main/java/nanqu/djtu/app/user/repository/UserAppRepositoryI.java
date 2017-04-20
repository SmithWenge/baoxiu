package nanqu.djtu.app.user.repository;

import nanqu.djtu.pojo.*;

import java.util.List;

public interface UserAppRepositoryI {

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
     * 查看这个设备的维修组
     *
     * @param equipmentId 设备Id
     * @return 返回这个设备的维修组Id
     */

    String selectRepairGroupId(String equipmentId);
    /**
     * 查询这个校区的编号
     *
     * @param distinctId 校区Id
     * @return 返回校区编号
     */

    String selectDistinctNumber(String distinctId);
    /**
     * 查询这个地点的编号
     *
     * @param buildingId 地点Id
     * @return 这个地点的编号
     */

    String selectBuildingNumber(String buildingId);
    /**
     * 查询这个位置的编号
     *
     * @param roomId 位置Id
     * @return 这个位置的编号
     */

    String selectRoomNumber(String roomId);
    /**
     * 查询这个设备的编号
     *
     * @param equipmentId 设备Id
     * @return 这个设备的编号
     */

    String selectEquipmentNumber(String equipmentId);
    /**
     * 查询这个报修单是否存在
     *
     * @param listId 报修单编号
     * @return 如果存在返回true, else false
     */

    boolean selectIfExistMaintenanceList(String listId);
    /**
     * 添加新的保修单
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */

    boolean insertNew(MaintenanceList list);

    /**
     * 添加保修状态
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */

    boolean insertNewListState(MaintenanceList list);

    /**
     * 通过roomID查询设备信息
     * @param roomId
     * @return 设备
     */

    List<Equipment> queryPlaceRoomByRoomId(String roomId);

    /**
     * 通过所以id查询所有名字
     * @param maintenanceList
     * @return
     */

    MaintenanceList selectAllName(MaintenanceList maintenanceList);

    /**
     * 通过电话查所有保修单
     * @param userTel
     * @return
     */

    List<MaintenanceList> selectMaintenanceListByTel(String userTel);

    /**
     * 通过保修单号查保修单详情
     * @param listNumber
     * @return
     */
    MaintenanceList selectOneMaintenance(String listNumber);

    /**
     * 查询未完成的保修单数
     * @return
     */

    int sumOfMaintenance();

    /**
     * 根据保修单号查日期
     * @param listNumber
     * @return
     */
  String selectListStateTime(String listNumber);

    /**
     * 根据保修单查保修单所有状态
     * @param listNumber
     * @return
     */
    List<MaintenanceList> selectAllState(String listNumber);

    /**
     * 查询校区
     * @return校区集合
     */
    List<PlaceDistinct> queryDistincts();

    /**
     * 查询每个校区下有多少个保修单
     * @param distinctId
     * @return 保修单数
     */
    int queryEachDistinctMaintenanceNumber(String distinctId);

    /**
     * 根据Id查校区名
     * @param maintenanceList
     * @return 报修单对象
     */
    MaintenanceList selectDistinctName(MaintenanceList maintenanceList);

    /**
     * 根据Id查校区名和地点名
     * @param maintenanceList
     * @return 报修单对象
     */
    MaintenanceList selectDistinctNameAndBuildingName(MaintenanceList maintenanceList);

    /**
     * 根据Id查校区，地点，位置名
     * @param maintenanceList
     * @return
     */
    MaintenanceList  selectDistinctNameAndBuildingNameAndRoomName(MaintenanceList maintenanceList);

}
