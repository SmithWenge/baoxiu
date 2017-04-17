package nanqu.djtu.app.worker.service;

import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.pojo.WorkerInfo;

import java.util.List;

public interface WorkerServiceI {
    WorkerInfo workerLogin(String workerTel, String workerPass);
    List<MaintenanceList> queryMaintenanceListByState(int listState, WorkerInfo info);
    List<MaintenanceList> queryLatest35MaintenanceList(WorkerInfo info);
    MaintenanceList query4details(String listNumber);
    Boolean edit(MaintenanceList list,WorkerInfo info);
}
