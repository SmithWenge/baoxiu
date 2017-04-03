package nanqu.djtu.admin.equipment.service.impl;

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
        boolean insertSet = equipmentRepository.insertNewEquipmentWithSet(equipment);

        if (insert && insertSet) {
            LOG.info("[Equipment] add new equipment success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[Equipment] add new equipment failure with user {}.", user.getAdminName());
        }

        return insert;
    }
}
