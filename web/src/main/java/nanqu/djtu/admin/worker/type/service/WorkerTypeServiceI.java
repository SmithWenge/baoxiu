package nanqu.djtu.admin.worker.type.service;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.WorkerType;

import java.util.List;

/**
 * Created by DELL on 2017/4/2.
 */
public interface WorkerTypeServiceI {
    List<WorkerType> query4List();
    boolean saveWorkerType(WorkerType type, AdminUser user);
    boolean deleteDistinct(String typeId, AdminUser user);

    boolean updateWorkerType(WorkerType type, AdminUser user);

    WorkerType query4Edit(String typeId);
}
