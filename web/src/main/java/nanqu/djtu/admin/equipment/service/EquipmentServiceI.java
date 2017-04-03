package nanqu.djtu.admin.equipment.service;

import nanqu.djtu.pojo.Equipment;
import nanqu.djtu.pojo.PlaceDistinct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EquipmentServiceI {
    Page<Equipment> query4Page(Equipment equipment, Pageable pageable);
    List<PlaceDistinct> queryAllPlaceDistincts();
}
