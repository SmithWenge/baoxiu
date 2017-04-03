package nanqu.djtu.admin.set.service.impl;

import nanqu.djtu.admin.set.repository.SetRepositoryI;
import nanqu.djtu.admin.set.service.SetServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.EquipmentSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SetServiceImpl implements SetServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(SetServiceImpl.class);

    @Autowired
    private SetRepositoryI setRepository;

    @Override
    public Page<EquipmentSet> query4Page(EquipmentSet equipmentSet, Pageable pageable) {
        return setRepository.select4Page(equipmentSet, pageable);
    }

    @Override
    public boolean saveNewSet(EquipmentSet set, AdminUser user) {
        boolean insert = setRepository.insertNewPlaceDistinct(set);

        if (insert) {
            LOG.info("[EquipmentSet] add new equipment set success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[EquipmentSet] add new equipment set failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public EquipmentSet query4Edit(String setId) {
        return setRepository.select4Edit(setId);
    }

    @Override
    public boolean updateSet(EquipmentSet set, AdminUser user) {
        boolean update = setRepository.updateSet(set);

        if (update) {
            LOG.info("[EquipmentSet] update equipment set {} success with user {}.", set.getSetId(), user.getAdminName());
        } else {
            LOG.warn("[EquipmentSet] update equipment set {} failure with user {}.", set.getSetId(), user.getAdminName());
        }

        return update;
    }

    @Override
    public boolean deleteSet(String setId, AdminUser user) {
        boolean delete = setRepository.deleteSet(setId);

        if (delete) {
            LOG.info("[EquipmentSet] delete equipment set {} success with user {}.", setId, user.getAdminName());
        } else {
            LOG.warn("[EquipmentSet] delete equipment set {} failure with user {}.", setId, user.getAdminName());
        }

        return delete;
    }
}
