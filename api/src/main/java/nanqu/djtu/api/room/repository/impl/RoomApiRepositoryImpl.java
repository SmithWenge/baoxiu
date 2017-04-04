package nanqu.djtu.api.room.repository.impl;

import nanqu.djtu.api.room.repository.RoomApiRepositoryI;
import nanqu.djtu.pojo.PlaceRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomApiRepositoryImpl implements RoomApiRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(RoomApiRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询这个地点下的位置
     *
     * @param buildingId 地点Id
     * @return 如果存在返回list, else size 0 list
     */
    @Override
    public List<PlaceRoom> selectRoomByBuildingId(String buildingId) {
        String sql = "SELECT roomId, roomName FROM baoxiu_placeroom WHERE buildingId = ? AND deleteFlag = 0";
        Object[] args = {
                buildingId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRoomByBuildingIdRowMapper());
        } catch (Exception e) {
            LOG.error("[API] query buidling {}'s rooms error with info {}.", buildingId, e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectRoomByBuildingIdRowMapper implements RowMapper<PlaceRoom> {

        @Override
        public PlaceRoom mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceRoom room = new PlaceRoom();

            room.setRoomId(rs.getString("roomId"));
            room.setRoomName(rs.getString("roomName"));

            return room;
        }
    }
}
