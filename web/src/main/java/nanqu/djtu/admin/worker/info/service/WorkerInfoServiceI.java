package nanqu.djtu.admin.worker.info.service;


import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.RepairGroup;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.pojo.WorkerType;

import java.util.List;

public interface WorkerInfoServiceI {
    List<WorkerInfo> query4List();

    List<RepairGroup> repairGroupQuery4List();

    List<WorkerType> workerTypeQuery4List();

    boolean saveNewWorkerInfo(WorkerInfo info, AdminUser user);

    boolean deleteWorkerINfo(String userId, AdminUser user);

    WorkerInfo query4Edit(String userId);

    boolean updateWorkerInfo(WorkerInfo info, AdminUser user);
}
