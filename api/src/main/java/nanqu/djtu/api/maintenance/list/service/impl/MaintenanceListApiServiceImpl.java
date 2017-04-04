package nanqu.djtu.api.maintenance.list.service.impl;

import nanqu.djtu.api.maintenance.list.repository.MaintenanceListApiRepositoryI;
import nanqu.djtu.api.maintenance.list.service.MaintenanceListApiServiceI;
import nanqu.djtu.pojo.MaintenanceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MaintenanceListApiServiceImpl implements MaintenanceListApiServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceListApiServiceImpl.class);

    @Autowired
    private MaintenanceListApiRepositoryI maintenanceListApiRepository;

    @Transactional
    @Override
    public boolean addNewMaintenanceList(MaintenanceList list) {

        String equipmentId = list.getEquipmentId();
        String repairGroupId = maintenanceListApiRepository.selectRepairGroupId(equipmentId);
        list.setRepairGroupId(repairGroupId);

        return maintenanceListApiRepository.insertNew(list);
    }
}
