package nanqu.djtu.app.worker.repository;

import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.pojo.WorkerInfo;

import java.util.List;

public interface WorkerRepositoryI {
    /**
     * 登录查询
     * @param workerTel
     * @param workerPass
     * @return
     */
    WorkerInfo workerLogin(String workerTel, String workerPass);

    /**
     * 查询维修单列表listNumber,listDescription
     * @param listState,userId
     * @return
     */
    List<MaintenanceList> selectMaintenanceListByState(int listState,String userId);

    /**
     * 根据状态查询维修单列表listStateTime
     * @param listState,userId
     * @return
     */
    List<MaintenanceList> selectListStateTimesByState(int listState,String userId);

    /**
     * 查询维修单列表listNumber,listDescription
     * @param userId
     * @return
     */
    List<MaintenanceList> selectMaintenanceLists(String userId);

    /**
     * 根据状态查询维修单列表listStateTime
     * @param ,userId
     * @return
     */
    List<MaintenanceList> selectListStateTimes(String userId);

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
     * 更改状态 插入新状态
     * @param time
     * @param list
     * @return
     */
    Boolean insertState(String time, MaintenanceList list);

    /**
     * 更新状态
     * @param time
     * @param list
     * @return
     */
    Boolean update(String time, MaintenanceList list);
}
