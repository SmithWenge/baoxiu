package nanqu.djtu.admin.place.room.service.impl;

import nanqu.djtu.admin.place.room.repository.PlaceRoomRepositoryI;
import nanqu.djtu.admin.place.room.service.PlaceRoomServiceI;
import nanqu.djtu.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceRoomServiceImpl implements PlaceRoomServiceI{
    private static final Logger LOG = LoggerFactory.getLogger(PlaceRoomServiceImpl.class);

    @Autowired
    private PlaceRoomRepositoryI placeRoomRepository;

    @Override
    public Page<PlaceRoom> query4Page(PlaceRoom room, Pageable pageable) {
        return placeRoomRepository.query4Page(room,pageable);
    }

    @Override
    public boolean saveNewPlaceRoom(PlaceRoom room, AdminUser user) {
        boolean insert = placeRoomRepository.insertNewPlaceRoom(room);

        if (insert) {
            LOG.info("[PlaceRoom] add new place room success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[PlaceRoom] add new place room failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public boolean deleteRoom(String roomId, AdminUser user) {
        boolean delete = placeRoomRepository.deletePlaceRoom(roomId);

        if (delete) {
            LOG.info("[PlaceRoom] delete room distinct {} success with user {}.", roomId, user.getAdminName());
        } else {
            LOG.warn("[PlaceRoom] delete room distinct {} failure with user {}.", roomId, user.getAdminName());
        }

        return delete;
    }

    @Override
    public PlaceRoom query4Edit(String roomId) {
        return placeRoomRepository.select4Edit(roomId);
    }

    @Override
    public boolean updatePlaceRoom(PlaceRoom room, AdminUser user) {
        boolean update = placeRoomRepository.updatePlaceRoom(room);

        if (update) {
            LOG.info("[PlaceRoom] update place room {} success with user {}.", room.getDistinctId(), user.getAdminName());
        } else {
            LOG.warn("[PlaceRoom] update place room {} failure with user {}.", room.getDistinctId(), user.getAdminName());
        }

        return update;
    }

    @Override
    public boolean query4PlaceRoomNumberUnique(String roomNumber) {
        return placeRoomRepository.select4PlaceRoomNumberUnique(roomNumber);
    }

    @Override
    public List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId) {
        return placeRoomRepository.selectBuildingsByDistinctId(distinctId);
    }

    @Override
    public List<PlaceDistinct> queryDistincts() {
        return placeRoomRepository.selectDistincts();
    }

    @Override
    public List<EquipmentSet> querySets() {
        return placeRoomRepository.selectSets();
    }

    @Override
    public List<PlaceBuilding> queryBuildings4Edit() {
        return placeRoomRepository.selectBuildings4Edit();
    }
}
