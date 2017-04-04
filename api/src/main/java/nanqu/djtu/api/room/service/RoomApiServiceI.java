package nanqu.djtu.api.room.service;

import nanqu.djtu.pojo.PlaceRoom;

import java.util.List;

public interface RoomApiServiceI {
    List<PlaceRoom> queryRoomByBuildingId(String buildingId);
}
