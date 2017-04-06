package nanqu.djtu.api.maintenance.list.repository;

import nanqu.djtu.pojo.MaintenanceList;

public interface MaintenanceListApiRepositoryI {
    /**
     * 通过微信添加新的保修单
     *
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNew(MaintenanceList list);

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
}
