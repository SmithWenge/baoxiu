package nanqu.djtu.admin.maintenance.list.user.repository.impl;


import nanqu.djtu.admin.maintenance.list.user.repository.UserMaintenanceListRepositoryI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.PrimaryKeyUtil;
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
import java.util.Objects;

/**
 * Created by Administrator on 2017/4/4.
 */
@Repository
public class UserMaintenanceListRepositoryImpl implements UserMaintenanceListRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(UserMaintenanceListRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询校区信息列表
     * @return 所有未删除的校区信息
     */
    @Override
    public List<PlaceDistinct> query4ListPlaceDistinct() {
        String sql = "SELECT distinctId, distinctName, distinctNumber FROM baoxiu_placedistinct WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new Select4ListRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }



    private class Select4ListRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaceDistinct distinct = new PlaceDistinct();

            distinct.setDistinctId(rs.getString("distinctId"));
            distinct.setDistinctName(rs.getString("distinctName"));
            distinct.setDistinctNumber(rs.getString("distinctNumber"));

            return distinct;
        }
    }

    /**
     * 查询地点
     * @return
     */
    @Override
    public List<PlaceBuilding> selectBuildingsByDistinctId(String distinctId) {
        String sql = "SELECT buildingId, buildingName FROM baoxiu_placebuilding WHERE deleteFlag = 0 AND distinctId = ?";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectBuildingsRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceBuilding] selectBuildings error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }



    class SelectBuildingsRowMapper implements RowMapper<PlaceBuilding> {

        @Override
        public PlaceBuilding mapRow(ResultSet resultSet, int i) throws SQLException {
            PlaceBuilding building = new PlaceBuilding();

            building.setBuildingId(resultSet.getString("buildingId"));
            building.setBuildingName(resultSet.getString("buildingName"));

            return building;
        }
    }

    /**
     * 查询位置信息
     * @return 未删除的位置信息列表
     */
    @Override
    public List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId) {
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
     * 查询设备信息
     *
     * @return 未删除的设备信息
     */
    @Override
    public List<Equipment> queryEquipment() {
        String sql = "SELECT equipmentId , equipmentName,repairGroupId FROM baoxiu.baoxiu_equipment WHERE deleteFlag = 0;";
        Object[] args = {};

        try {
            return  jdbcTemplate.query(sql, args, new queryEquipmentRowMapper());
        }catch (Exception e) {
            LOG.error("[Equipment] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }


    private class queryEquipmentRowMapper implements RowMapper<Equipment> {

        @Override
        public Equipment  mapRow(ResultSet rs, int rowNum) throws SQLException {

            Equipment equipment = new Equipment();
            equipment.setEquipmentId(rs.getString("equipmentId"));
            equipment.setEquipmentName(rs.getString("equipmentName"));
            equipment.setRepairGroupId(rs.getString("repairGroupId"));

            return equipment;
        }
    }
    /**
     * 添加新的保修单
     *
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNew(MaintenanceList list) {
        String sql = "INSERT INTO baoxiu_maintenancelist (listNumber, userId, userName, repairGroupId, roomId, buildingId, distinctId, listDescription, equipmentId, listState) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                list.getListNumber(),
                list.getUserId(),
                list.getUserName(),
                list.getRepairGroupId(),
                list.getRoomId(),
                list.getBuildingId(),
                list.getDistinctId(),
                list.getListDescription(),
                list.getEquipmentId(),
                list.getListState()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[Maintenance] add new maintenance error with info {}.", e.getMessage());

            return false;
        }
    }


    /**
     * 查看这个设备的维修组
     *
     * @param equipmentId 设备Id
     * @return 返回这个设备的维修组Id
     */
    @Override
    public String selectRepairGroupId(String equipmentId) {
        String sql = "SELECT repairGroupId FROM baoxiu_equipment WHERE equipmentId = ?";
        Object[] args = {
                equipmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query equipment {}'s repair group id error with info {}.", equipmentId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个校区的编号
     *
     * @param distinctId 校区Id
     * @return 返回校区编号
     */
    @Override
    public String selectDistinctNumber(String distinctId) {
        String sql = "SELECT distinctNumber FROM baoxiu_placedistinct WHERE distinctId = ?";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query distinct {}'s number error with info {}.", distinctId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个地点的编号
     *
     * @param buildingId 地点Id
     * @return 这个地点的编号
     */
    @Override
    public String selectBuildingNumber(String buildingId) {
        String sql = "SELECT buildingNumber FROM baoxiu_placebuilding WHERE buildingId = ?";
        Object[] args = {
                buildingId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query building {}'s number error with info {}.", buildingId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个位置的编号
     *
     * @param roomId 位置Id
     * @return 这个位置的编号
     */
    @Override
    public String selectRoomNumber(String roomId) {
        String sql = "SELECT roomNumber FROM baoxiu_placeroom WHERE roomId = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[Maintenance] query room {}'s number error with info {}.", roomId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个设备的编号
     *
     * @param equipmentId 设备Id
     * @return 这个设备的编号
     */
    @Override
    public String selectEquipmentNumber(String equipmentId) {
        String sql = "SELECT equipmentNumber FROM baoxiu_equipment WHERE equipmentId = ?";
        Object[] args = {
                equipmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[Maintenance] query equipment {}'s number error with info {}.", equipmentId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个报修单是否存在
     *
     * @param listId 报修单编号
     * @return 如果不存在返回true, else false
     */
    @Override
    public boolean selectIfExistMaintenanceList(String listId) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_maintenancelist WHERE listNumber = ? AND deleteFLag = 0 AND listState != 1";
        Object[] args = {
                listId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            LOG.error("[Maintenance] query exit list error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 添加保修状态
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */

    @Override
    public boolean insertNewListState(MaintenanceList list) {
       String sql ="INSERT INTO baoxiu.baoxiu_liststatetime (liststatetimeid,listNumber, listState, liststatetime) VALUES (?,?,?,?)";
        Object[] args = {PrimaryKeyUtil.uuidPrimaryKey(),list.getListNumber(),list.getListState(),list.getListStatusTime() };

        try {
                return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e){
            LOG.error("[Maintenance] add new maintenance error with info {}.", e.getMessage());

            return false;

        }
    }
}
