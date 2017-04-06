package nanqu.djtu.admin.maintenance.list.user.service.impl;


import nanqu.djtu.admin.maintenance.list.user.repository.UserMaintenanceListRepositoryI;
import nanqu.djtu.admin.maintenance.list.user.service.UserMaintenanceListServiceI;
import nanqu.djtu.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */
@Service
public class UserMaintenanceListServiceImpl implements UserMaintenanceListServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(UserMaintenanceListServiceImpl.class);
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
    public List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId) {
        return maintenanceListRepository.queryPlaceRoomByBuildingId(buildingId);
    }

    @Override
    public List<Equipment> queryEquipment() {
        return maintenanceListRepository.queryEquipment();
    }

    @Override
    public boolean saveNewMaintenanceList(MaintenanceList maintenance) {
        boolean insert =  maintenanceListRepository.saveNewMaintenanceList(maintenance);
        if (insert) {
            LOG.info("[Maintenance] add new place room success with user {}.");
        } else {
            LOG.warn("[Maintenance] add new place room failure with user {}.");
        }

        return insert;
    }

    @Override
    public MaintenanceList queryDistinctNumberAndBuildingNumber(MaintenanceList maintenance) {
        return maintenanceListRepository.queryDistinctNumberAndBuildingNumber(maintenance);
    }

    @Override
    public MaintenanceList queryEquipmentNumber(MaintenanceList maintenance) {
        return maintenanceListRepository.queryEquipmentNumber(maintenance);
    }


}
