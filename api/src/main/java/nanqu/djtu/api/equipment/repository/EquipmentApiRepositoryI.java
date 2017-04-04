package nanqu.djtu.api.equipment.repository;

import nanqu.djtu.pojo.Equipment;

import java.util.List;

public interface EquipmentApiRepositoryI {
    /**
     * 查询room下的equipment
     *
     * @param roomId roomId
     * @return 如果存在返回list, else size 0 list
     */
    List<Equipment> selectEquipmentByRoomId(String roomId);
}
