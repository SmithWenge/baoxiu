package nanqu.djtu.api.room.repository;

import nanqu.djtu.pojo.PlaceRoom;

import java.util.List;

public interface RoomApiRepositoryI {
    /**
     * 查询这个地点下的位置
     *
     * @param buildingId 地点Id
     * @return 如果存在返回list, else size 0 list
     */
    List<PlaceRoom> selectRoomByBuildingId(String buildingId);
}
