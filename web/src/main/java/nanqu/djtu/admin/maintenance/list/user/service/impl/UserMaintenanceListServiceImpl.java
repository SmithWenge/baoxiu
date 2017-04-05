package nanqu.djtu.admin.maintenance.list.user.service.impl;


import nanqu.djtu.admin.maintenance.list.user.repository.UserMaintenanceListRepositoryI;
import nanqu.djtu.admin.maintenance.list.user.service.UserMaintenanceListServiceI;
import nanqu.djtu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
@Service
public class UserMaintenanceListServiceImpl implements UserMaintenanceListServiceI {
    @Autowired
    private UserMaintenanceListRepositoryI maintenanceListRepository;

    @Override
    public List<PlaceDistinct> query4ListPlaceDistinct() {
        return maintenanceListRepository.query4ListPlaceDistinct();
    }

    @Override
    public List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId) {
        return maintenanceListRepository.selectBuildingsByDistinctId(distinctId);
    }

    @Override
    public List<EquipmentSet> querySets() {
        return maintenanceListRepository.querySets();
    }

    @Override
    public List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId) {
        return maintenanceListRepository.queryPlaceRoomByBuildingId(buildingId);
    }

    @Override
    public List<Equipment> queryEquipment() {
        return maintenanceListRepository.queryEquipment();
    }

    @Override
    public boolean saveNewMaintenanceList(MaintenanceList maintenance) {
        return false;
    }

    @Override
    public MaintenanceList selectRepairGroupId(MaintenanceList maintenance) {
        return maintenanceListRepository.selectRepairGroupId(maintenance);
    }
}
