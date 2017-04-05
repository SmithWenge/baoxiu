package nanqu.djtu.admin.maintenance.list.manage.repository.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.maintenance.list.manage.repository.MaintenanceLisRepositoryI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.util.RepositoryUtils;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MaintenanceLisRepositoryImpl implements MaintenanceLisRepositoryI {

    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceLisRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<MaintenanceList> repositoryUtils;

    @Override
    public Page<MaintenanceList> select4Page(MaintenanceList list, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT listNumber,listState,equipmentName,groupName,listTime FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_equipment AS E ON M.equipmentId = E.equipmentId LEFT JOIN baoxiu_repairgroup AS R ON M.repairGroupId = R.repairGroupId WHERE R.deleteFlag = 0");

        List<Object> argsList = new ArrayList<>();
        if (!Strings.isNullOrEmpty(list.getStartListTime())) {
            sql.append(" AND M.listTime >= ?");
            argsList.add(list.getStartListTime());
        }

        if (!Strings.isNullOrEmpty(list.getStopListTime())) {
            sql.append(" AND M.listTime <= ?");
            argsList.add(list.getStopListTime());
        }

        if (!Strings.isNullOrEmpty(list.getBuildingId())) {
            sql.append(" AND M.buildingId = ?");
            argsList.add(list.getBuildingId());
        }

        if (!Strings.isNullOrEmpty(list.getRoomId())) {
            sql.append(" AND M.roomId = ?");
            argsList.add(list.getRoomId());
        }

        if (!Strings.isNullOrEmpty(list.getEquipmentId())) {
            sql.append(" AND M.equipmentId = ?");
            argsList.add(list.getEquipmentId());
        }

        if (list.getListState() > 0) {
            sql.append(" AND M.listState = ?");
            argsList.add(list.getListState());
        }

        if (!Strings.isNullOrEmpty(list.getRepairGroupId())) {
            sql.append(" AND M.repairGroupId = ?");
            argsList.add(list.getRepairGroupId());
        }

        sql.append(" ORDER BY M.listTime DESC");
        Object[] args = argsList.toArray();

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowmapper());
    }

    class Query4PageRowmapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            list.setListNumber(resultSet.getString("listNumber"));
            list.setListState(String.valueOf(resultSet.getInt("listState")));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setGroupName(resultSet.getString("groupName"));
            list.setListTime(format.format(resultSet.getTimestamp("listTime")));

            return list;
        }
    }

    /**
     * 页面初始化填充下拉框
     * @return
     */
    @Override
    public List<MaintenanceList> selectDistincts() {
        String sql = "SELECT distinctId, distinctName FROM baoxiu_placedistinct WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectDistinctsRowMapper());
        } catch (Exception e) {
            LOG.error("[MaintenanceList] selectDistincts error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectDistinctsRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();

            list.setDistinctId(resultSet.getString("distinctId"));
            list.setDistinctName(resultSet.getString("distinctName"));

            return list;
        }
    }

    /**
     * 查询这个校区下的地点
     *
     * @param distinctId 校区Id
     * @return 校区的所有地点
     */
    @Override
    public List<PlaceBuilding> selectBuildingsWithDistinctId(String distinctId) {
        String sql = "SELECT buildingId, buildingName FROM baoxiu_placebuilding WHERE distinctId = ? AND deleteFlag = 0";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectBuildingWithDistinctIdRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select distinct {}'s buildings error with info {}.", distinctId, e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectBuildingWithDistinctIdRowMapper implements RowMapper<PlaceBuilding> {

        @Override
        public PlaceBuilding mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceBuilding building = new PlaceBuilding();

            building.setBuildingId(rs.getString("buildingId"));
            building.setBuildingName(rs.getString("buildingName"));

            return building;
        }
    }

    /**
     * 查询这个地点下的所有位置
     *
     * @param buildingId 地点Id
     * @return 这个地点下位置数据
     */
    @Override
    public List<PlaceRoom> selectRoomWithBuildingId(String buildingId) {
        String sql = "SELECT roomId, roomName FROM baoxiu_placeroom WHERE buildingId = ? AND deleteFlag = 0";
        Object[] args = {
                buildingId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectRoomWithBuildingIdRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select building {}'s rooms error with info {}.", buildingId, e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectRoomWithBuildingIdRowMapper implements RowMapper<PlaceRoom> {

        @Override
        public PlaceRoom mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceRoom room = new PlaceRoom();

            room.setRoomId(rs.getString("roomId"));
            room.setRoomName(rs.getString("roomName"));

            return room;
        }
    }

    @Override
    public List<Equipment> selectEquipmentsWithBuildingId(PlaceRoom room) {
        String sql = "SELECT equipmentId,equipmentName FROM baoxiu_equipment WHERE equipmentId IN (SELECT ES.equipmentId FROM baoxiu_set AS S LEFT JOIN baoxiu_equipmentset AS ES ON s.setId = ES.setId WHERE S.setId IN (SELECT S.setId FROM baoxiu_set AS S LEFT JOIN baoxiu_placebuilding AS B ON s.setId = B.setId WHERE buildingId = ? AND S.deleteFlag = 0))";
        Object[] args = {
                room.getBuildingId()
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectEquipmentsWithBuildingIdRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select building {}'s rooms error with info {}.", room.getBuildingId(), e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectEquipmentsWithBuildingIdRowMapper implements RowMapper<Equipment> {

        @Override
        public Equipment mapRow(ResultSet resultSet, int i) throws SQLException {
            Equipment equipment = new Equipment();

            equipment.setEquipmentId(resultSet.getString("equipmentId"));
            equipment.setEquipmentName(resultSet.getString("equipmentName"));

            return equipment;
        }
    }

    @Override
    public List<Equipment> selectEquipmentsWithRoomId(PlaceRoom room) {
        String sql = "SELECT equipmentId,equipmentName FROM baoxiu_equipment WHERE equipmentId IN (SELECT ES.equipmentId FROM baoxiu_set AS S LEFT JOIN baoxiu_equipmentset AS ES ON s.setId = ES.setId WHERE S.setId IN (SELECT S.setId FROM baoxiu_set AS S LEFT JOIN baoxiu_placeroom AS R ON s.setId = R.setId WHERE roomId = ? AND S.deleteFlag = 0))";
        Object[] args = {
                room.getRoomId()
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectEquipmentsWithBuildingIdRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select building {}'s rooms error with info {}.", room.getBuildingId(), e.getMessage());

            return new ArrayList<>();
        }
    }

    @Override
    public List<MaintenanceList> selectGroups() {
        String sql = "SELECT repairGroupId, groupName FROM baoxiu_repairgroup WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectGroupsRowMapper());
        } catch (Exception e) {
            LOG.error("[MaintenanceList] selectDistincts error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectGroupsRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();

            list.setRepairGroupId(resultSet.getString("repairGroupId"));
            list.setGroupName(resultSet.getString("groupName"));

            return list;
        }
    }
}
