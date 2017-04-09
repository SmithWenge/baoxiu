package nanqu.djtu.admin.maintenance.list.manage.service.impl;

import ch.qos.logback.classic.Logger;
import com.google.common.base.Strings;
import nanqu.djtu.admin.maintenance.list.manage.repository.MaintenanceLisRepositoryI;
import nanqu.djtu.admin.maintenance.list.manage.service.MaintenanceListServiceI;
import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaintenanceListServiceImpl implements MaintenanceListServiceI {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MaintenanceListServiceImpl.class);
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

    @Override
    public MaintenanceList query4details(String listNumber) {
        MaintenanceList list = maintenanceLisRepository.select4details(listNumber);
        List<MaintenanceList> lists = maintenanceLisRepository.selectStatusWithListNum(listNumber);
        list.setLists(lists);

        return list;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public boolean updatestate(String listNumber, AdminUser user) {


        boolean update = maintenanceLisRepository.updateliststate(listNumber);
        boolean insert = maintenanceLisRepository.insertliststate(listNumber);

        if (update && insert) {
            LOG.info("[ListState] update  liststate {} success with user {}.",listNumber,user.getAdminName());

        } else {
            LOG.warn("[ListState] delete place distinct {} failure with user {}.", listNumber, user.getAdminName());

        }

        return insert;


    }

}
