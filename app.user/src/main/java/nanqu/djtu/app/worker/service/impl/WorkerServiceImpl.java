package nanqu.djtu.app.worker.service.impl;

import nanqu.djtu.app.worker.repository.WorkerRepositoryI;
import nanqu.djtu.app.worker.service.WorkerServiceI;
import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.pojo.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    private WorkerRepositoryI workerRepository;

    @Override
    public WorkerInfo workerLogin(String workerTel, String workerPass) {
        return workerRepository.workerLogin(workerTel,workerPass);
    }

    @Override
    public List<MaintenanceList> queryMaintenanceListByState(int listState, WorkerInfo info) {
        return workerRepository.selectMaintenanceListByState(listState, info.getUserId());
    }

    @Override
    public List<MaintenanceList> queryLatest35MaintenanceList(WorkerInfo info) {
        return workerRepository.selectMaintenanceLists(info.getUserId());
    }

    @Override
    public MaintenanceList query4details(String listNumber) {
        MaintenanceList list = workerRepository.select4details(listNumber);
        List<MaintenanceList> lists = workerRepository.selectStatusWithListNum(listNumber);
        list.setLists(lists);

        return list;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public Boolean edit(MaintenanceList list, WorkerInfo info) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        Boolean tmp1 = workerRepository.insertState(time, list);
        Boolean tmp2 = workerRepository.update(time,list);

        if (tmp1 && tmp2) {
            LOG.info("[worker] update MaintenanceList {} success with worker {}.", list.getListNumber(), info.getWorkerName());
        } else {
            LOG.warn("[worker] update MaintenanceList {} failure with worker {}.", list.getListNumber(), info.getWorkerName());
        }

        return tmp1 && tmp2;
    }

    @Override
    public WorkerInfo queryWorkerInfo(String userId) {
        return workerRepository.selectWorkerInfo(userId);
    }

    @Override
    public Boolean changePass(WorkerInfo info, WorkerInfo workerInfo) {
        Boolean change = workerRepository.changePass(info);
        if (change) {
            LOG.info("[worker] update password {} success with workerName {}.", workerInfo.getWorkerName(), workerInfo.getWorkerName());
        } else {
            LOG.warn("[worker] update password {} failure with workerName {}.", workerInfo.getWorkerName(), workerInfo.getWorkerName());
        }

        return change;
    }

    @Override
    public int  queryTodoMaintenanceNumber(String userId) {
        return workerRepository.selectTodoMiantenanceSum(userId);
    }

    @Override
    public int queryallDoMaintenanceSum(String userId) {
        return workerRepository.selectallDoMaintenanceSum(userId);
    }

    @Override
    public int queryOnDoMaintenanceSum(String userId) {
        return workerRepository.selectOnDoMaintenanceSum(userId);
    }
}
