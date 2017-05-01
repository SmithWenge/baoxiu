package nanqu.djtu.admin.maintenance.list.manage.service.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.maintenance.list.manage.repository.MaintenanceLisRepositoryI;
import nanqu.djtu.admin.maintenance.list.manage.service.MaintenanceListServiceI;
import nanqu.djtu.app.user.repository.UserAppRepositoryI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MaintenanceListServiceImpl implements MaintenanceListServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceListServiceImpl.class);

    @Autowired
    private MaintenanceLisRepositoryI maintenanceLisRepository;
    @Autowired
    private UserAppRepositoryI userAppRepository;

    @Override
    public Page<MaintenanceList> query4Page(MaintenanceList list, Pageable pageable) {
        return maintenanceLisRepository.select4Page(list,pageable);
    }

    @Override
    public List<MaintenanceList> queryDistincts() {
        return maintenanceLisRepository.selectDistincts();
    }

    @Override
    public List<PlaceBuilding> queryBuildingsWithDistinctId(String distinctId) {
        return maintenanceLisRepository.selectBuildingsWithDistinctId(distinctId);
    }

    @Override
    public List<PlaceRoom> queryRoomWithBuildingId(String buildingId) {
        return maintenanceLisRepository.selectRoomWithBuildingId(buildingId);
    }

    @Override
    public List<Equipment> queryEquipmentsWithRoom(PlaceRoom room) {
        return maintenanceLisRepository.selectEquipmentsWithRoomId(room);
    }

    @Override
    public List<MaintenanceList> queryGroups() {
        return maintenanceLisRepository.selectGroups();
    }

    @Override
    public MaintenanceList query4details(String listNumber) {
        MaintenanceList list = maintenanceLisRepository.select4details(listNumber);
        List<MaintenanceList> lists = maintenanceLisRepository.selectStatusWithListNum(listNumber);
        list.setLists(lists);

        return list;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean updatestate(String listNumber, AdminUser user) {
        boolean update = maintenanceLisRepository.updateliststate(listNumber);
        boolean insert = maintenanceLisRepository.insertliststate(listNumber);

        if (update && insert) {
            LOG.info("[ListState] update  liststate {} success with user {}.",listNumber,user.getAdminName());
        } else {
            LOG.warn("[ListState] delete place distinct {} failure with user {}.", listNumber, user.getAdminName());
        }

        return insert;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean done(String listNumber, AdminUser user) {
        boolean update2 = maintenanceLisRepository.updatestate(listNumber);
        boolean insert2 = maintenanceLisRepository.insertstate(listNumber);
        if (update2 && insert2) {
            LOG.info("[ListState] update  liststate {} success with user {}.",listNumber,user.getAdminName());
        } else {
            LOG.warn("[ListState] delete place distinct {} failure with user {}.", listNumber, user.getAdminName());
        }
        return insert2;
    }

    @Override
    public boolean editMaintenanceList(MaintenanceList list, AdminUser user) {
        String equipmentId = list.getEquipmentId();

        String repairGroupId = userAppRepository.selectRepairGroupId(equipmentId);

        if (Strings.isNullOrEmpty(repairGroupId)) {
            repairGroupId = ConstantFields.DEFAULT_GROUP_ID;
        }

        list.setRepairGroupId(repairGroupId);
        String distinctId = list.getDistinctId();
        String buildingId = list.getBuildingId();
        String roomId = list.getRoomId();

        // 拼接维修单编号
        String distinctNumber = userAppRepository.selectDistinctNumber(distinctId);

        String buildingNumber = userAppRepository.selectBuildingNumber(buildingId);
        String roomNumber = userAppRepository.selectRoomNumber(roomId);
        String equipmentNumber = userAppRepository.selectEquipmentNumber(equipmentId);

        if (Strings.isNullOrEmpty(distinctNumber) || Strings.isNullOrEmpty(buildingNumber) ||
                Strings.isNullOrEmpty(roomNumber) || Strings.isNullOrEmpty(equipmentNumber)) {
            list.setNewListNumber(list.getListNumber());
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append(Strings.padStart(distinctNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            builder.append(Strings.padStart(buildingNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            builder.append(Strings.padStart(roomNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            builder.append(Strings.padStart(equipmentNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));

            list.setNewListNumber(builder.toString());
        }

        boolean update = maintenanceLisRepository.updateMaintenanceList(list);

        if (update) {
            LOG.info("[PlaceDistinct] update MaintenanceList {} success with user {}.", list.getListNumber(), user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] update MaintenanceList {} failure with user {}.", list.getListNumber(), user.getAdminName());
        }

        return update;
    }

    @Override
    public boolean updateMaintananceState(MaintenanceList list, AdminUser user) {
        boolean update = maintenanceLisRepository.updateMaintenancestate(list);
        boolean insert = maintenanceLisRepository.insertMaintenancestate(list);

        if (update && insert) {
            LOG.info("[ListState] update  liststate {} success with user {}.",list.getListNumber(),user.getAdminName());
        } else {
            LOG.warn("[ListState] delete place distinct {} failure with user {}.",list.getListNumber(), user.getAdminName());
        }

        return insert;
    }

}
