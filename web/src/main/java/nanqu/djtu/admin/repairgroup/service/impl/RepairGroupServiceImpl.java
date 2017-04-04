package nanqu.djtu.admin.repairgroup.service.impl;

import nanqu.djtu.admin.place.building.repository.PlaceBuildingRepositoryI;
import nanqu.djtu.admin.repairgroup.repository.RepairGroupRepositoryI;
import nanqu.djtu.admin.repairgroup.service.RepairGroupServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.RepairGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RepairGroupServiceImpl implements RepairGroupServiceI{
    private static final Logger LOG = LoggerFactory.getLogger(RepairGroupServiceImpl.class);

    @Autowired
    private RepairGroupRepositoryI repairGroupRepository;

    @Override
    public List<RepairGroup> query4List() {

        return repairGroupRepository.select4List();
    }

    @Override
    public boolean saveNewRepairGroup(RepairGroup group, AdminUser user) {
        boolean insert = repairGroupRepository.insertNewRepairGroup(group);

        if (insert) {
            LOG.info("[RepairGroup] add new repairgroup success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[RepairGroup] add new repairgroup failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public boolean deleteDistinct(String repairGroupId, AdminUser user) {
        boolean delete = repairGroupRepository.deleteRepairGroup(repairGroupId);

        if (delete) {
            LOG.info("[PlaceDistinct] repairgroup distinct {} success with user {}.", repairGroupId, user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] repairgroup distinct {} failure with user {}.", repairGroupId, user.getAdminName());
        }

        return delete;
    }
    @Override
    public RepairGroup query4Edit(String repairGroupId) {
        return repairGroupRepository.select4Edit(repairGroupId);
    }

    @Override
    public boolean updateRepairGroup(RepairGroup group, AdminUser user) {
        boolean update = repairGroupRepository.updateRepairGroup(group);

        if (update) {
            LOG.info("[RepairGroup] update repairgroup {} success with user {}.", group.getRepairGroupId(), user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] update repairgroup {} failure with user {}.", group.getRepairGroupId(), user.getAdminName());
        }

        return update;
    }

    @Override
    public boolean query4RepairGroupNumberUnique(String groupNumber) {
        return repairGroupRepository.select4RepairGroupNumberUnique(groupNumber);
    }


}
