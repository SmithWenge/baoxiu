package nanqu.djtu.admin.maintenance.list.user.service.impl;


import nanqu.djtu.admin.maintenance.list.user.repository.UserMaintenanceListRepositoryI;
import nanqu.djtu.admin.maintenance.list.user.service.UserMaintenanceListServiceI;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;
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
}
