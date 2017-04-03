package nanqu.djtu.admin.place.room.service.impl;

import nanqu.djtu.admin.place.room.repository.PlaceRoomRepositoryI;
import nanqu.djtu.admin.place.room.service.PlaceRoomServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.PlaceRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceRoomServiceImpl implements PlaceRoomServiceI{
    @Autowired
    private PlaceRoomRepositoryI placeRoomRepository;

    @Override
    public Page<PlaceRoom> query4Page(PlaceRoom room, Pageable pageable) {
        return placeRoomRepository.query4Page(room,pageable);
    }

    @Override
    public boolean saveNewPlaceRoom(PlaceRoom room, AdminUser user) {
        return false;
    }

    @Override
    public boolean deleteRoom(String roomId, AdminUser user) {
        return false;
    }

    @Override
    public PlaceRoom query4Edit(String roomId) {
        return null;
    }

    @Override
    public boolean updatePlaceRoom(PlaceRoom room, AdminUser user) {
        return false;
    }

    @Override
    public boolean query4PlaceRoomNumberUnique(String roomNumber) {
        return placeRoomRepository.select4PlaceRoomNumberUnique(roomNumber);
    }

    @Override
    public List<PlaceRoom> queryBuildings() {
        return placeRoomRepository.selectBuildings();
    }

    @Override
    public List<PlaceDistinct> queryDistincts() {
        return placeRoomRepository.selectDistincts();
    }
}
