package nanqu.djtu.admin.equipment.service.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.equipment.repository.EquipmentRepositoryI;
import nanqu.djtu.admin.equipment.service.EquipmentServiceI;
import nanqu.djtu.pojo.*;
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
public class EquipmentServiceImpl implements EquipmentServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Autowired
    private EquipmentRepositoryI equipmentRepository;

    @Override
    public Page<Equipment> query4Page(Equipment equipment, Pageable pageable) {
//        String roomId = equipment.getRoomId();
//        String buildingId = equipment.getBuildingId();

//        if (Strings.isNullOrEmpty(roomId) && (!Strings.isNullOrEmpty(buildingId))) {
//            String setId = equipmentRepository.selectSetIdWithBuilding(buildingId);
//
//            equipment.setSetId(setId);
//        } else if (!Strings.isNullOrEmpty(roomId)) {
//            String setId = equipmentRepository.selectSetIdWithRoom(roomId);
//
//            equipment.setSetId(setId);
//        }

        return equipmentRepository.select4Page(equipment, pageable);
    }

    @Override
    public List<PlaceDistinct> queryAllPlaceDistincts() {
        return equipmentRepository.selectAllPlaceDistincts();
    }

    @Override
    public List<PlaceBuilding> queryBuildingWithDistinctId(String distinctId) {
        return equipmentRepository.selectBuildingWithDistinctId(distinctId);
    }

    @Override
    public List<PlaceRoom> queryRoomWithBuildingId(String buildingId) {
        return equipmentRepository.selectRoomWithBuildingId(buildingId);
    }

    @Override
    public List<EquipmentSet> querySetsWithRoomId(String roomId) {
        return equipmentRepository.selectSetsWithRoomId(roomId);
    }

    @Override
    public List<EquipmentSet> queryAllEquipmentSets() {
        return equipmentRepository.selectAllEquipmentSets();
    }

    @Override
    public List<RepairGroup> queryAllRepairGroup() {
        return equipmentRepository.selectAllRepairGroup();
    }

    @Override
    public boolean queryUniqueEquipmentNumber(String equipmentNumber) {
        return equipmentRepository.selectUniqueEquipmentNumber(equipmentNumber);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean saveNewEquipment(Equipment equipment, AdminUser user) {
        String equipmentId = PrimaryKeyUtil.uuidPrimaryKey();
        equipment.setEquipmentId(equipmentId);

        boolean insert = equipmentRepository.insertNewEquipment(equipment);

        if (insert) {
            LOG.info("[Equipment] add new equipment success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[Equipment] add new equipment failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public Equipment query4Edit(String equipmentId) {
        return equipmentRepository.select4Edit(equipmentId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean updateEquipment(Equipment equipment, AdminUser user) {
        boolean update = equipmentRepository.updateEquipment(equipment);

        if (update) {
            LOG.info("[PlaceDistinct] update equipment {} success with user {}.", equipment.getEquipmentId(), user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] update equipment {} failure with user {}.", equipment.getEquipmentId(), user.getAdminName());
        }

        return update;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean deleteEquipment(String equipmentId, AdminUser user) {
        boolean delete = equipmentRepository.deleteEquipment(equipmentId);
        boolean deleteSet = equipmentRepository.deleteEquipmentSetTable(equipmentId);

        if (delete && deleteSet) {
            LOG.info("[PlaceDistinct] delete equipment {} success with user {}.", equipmentId, user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] delete equipment {} failure with user {}.", equipmentId, user.getAdminName());
        }

        return delete;
    }
}
