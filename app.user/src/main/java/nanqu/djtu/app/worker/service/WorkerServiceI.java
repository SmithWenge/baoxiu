package nanqu.djtu.app.worker.service;

import nanqu.djtu.pojo.WorkerInfo;

public interface WorkerServiceI {
    public WorkerInfo workerLogin(String workerTel, String workerPass);
}
