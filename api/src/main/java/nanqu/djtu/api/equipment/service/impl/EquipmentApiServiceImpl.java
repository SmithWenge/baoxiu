package nanqu.djtu.api.equipment.service.impl;

import nanqu.djtu.api.equipment.repository.EquipmentApiRepositoryI;
import nanqu.djtu.api.equipment.service.EquipmentApiServiceI;
import nanqu.djtu.pojo.Equipment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentApiServiceImpl implements EquipmentApiServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentApiServiceImpl.class);

    @Autowired
    private EquipmentApiRepositoryI equipmentApiRepository;

    @Override
    public List<Equipment> queryEquipmentByRoomId(String roomId) {
        return equipmentApiRepository.selectEquipmentByRoomId(roomId);
    }
}
