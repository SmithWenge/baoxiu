package nanqu.djtu.app.user.repository.impl;

import com.google.common.base.Strings;
import nanqu.djtu.app.user.repository.UserAppRepositoryI;
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

@Repository
public class UserAppRepositoryImpl implements UserAppRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(UserAppRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询校区信息列表
     *
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
     *
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
     *
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
     * 添加新的保修单
     *
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNew(MaintenanceList list) {
        String sql = "INSERT INTO baoxiu_maintenancelist (listNumber, userId, userTel, repairGroupId, roomId, buildingId, distinctId, equipmentId, listState,listDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                list.getListNumber(),
                list.getUserId(),
                list.getUserTel(),
                list.getRepairGroupId(),
                list.getRoomId(),
                list.getBuildingId(),
                list.getDistinctId(),
                list.getEquipmentId(),
                list.getListState(),
                list.getListDescription()
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
        Object[] args = {equipmentId};
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
        Object[] args = {distinctId};
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
        Object[] args = {buildingId};
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
        Object[] args = {roomId};
        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query room {}'s number error with info {}.", roomId, e.getMessage());

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
        Object[] args = {equipmentId};
        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query equipment {}'s number error with info {}.", equipmentId, e.getMessage());

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
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_maintenancelist WHERE listNumber = ? AND deleteFLag = 0 ";
        Object[] args = {listId};
        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            LOG.error("[API] query exit list error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 添加保修状态
     *
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewListState(MaintenanceList list) {
        String sql = "INSERT INTO baoxiu_liststatetime (liststatetimeid, listNumber, listState, listDescription) VALUES (?, ?, ?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                list.getListNumber(),
                list.getListState(),
                "提交成功"
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[API] add new maintenance error with info {}.", e.getMessage());

            return false;

        }
    }
    /**
     * 通过roomID查询设备信息
     * @param roomId
     * @return 设备
     */
    @Override
    public List<Equipment> queryPlaceRoomByRoomId(String roomId) {
        String sql = "SELECT equipmentId,equipmentName FROM baoxiu_equipment WHERE roomId = ?";
        Object[] args = {roomId};

        try {
            return  jdbcTemplate.query(sql, args, new QueryPlaceRoomByRoomIdRowMapper());
        }catch (Exception e){
            LOG.error("[API] select equipment error with info {}.", e.getMessage());
            return new ArrayList<>();
        }
    }


    private class QueryPlaceRoomByRoomIdRowMapper implements RowMapper<Equipment> {

        @Override
        public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {

            Equipment equipment = new Equipment();
            equipment.setEquipmentId(rs.getString("equipmentId"));
            equipment.setEquipmentName(rs.getString("equipmentName"));


            return equipment;
        }
    }

    /**
     * 通过所以id查询所有名字
     * @param maintenanceList
     * @return
     */
    @Override
    public MaintenanceList selectAllName(MaintenanceList maintenanceList) {
        String sql = "SELECT buildingName,distinctName,roomName,equipmentName FROM baoxiu_placedistinct AS DI LEFT JOIN baoxiu_placebuilding AS BU ON DI.distinctId = BU.distinctId LEFT JOIN baoxiu_placeroom AS RO ON BU.buildingId = RO.buildingId LEFT JOIN baoxiu_equipment AS EQ ON RO.roomId = EQ.roomId  WHERE DI.deleteFlag = 0 AND DI.distinctId = ? AND BU.deleteFlag = 0 AND BU.buildingId =? AND RO.deleteFlag = 0 AND RO.roomId = ? AND EQ.deleteFlag = 0 AND EQ.equipmentId = ?";
        Object[] args = {
                maintenanceList.getDistinctId(),
                maintenanceList.getBuildingId(),
                maintenanceList.getRoomId(),
                maintenanceList.getEquipmentId()
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectAllNameRowMapper());
        }catch (Exception e) {
            LOG.error("[API] select all name error with info {}.", e.getMessage());
            return null;
        }
    }

    private class SelectAllNameRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet rs, int rowNum) throws SQLException {

            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setDistinctName(rs.getString("distinctName"));
            maintenanceList.setBuildingName(rs.getString("buildingName"));
            maintenanceList.setRoomName(rs.getString("roomName"));
            maintenanceList.setEquipmentName(rs.getString("equipmentName"));

            return maintenanceList;
        }
    }

    /**
     * 通过电话查保修单
     * @param userTel
     * @return
     */
    @Override
    public List<MaintenanceList> selectMaintenanceListByTel(String userTel) {
        String sql = "SELECT listNumber, repairGroupId, roomId, buildingId, userTel, listState, distinctId, equipmentId, liststatetime, listDescription FROM baoxiu_maintenancelist WHERE userTel = ? AND deleteFlag = 0";
        Object[] args = {
                userTel
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectMaintenanceListByTelRowMapper());
        } catch (Exception e) {
            LOG.error("[API] select maintenances error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }


    private class SelectMaintenanceListByTelRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet rs, int rowNum) throws SQLException {

            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setListNumber(rs.getString("listNumber"));
            maintenanceList.setUserTel(rs.getString("userTel"));
            maintenanceList.setListState(rs.getString("listState"));
            maintenanceList.setListstatetime(rs.getString("liststatetime"));
            maintenanceList.setListDescription(rs.getString("listDescription"));
            maintenanceList.setRepairGroupId(rs.getString("repairGroupId"));
            maintenanceList.setRoomId(rs.getString("roomId"));
            maintenanceList.setBuildingId(rs.getString("buildingId"));
            maintenanceList.setDistinctId(rs.getString("distinctId"));
            maintenanceList.setEquipmentId(rs.getString("equipmentId"));


            return maintenanceList;
        }
    }

    /**
     * 通过保修单号查保修单详情
     * @param listNumber
     * @return
     */
    @Override
    public MaintenanceList selectOneMaintenance(String listNumber) {
        String sql = "SELECT listNumber, userTel, repairGroupId, roomId, buildingId, distinctId, equipmentId, listState, liststatetime,listDescription FROM baoxiu_maintenancelist WHERE listNumber = ?";
        Object[] args = {
                listNumber
        };

        try {
                return jdbcTemplate.queryForObject(sql, args, new SelectMaintenanceListByTelRowMapper());
        }catch (Exception e){
            LOG.error("[API] select oneMaintenances error with info {}.", e.getMessage());
            return null;
        }
    }

    @Override
    public int sumOfMaintenance() {
        String sql = "SELECT count(listNumber) FROM baoxiu_maintenancelist WHERE listState < 7;";
        Object[] args = {};

        try{
            return jdbcTemplate.queryForInt(sql, args);
        }catch (Exception e){
            LOG.error("[API] select sumOfMaintenance error with info {}.", e.getMessage());
            return 0;
        }
    }

    /**
     * 根据保修单号查日期
     * @param listNumber
     * @return
     */
    @Override
    public String selectListStateTime(String listNumber) {
        String sql = "SELECT liststatetime FROM baoxiu.baoxiu_maintenancelist WHERE listNumber = ?";
        Object[] args = {listNumber};
        try {
            return jdbcTemplate.queryForObject(sql, args, String.class );
        }catch (Exception e){
            LOG.error("[API] select selectListStateTime error with info {}.", e.getMessage());
            return null;
        }
    }


    /**
     * 根据保修单查保修单所有状态
     * @param listNumber
     * @return
     */
    @Override
    public List<MaintenanceList> selectAllState(String listNumber) {
        String sql = "SELECT listNumber, listState, liststatetime, listDescription FROM baoxiu_liststatetime where listNumber= ? ORDER BY liststatetime";
        Object[] args = {listNumber};

        try {
            return  jdbcTemplate.query(sql,args, new SelectAllStateRowMapper());
        }catch (Exception e){
            LOG.error("[API] select selectAllState error with info {}.", e.getMessage());
            return null;
        }
    }

    private class SelectAllStateRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet rs, int rowNum) throws SQLException {

            MaintenanceList maintenanceList = new MaintenanceList();
            maintenanceList.setListNumber(rs.getString("listNumber"));
            maintenanceList.setListState(rs.getString("listState"));
            maintenanceList.setListstatetime(rs.getString("liststatetime"));
            maintenanceList.setListDescription(rs.getString("listDescription"));

            return maintenanceList;
        }
    }

}




