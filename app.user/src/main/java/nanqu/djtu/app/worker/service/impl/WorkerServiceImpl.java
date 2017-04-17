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
        List<MaintenanceList> lists1 = workerRepository.selectMaintenanceListByState(listState, info.getUserId());
        List<MaintenanceList> lists2 = workerRepository.selectListStateTimesByState(listState, info.getUserId());

        for (MaintenanceList list1 : lists1) {
            for (MaintenanceList list2 : lists2) {
                if (list1.getListNumber().equals(list2.getListNumber())) {
                    list1.setListstatetime(list2.getListstatetime());
                    list1.setListDescription(list2.getListDescription());
                }
            }
        }

        return lists1;
    }

    @Override
    public List<MaintenanceList> queryLatest35MaintenanceList(WorkerInfo info) {
        List<MaintenanceList> lists1 = workerRepository.selectMaintenanceLists(info.getUserId());
        List<MaintenanceList> lists2 = workerRepository.selectListStateTimes(info.getUserId());

        for (MaintenanceList list1 : lists1) {
            for (MaintenanceList list2 : lists2) {
                if (list1.getListNumber().equals(list2.getListNumber())) {
                    list1.setListstatetime(list2.getListstatetime());
                    list1.setListDescription(list2.getListDescription());
                }
            }
        }

        return lists1;
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
}
