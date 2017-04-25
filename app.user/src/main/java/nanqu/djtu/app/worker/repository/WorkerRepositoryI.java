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

//    /**
//     * 根据状态查询维修单列表listStateTime
//     * @param listState,userId
//     * @return
//     */
//    List<MaintenanceList> selectListStateTimesByState(int listState,String userId);

    /**
     * 查看在办维修单
     *
     * @param userId 工人Id
     * @return 这个工人所在维修小组的的在办报修单
     */
    List<MaintenanceList> selectDoingMaintenanceList(String userId);

    /**
     * 查看待办维修单
     *
     * @param userId 工人Id
     * @return 这个工人所在维修小组的的待办报修单
     */
    List<MaintenanceList> selectWaitingMaintenanceList(String userId);

    /**
     * 查询维修单列表listNumber,listDescription
     * @param userId
     * @return
     */
    List<MaintenanceList> selectMaintenanceLists(String userId);

//    /**
//     * 根据状态查询维修单列表listStateTime
//     * @param ,userId
//     * @return
//     */
//    List<MaintenanceList> selectListStateTimes(String userId);

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

    /**
     * 查询工人信息
     * @param userId
     * @return
     */
    WorkerInfo selectWorkerInfo(String userId);

    /**
     * 修改密码
     * @param info
     * @return
     */
    Boolean changePass(WorkerInfo info);

    /**
     * 查询待办的报修单数
     * @return 报修单数
     */

    int selectTodoMiantenanceSum(String userId);

    /**
     * 查询所有需处理的报修单
     * @param userId
     * @return 报修单数
     */

    int selectallDoMaintenanceSum(String userId);

    /**
     * 查询所有在处理的报修单数
     * @return
     */

    int selectOnDoMaintenanceSum(String userId);
}
