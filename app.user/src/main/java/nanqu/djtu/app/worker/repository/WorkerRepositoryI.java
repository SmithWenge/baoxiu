package nanqu.djtu.app.worker.repository;

import nanqu.djtu.pojo.WorkerInfo;

public interface WorkerRepositoryI {
    public WorkerInfo workerLogin(String workerTel, String workerPass);
}
