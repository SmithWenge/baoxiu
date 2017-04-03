package nanqu.djtu.admin.equipment.repository.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.equipment.repository.EquipmentRepositoryI;
import nanqu.djtu.pojo.*;
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
public class EquipmentRepositoryImpl implements EquipmentRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Equipment> repositoryUtils;

    /**
     * 分页查询设备信息
     *
     * @param equipment 查询条件的信息
     * @param pageable 分页信息
     * @return 分页查询的数据
     */
    @Override
    public Page<Equipment> select4Page(Equipment equipment, Pageable pageable) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT E.equipmentId, E.equipmentName, E.equipmentNumber, RG.groupName,");
        builder.append(" RG.groupNumber FROM baoxiu_equipmentset ES LEFT JOIN baoxiu_equipment E ON ES.equipmentId =");
        builder.append(" E.equipmentId LEFT JOIN baoxiu_repairgroup RG ON E.repairGroupId = RG.repairGroupId WHERE");
        builder.append(" E.deleteFlag = 0");

        List<Object> argList = new ArrayList();

        if (!Strings.isNullOrEmpty(equipment.getSetId())) {
            builder.append(" AND ES.setId = ?");

            argList.add(equipment.getSetId());
        }

        Object[] args = argList.toArray();

        return repositoryUtils.select4Page(builder.toString(), pageable, args, new Select4PageRowMapper());
    }

    private class Select4PageRowMapper implements RowMapper<Equipment> {

        @Override
        public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {

            Equipment equipment = new Equipment();

            equipment.setEquipmentId(rs.getString("equipmentId"));
            equipment.setEquipmentName(rs.getString("equipmentName"));
            equipment.setEquipmentNumber(rs.getString("equipmentNumber"));
            equipment.setRepairGroupName(rs.getString("groupName"));
            equipment.setRepairGroupNumber(rs.getString("groupNumber"));

            return equipment;
        }
    }

    /**
     * 查询所有校区
     *
     * @return 所有校区数据
     */
    @Override
    public List<PlaceDistinct> selectAllPlaceDistincts() {
        String sql = "SELECT distinctId, distinctName FROM baoxiu_placedistinct WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllPlaceDistinctsRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select all place distincts error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectAllPlaceDistinctsRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceDistinct distinct = new PlaceDistinct();

            distinct.setDistinctId(rs.getString("distinctId"));
            distinct.setDistinctName(rs.getString("distinctName"));

            return distinct;
        }
    }

    /**
     * 查询这个校区下的地点
     *
     * @param distinctId 校区Id
     * @return 校区的所有地点
     */
    @Override
    public List<PlaceBuilding> selectBuildingWithDistinctId(String distinctId) {
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

    /**
     * 查询这个位置下的所有设备组
     *
     * @param roomId 位置Id
     * @return 这个位置下设备组数据
     */
    @Override
    public List<EquipmentSet> selectSetsWithRoomId(String roomId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT setId, setName FROM baoxiu_set WHERE setId = (SELECT setId FROM baoxiu_placeroom WHERE");
        builder.append(" roomId = ? AND deleteFlag = 0) AND deleteFlag = 0");
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.query(builder.toString(), args, new SelectSetsWithRoomIdRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select room {}'s sets error with info {}.", roomId, e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectSetsWithRoomIdRowMapper implements RowMapper<EquipmentSet> {

        @Override
        public EquipmentSet mapRow(ResultSet rs, int rowNum) throws SQLException {

            EquipmentSet set = new EquipmentSet();

            set.setSetId(rs.getString("setId"));
            set.setSetName(rs.getString("setName"));

            return set;
        }
    }

    /**
     * 查询所有的设备组
     *
     * @return 返回设备组对象List
     */
    @Override
    public List<EquipmentSet> selectAllEquipmentSets() {
        String sql = "SELECT setId, setName FROM baoxiu_set WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllEquipmentSetsRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select all equipment sets error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectAllEquipmentSetsRowMapper implements RowMapper<EquipmentSet> {

        @Override
        public EquipmentSet mapRow(ResultSet rs, int rowNum) throws SQLException {

            EquipmentSet set = new EquipmentSet();

            set.setSetId(rs.getString("setId"));
            set.setSetName(rs.getString("setName"));

            return set;
        }
    }

    /**
     * 查询所有的维修小组
     *
     * @return 返回维修小组对象List
     */
    @Override
    public List<RepairGroup> selectAllRepairGroup() {
        String sql = "SELECT repairGroupId, groupName FROM baoxiu_repairgroup WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllRepairGroupRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select all repair groups error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectAllRepairGroupRowMapper implements RowMapper<RepairGroup> {

        @Override
        public RepairGroup mapRow(ResultSet rs, int rowNum) throws SQLException {

            RepairGroup group = new RepairGroup();

            group.setRepairGroupId(rs.getString("repairGroupId"));
            group.setGroupName(rs.getString("groupName"));

            return group;
        }
    }

    /**
     * 查询判断设备编号的唯一
     *
     * @param equipmentNumber 设备编号
     * @return 如果不重复返回true, else false
     */
    @Override
    public boolean selectUniqueEquipmentNumber(String equipmentNumber) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_equipment WHERE equipmentNumber = ?";
        Object[] args = {
                equipmentNumber
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            LOG.error("[Equipment] select unique equipment number error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 保存新添加的设备
     *
     * @param equipment 设备对象
     * @return 添加成功返回true, else falase
     */
    @Override
    public boolean insertNewEquipment(Equipment equipment) {
        String sql = "INSERT INTO baoxiu_equipment (equipmentId, equipmentName, repairGroupId, equipmentNumber) VALUES (?, ?, ?, ?)";
        Object[] args = {
                equipment.getEquipmentId(),
                equipment.getEquipmentName(),
                equipment.getRepairGroupId(),
                equipment.getEquipmentNumber()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[Equipment] insert new equipment error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 添加设备与设备组关联表数据
     *
     * @param equipment 包含两个Id的设备对象
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewEquipmentWithSet(Equipment equipment) {
        String sql = "INSERT INTO baoxiu_equipmentset (setId, equipmentId) VALUES (?, ?)";
        Object[] args = {
                equipment.getSetId(),
                equipment.getEquipmentId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[Equipment] insert equipment set three table error with info {}.", e.getMessage());

            return false;
        }
    }
}
