package nanqu.djtu.api.room.service.impl;

import nanqu.djtu.api.room.repository.RoomApiRepositoryI;
import nanqu.djtu.api.room.service.RoomApiServiceI;
import nanqu.djtu.pojo.PlaceRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomApiServiceImpl implements RoomApiServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(RoomApiServiceImpl.class);

    @Autowired
    private RoomApiRepositoryI roomApiRepository;

    @Override
    public List<PlaceRoom> queryRoomByBuildingId(String buildingId) {
        return roomApiRepository.selectRoomByBuildingId(buildingId);
    }
}
