package nanqu.djtu.admin.place.building.service.impl;


import nanqu.djtu.admin.place.building.repository.PlaceBuildingRepositoryI;
import nanqu.djtu.admin.place.building.service.PlaceBuildingServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceBuildingServiceImpl implements PlaceBuildingServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceBuildingServiceImpl.class);

    @Autowired
    private PlaceBuildingRepositoryI placeBuildingRepository;

    @Override
    public List<PlaceBuilding> query4List() {
        return placeBuildingRepository.select4List();
    }

    @Override
    public List<PlaceDistinct> placeDistinctQuery4List() {
        return placeBuildingRepository.placeDistinctSelect4List();
    }

    @Override
    public List<EquipmentSet> equipmentSetQuery4List() {
        return placeBuildingRepository.equipmentSetSelect4List();
    }

    @Override
    public boolean saveNewPlaceDistinct(PlaceBuilding building, AdminUser user) {
        boolean insert = placeBuildingRepository.insertNewPlaceDistinct(building);

        if (insert) {
            LOG.info("[PlaceBuilding] add new place building success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[PlaceBuilding] add new place building failure with user {}.", user.getAdminName());
        }

        return insert;

    }

    @Override
    public boolean query4PlaceBuildingNumberUnique(PlaceBuilding building) {
        return placeBuildingRepository.select4PlaceBuildingNumberUnique(building);
    }

    @Override
    public boolean deleteDistinct(String buildingId, AdminUser user) {
        boolean delete = placeBuildingRepository.deletePlaceBuilding(buildingId);

        if (delete) {
            LOG.info("[PlaceBuilding] delete place buildingId {} success with user {}.", buildingId, user.getAdminName());
        } else {
            LOG.warn("[PlaceBuilding] delete place buildingId {} failure with user {}.", buildingId, user.getAdminName());
        }

        return delete;
    }

    @Override
    public PlaceBuilding query4Edit(String buildingId) {
        return placeBuildingRepository.select4Edit(buildingId);
    }

    @Override
    public boolean updatePlaceBuilding(PlaceBuilding placeBuilding, AdminUser user) {
        boolean update = placeBuildingRepository.updatePlaceBuilding(placeBuilding);

        if (update) {
            LOG.info("[PlaceBuilding] update place distinct {} success with user {}.", placeBuilding.getBuildingId(), user.getAdminName());
        } else {
            LOG.warn("[PlaceBuilding] update place distinct {} failure with user {}.", placeBuilding.getBuildingId(), user.getAdminName());
        }

        return update;
    }

}
