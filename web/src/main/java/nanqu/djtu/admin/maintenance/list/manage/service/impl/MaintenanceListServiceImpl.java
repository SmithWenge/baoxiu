package nanqu.djtu.admin.maintenance.list.manage.service.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.maintenance.list.manage.repository.MaintenanceLisRepositoryI;
import nanqu.djtu.admin.maintenance.list.manage.service.MaintenanceListServiceI;
import nanqu.djtu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaintenanceListServiceImpl implements MaintenanceListServiceI {

    @Autowired
    private MaintenanceLisRepositoryI maintenanceLisRepository;

    @Override
    public Page<MaintenanceList> query4Page(MaintenanceList list, Pageable pageable) {
        return maintenanceLisRepository.select4Page(list,pageable);
    }

    @Override
    public List<MaintenanceList> queryDistincts() {
        return maintenanceLisRepository.selectDistincts();
    }

    @Override
    public List<PlaceBuilding> queryBuildingsWithDistinctId(String distinctId) {
        return maintenanceLisRepository.selectBuildingsWithDistinctId(distinctId);
    }

    @Override
    public List<PlaceRoom> queryRoomWithBuildingId(String buildingId) {
        return maintenanceLisRepository.selectRoomWithBuildingId(buildingId);
    }

    @Override
    public List<Equipment> queryEquipmentsWithRoom(PlaceRoom room) {
        String roomId = room.getRoomId();
        String BuildingId = room.getBuildingId();
        List<Equipment> list;

        if (Strings.isNullOrEmpty(roomId) && !Strings.isNullOrEmpty(BuildingId)) {
            list = maintenanceLisRepository.selectEquipmentsWithBuildingId(room);
        } else {
            list = maintenanceLisRepository.selectEquipmentsWithRoomId(room);
        }

        return list;
    }

    @Override
    public List<MaintenanceList> queryGroups() {
        return maintenanceLisRepository.selectGroups();
    }
}
