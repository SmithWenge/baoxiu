package nanqu.djtu.admin.maintenance.list.manage.repository.impl;

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
import java.util.ArrayList;
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
        if (list.getDistinctId() != null && !list.getDistinctId().equals("")) {
            sql.append(" AND M.distinctId = ?");
            argsList.add(list.getDistinctId());
        }

        if (list.getBuildingId() != null && !list.getBuildingId().equals("")) {
            sql.append(" AND M.buildingId = ?");
            argsList.add(list.getBuildingId());
        }

        if (list.getRoomId() != null && !list.getRoomId().equals("")) {
            sql.append(" AND M.roomId = ?");
            argsList.add(list.getRoomId());
        }

        if (list.getEquipmentId() != null && !list.getEquipmentId().equals("")) {
            sql.append(" AND M.equipmentId = ?");
            argsList.add(list.getEquipmentId());
        }

        if (list.getListState() > 0) {
            sql.append(" AND M.listState = ?");
            argsList.add(list.getListState());
        }

        if (list.getRepairGroupId() != null && !list.getRepairGroupId().equals("")) {
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

            list.setListNumber(resultSet.getString("listNumber"));
            list.setListState(String.valueOf(resultSet.getInt("listState")));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setGroupName(resultSet.getString("groupName"));
            list.setListTime(resultSet.getTimestamp("listTime"));

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
        String sql = "SELECT E.equipmentId, E.equipmentName FROM  baoxiu_equipment AS E LEFT JOIN baoxiu_equipmentset AS ES ON E.equipmentId = ES.equipmentId LEFT JOIN  baoxiu_set AS S ON ES.setId = s.setId IN (SELECT S.setId FROM baoxiu_set AS S LEFT JOIN baoxiu_placebuilding AS B ON s.setId = B.setId WHERE buildingId = ? AND S.deleteFlag = 0)";

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
        String sql = "SELECT E.equipmentId, E.equipmentName FROM  baoxiu_equipment AS E LEFT JOIN baoxiu_equipmentset AS ES ON E.equipmentId = ES.equipmentId LEFT JOIN  baoxiu_set AS S ON ES.setId = s.setId IN (SELECT S.setId FROM baoxiu_set AS S LEFT JOIN baoxiu_placeroom AS R ON s.setId = R.setId WHERE roomId = ? AND S.deleteFlag = 0)";

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
}
