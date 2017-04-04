package nanqu.djtu.admin.worker.info.service.impl;

import nanqu.djtu.admin.worker.info.repository.WorkerInfoRepositoryI;
import nanqu.djtu.admin.worker.info.service.WorkerInfoServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.RepairGroup;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.pojo.WorkerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerInfoServiceImpl implements WorkerInfoServiceI{
    private static final Logger LOG = LoggerFactory.getLogger(WorkerInfoServiceImpl.class);
    @Autowired
    private WorkerInfoRepositoryI workerInfoRepository;

    @Override
    public List<WorkerInfo> query4List() {
        return workerInfoRepository.query4List();
    }

    @Override
    public List<RepairGroup> repairGroupQuery4List() {
        return workerInfoRepository.repairGroupQuery4List();
    }

    @Override
    public List<WorkerType> workerTypeQuery4List() {
        return workerInfoRepository.workerTypeQuery4List();
    }

    @Override
    public boolean saveNewWorkerInfo(WorkerInfo info, AdminUser user) {
        boolean insert = workerInfoRepository.insertNewWorkerInfo(info);

        if (insert) {
            LOG.info("[WorkerInfo] add new worker info success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[WorkerInfo] add new worker info failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public boolean deleteWorkerINfo(String userId, AdminUser user) {
        boolean delete = workerInfoRepository.deleteWorkerInfo(userId);

        if (delete) {
            LOG.info("[WorkerInfo] delete worker info {} success with user {}.", userId, user.getAdminName());
        } else {
            LOG.warn("[WorkerInfo] delete worker info{} failure with user {}.", userId, user.getAdminName());
        }

        return delete;
    }

    @Override
    public WorkerInfo query4Edit(String userId) {
        return workerInfoRepository.query4Edit(userId);
    }

    @Override
    public boolean updateWorkerInfo(WorkerInfo info, AdminUser user) {
        boolean update = workerInfoRepository.updateWorkerInfo(info);

        if (update) {
            LOG.info("[WorkerInfo] update worker info {} success with user {}.", info.getUserId(), user.getAdminName());
        } else {
            LOG.warn("[WorkerInfo] update worker info {} failure with user {}.", info.getUserId(), user.getAdminName());
        }

        return update;
    }

}

