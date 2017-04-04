package nanqu.djtu.api.equipment.service;

import nanqu.djtu.pojo.Equipment;

import java.util.List;

public interface EquipmentApiServiceI {
    List<Equipment> queryEquipmentByRoomId(String roomId);
}
