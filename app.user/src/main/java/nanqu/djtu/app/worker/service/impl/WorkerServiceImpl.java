package nanqu.djtu.app.worker.service.impl;

import nanqu.djtu.app.worker.repository.WorkerRepositoryI;
import nanqu.djtu.app.worker.service.WorkerServiceI;
import nanqu.djtu.pojo.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    private WorkerRepositoryI workerRepository;

    @Override
    public WorkerInfo workerLogin(String workerTel, String workerPass) {
        return workerRepository.workerLogin(workerTel,workerPass);
    }
}
