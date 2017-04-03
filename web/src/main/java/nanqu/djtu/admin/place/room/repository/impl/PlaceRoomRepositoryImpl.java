package nanqu.djtu.admin.place.room.repository.impl;

import nanqu.djtu.admin.place.room.repository.PlaceRoomRepositoryI;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.PlaceRoom;
import nanqu.djtu.util.RepositoryUtils;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaceRoomRepositoryImpl implements PlaceRoomRepositoryI{
    private static final Logger LOG = LoggerFactory.getLogger(PlaceRoomRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<PlaceRoom> repositoryUtils;

    /**
     * 分页查询位置信息列表
     *
     * @return 所有未删除位置的信息
     */
    @Override
    public Page<PlaceRoom> query4Page(PlaceRoom room, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT roomId,roomName,distinctName,buildingName,setName,roomNumber FROM baoxiu_placeroom AS R LEFT JOIN baoxiu_placebuilding AS B ON R.buildingId = B.buildingId LEFT JOIN baoxiu_placedistinct AS D ON B.distinctId = D.distinctId LEFT JOIN baoxiu_set AS S ON R.setId = S.setId WHERE R.deleteFlag = 0");

        List<Object> list = new ArrayList<>();
        if (room.getDistinctId() != null && !room.getDistinctId().equals("")) {
            sql.append(" AND D.distinctId = ?");
            list.add(room.getDistinctId());
        }

        if (room.getBuildingId() != null && !room.getBuildingId().equals("")) {
            sql.append(" AND B.buildingId = ?");
            list.add(room.getBuildingId());
        }

        sql.append(" ORDER BY roomId DESC");
        Object[] args = list.toArray();

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Select4PageRowMapper());
    }

    class Select4PageRowMapper implements RowMapper<PlaceRoom> {
        //roomId,roomName,distinctName,buildingName,setName,roomNumber
        @Override
        public PlaceRoom mapRow(ResultSet resultSet, int i) throws SQLException {
            PlaceRoom room = new PlaceRoom();

            room.setRoomId(resultSet.getString("roomId"));
            room.setRoomName(resultSet.getString("roomName"));
            room.setDistinctName(resultSet.getString("distinctName"));
            room.setBuildingName(resultSet.getString("buildingName"));
            room.setSetName(resultSet.getString("setName"));
            room.setRoomNumber(resultSet.getString("roomNumber"));

            return room;
        }
    }

    /**
     * 添加新的位置
     * @param room 新位置的信息
     * @return
     */
    @Override
    public boolean insertNewPlaceRoom(PlaceRoom room) {
        String sql = "INSERT INTO baoxiu_placeroom (roomId, roomName, buildingId, setId, roomNumber,) VALUES (?, ?, ?, ?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                room.getRoomName(),
                room.getBuildingId(),
                room.getSetId(),
                room.getRoomNumber()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceRoom] add new place room error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 删除位置
     * @param roomId 位置Id
     * @return
     */
    @Override
    public boolean deletePlaceRoom(String roomId) {
        String sql = "UPDATE baoxiu_placeroom SET deleteFlag = 1 WHERE roomId = ? AND deleteFlag = 0";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceRoom] delete place room error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 编辑前查找
     * @param roomId 位置Id
     * @return
     */
    @Override
    public PlaceRoom select4Edit(String roomId) {
        String sql = "SELECT roomId,roomName,distinctName,buildingName,setName,roomNumber FROM baoxiu_placeroom AS R LEFT JOIN baoxiu_placebuilding AS B ON R.buildingId = B.buildingId LEFT JOIN baoxiu_placedistinct AS D ON B.distinctId = D.distinctId LEFT JOIN baoxiu_set AS S ON R.setId = S.setId WHERE R.deleteFlag = 0 AND R.roomId = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4PageRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceRoom] query4Edit error with info {}.", e.getMessage());

            return null;
        }
    }

    /**
     * 编辑位置信息
     * @param room 新的更改后的位置信息
     * @return
     */
    @Override
    public boolean updatePlaceRoom(PlaceRoom room) {
        String sql = "UPDATE baoxiu_placeroom SET roomName = ?, buildingId = ?, setId = ?, roomNumber = ? WHERE roomId = ? AND deleteFlag = 0";
        Object[] args = {
                room.getRoomName(),
                room.getBuildingId(),
                room.getSetId(),
                room.getRoomNumber(),
                room.getRoomId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceRoom] update place room error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 验证位置编号是否唯一
     * @param roomNumber 位置编号
     * @return
     */
    @Override
    public boolean select4PlaceRoomNumberUnique(String roomNumber) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_placeroom WHERE roomNumber = ?";
        Object[] args = {
                roomNumber
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询地点
     * @return
     */
    @Override
    public List<PlaceRoom> selectBuildings() {
        String sql = "SELECT buildingId, buildingName FROM baoxiu_placebuilding WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectBuildingsRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceBuilding] selectBuildings error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectBuildingsRowMapper implements RowMapper<PlaceRoom> {

        @Override
        public PlaceRoom mapRow(ResultSet resultSet, int i) throws SQLException {
            PlaceRoom room = new PlaceRoom();

            room.setBuildingId(resultSet.getString("buildingId"));
            room.setBuildingName(resultSet.getString("buildingName"));

            return room;
        }
    }

    /**
     * 查询校区
     * @return
     */
    @Override
    public List<PlaceDistinct> selectDistincts() {
        String sql = "SELECT distinctId, distinctName FROM baoxiu_placedistinct WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectDistinctsRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] selectDistincts error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectDistinctsRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet resultSet, int i) throws SQLException {
            PlaceDistinct distinct = new PlaceDistinct();

            distinct.setDistinctId("distinctId");
            distinct.setDistinctName("distinctName");

            return distinct;
        }
    }
}
