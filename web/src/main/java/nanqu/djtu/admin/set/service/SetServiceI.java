package nanqu.djtu.admin.set.service;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.EquipmentSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SetServiceI {
    Page<EquipmentSet> query4Page(EquipmentSet equipmentSet, Pageable pageable);
    boolean saveNewSet(EquipmentSet set, AdminUser user);
    EquipmentSet query4Edit(String setId);
    boolean updateSet(EquipmentSet set, AdminUser user);
    boolean deleteSet(String setId, AdminUser user);
}
