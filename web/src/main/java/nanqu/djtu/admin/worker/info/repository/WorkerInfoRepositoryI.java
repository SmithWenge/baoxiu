package nanqu.djtu.admin.worker.info.repository;


import nanqu.djtu.pojo.RepairGroup;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.pojo.WorkerType;

import java.util.List;

public interface WorkerInfoRepositoryI {
    /**
     * 查询工人信息列表
     *
     * @return 所有未删工人的信息
     */

    List<WorkerInfo> query4List();

    /**
     * 查询维修小组的信息列表
     *
     * @return 所未删维修小组的信息
     */

    List<RepairGroup> repairGroupQuery4List();

    /**
     * 查询工种的信息列表
     *
     * @return 所有未删除工种的信息
     */

    List<WorkerType> workerTypeQuery4List();

    /**
     * 添加新的工人
     * @param info 工人信息
     * @return true ,else false
     */

    boolean insertNewWorkerInfo(WorkerInfo info);
    /**
     * 删除工人
     *
     * @param userId 工人Id
     * @return 删除成功返回true, else false
     */

    boolean deleteWorkerInfo(String userId);
    /**
     * 查询需要编辑的工人的信息
     *
     * @param userId 校区Id
     * @return 校区信息对象
     */

    WorkerInfo query4Edit(String userId);
    /**
     * 更新工人信息
     *
     * @param info 新的更改后的工人信息
     * @return 更改成功返回true, else false
     */

    boolean updateWorkerInfo(WorkerInfo info);
}
