package nanqu.djtu.admin.worker.type.service.impl;

import nanqu.djtu.admin.place.distinct.service.impl.PlaceDistinctServiceImpl;
import nanqu.djtu.admin.worker.type.repository.WorkerTyeRepositoryI;
import nanqu.djtu.admin.worker.type.service.WorkerTypeServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.WorkerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerTypeServiceImpl implements WorkerTypeServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceDistinctServiceImpl.class);

    @Autowired
    private WorkerTyeRepositoryI workerTyeRepository;

    @Override
    public List<WorkerType> query4List() {
        return workerTyeRepository.select4List();
    }

    @Override
    public boolean saveWorkerType(WorkerType distinct, AdminUser user) {
        boolean insert = workerTyeRepository.insertNewWorkerType(distinct);

        if (insert) {
            LOG.info("[PlaceDistinct] add new place distinct success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] add new place distinct failure with user {}.", user.getAdminName());
        }
        return insert;

    }

    @Override
    public boolean deleteDistinct(String typeId, AdminUser user) {
        boolean delete = workerTyeRepository.deleteWorkerType(typeId);

        if (delete) {
            LOG.info("[PlaceDistinct] delete place distinct {} success with user {}.", typeId, user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] delete place distinct {} failure with user {}.", typeId, user.getAdminName());
        }

        return delete;
    }
    @Override
    public WorkerType query4Edit(String typeId) {
        return workerTyeRepository.select4Edit(typeId);
    }

    @Override
        public boolean updateWorkerType(WorkerType distinct, AdminUser user) {
            boolean update = workerTyeRepository.updateWorkerType(distinct);

            if (update) {
                LOG.info("[PlaceDistinct] update place distinct {} success with user {}.", distinct.getTypeId(), user.getAdminName());
            } else {
                LOG.warn("[PlaceDistinct] update place distinct {} failure with user {}.", distinct.getTypeId(), user.getAdminName());
            }

            return update;
        }
}

