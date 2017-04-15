package nanqu.djtu.admin.maintenance.list.manage.repository.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.maintenance.list.manage.repository.MaintenanceLisRepositoryI;
import nanqu.djtu.dictionary.feature.manager.IDictionaryManager;
import nanqu.djtu.dictionary.feature.manager.impl.DefaultDictionaryManager;
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
import java.text.SimpleDateFormat;
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
        StringBuilder sql = new StringBuilder("SELECT listNumber,listState,equipmentName,groupName,liststatetime FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_equipment AS E ON M.equipmentId = E.equipmentId LEFT JOIN baoxiu_repairgroup AS R ON M.repairGroupId = R.repairGroupId WHERE R.deleteFlag = 0");
        List<Object> argsList = new ArrayList<>();
        if (!Strings.isNullOrEmpty(list.getStartListTime())) {
            sql.append(" AND M.liststatetime >= ?");
            argsList.add(list.getStartListTime());
        }

        if (!Strings.isNullOrEmpty(list.getStopListTime())) {
            sql.append(" AND M.liststatetime <= ?");
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

        if (!Strings.isNullOrEmpty(list.getListstateStr())) {
            sql.append(" AND M.listState = ?");
            argsList.add(list.getListstateStr());
        }

        if (!Strings.isNullOrEmpty(list.getRepairGroupId())) {
            sql.append(" AND M.repairGroupId = ?");
            argsList.add(list.getRepairGroupId());
        }

        sql.append(" ORDER BY M.liststatetime DESC");
        Object[] args = argsList.toArray();

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowmapper());
    }

    class Query4PageRowmapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            IDictionaryManager dictionary = DefaultDictionaryManager.getInstance();
            int listState = resultSet.getInt("listState");

            list.setListNumber(resultSet.getString("listNumber"));
            list.setListState(String.valueOf(listState));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setGroupName(resultSet.getString("groupName"));
            list.setListstatetime(format.format(resultSet.getTimestamp("liststatetime")));
            list.setListstateStr(dictionary.dictionary(listState, "listState").getItemValue());

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
        String sql = "SELECT equipmentId,equipmentName FROM baoxiu_equipment WHERE roomId = ? AND deleteFlag = 0";
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

    @Override
    public MaintenanceList select4details(String listNumber) {
        String sql = "SELECT listNumber,userTel,groupName,roomName,buildingName,distinctName,equipmentName,listState,listDescription,listPicture FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_repairgroup AS R ON M.repairGroupId = R.repairGroupId LEFT JOIN baoxiu_placeroom AS PR ON M.roomId = PR.roomId LEFT JOIN baoxiu_placebuilding AS PB ON M.buildingId = PB.buildingId LEFT JOIN baoxiu_placedistinct AS PD ON M.distinctId = PD.distinctId LEFT JOIN baoxiu_equipment AS E ON M.equipmentId = E.equipmentId WHERE listNumber = ?";
        Object[] args = {
                listNumber
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4detailsRowMapper());
        } catch (Exception e) {
            LOG.error("[MaintenanceList] query4details error with info {}.", e.getMessage());

            return null;
        }
    }
    /**
     * 更改状态为已派单
     * @param listNumber
     * @return
     */
    @Override
    public boolean updateliststate(String listNumber) {
        String sql="UPDATE baoxiu_maintenancelist SET listState =2 WHERE listNumber = ? AND deleteFlag = 0";
        Object[] args = {
                listNumber
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[ListNumber] update listState error with info {}.", e.getMessage());

            return false;
        }
    }
    @Override
    public boolean insertliststate(String listNumber) {
        String sql = "INSERT INTO baoxiu.baoxiu_liststatetime (liststatetimeid, listNumber, listState ) VALUES ( ?,?,2 )";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                listNumber


        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[ListNumber] add new liststate error with info {}.", e.getMessage());

            return false;
        }

    }

    /**
     * 更改状态为已完成
     * @param listNumber
     * @return
     */
    @Override
    public boolean updatestate(String listNumber) {
        String sql="UPDATE baoxiu_maintenancelist SET listState =8 WHERE listNumber = ? AND deleteFlag = 0";
        Object[] args = {
                listNumber
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[ListNumber] update listState error with info {}.", e.getMessage());

            return false;
        }
    }

    @Override
    public boolean insertstate(String listNumber) {
        String sql = "INSERT INTO baoxiu.baoxiu_liststatetime (liststatetimeid, listNumber, listState ) VALUES ( ?,?,8 )";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                listNumber,



        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[ListNumber] add new liststate error with info {}.", e.getMessage());

            return false;
        }
    }



    class Select4detailsRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();
            IDictionaryManager dictionary = DefaultDictionaryManager.getInstance();

            int listState = resultSet.getInt("listState");
            String groupName = resultSet.getString("groupName");
            String roomName = resultSet.getString("roomName");
            String buildingName = resultSet.getString("buildingName");
            String distinctName = resultSet.getString("distinctName");
            String listDescription = resultSet.getString("listDescription");
            String listPicture = resultSet.getString("listPicture");


            list.setGroupName(Strings.isNullOrEmpty(groupName) ? "无" : groupName);
            list.setRoomName(Strings.isNullOrEmpty(roomName) ? "无" : roomName);
            list.setBuildingName(Strings.isNullOrEmpty(buildingName) ? "无" : buildingName);
            list.setDistinctName(Strings.isNullOrEmpty(distinctName) ? "无" : distinctName);
            list.setListDescription(Strings.isNullOrEmpty(listDescription) ? "无" : listDescription);
            list.setListPicture(Strings.isNullOrEmpty(listPicture) ? "default_list.png" : listPicture);

            list.setListNumber(resultSet.getString("listNumber"));
            list.setUserTel(resultSet.getString("userTel"));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setListState(String.valueOf(resultSet.getInt("listState")));
            list.setListstateStr(dictionary.dictionary(listState,"listState").getItemValue());

            return list;
        }
    }

    @Override
    public List<MaintenanceList> selectStatusWithListNum(String listNumber) {
        String sql = "SELECT listState,liststatetime FROM baoxiu_liststatetime WHERE listNumber = ? AND deleteFlag = 0 ORDER BY liststatetime DESC";
        Object[] args = {
                listNumber
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectStatusWithListNumRowMapper());
        } catch (Exception e) {
            LOG.error("[MaintenanceList] select status {}'s MaintenanceList error with info {}.", listNumber, e.getMessage());

            MaintenanceList list = new MaintenanceList();
            List<MaintenanceList> lists = new ArrayList<>();
            list.setListstateStr("暂无更新");
            list.setListstatetime("暂无更新");
            lists.add(list);

            return lists;
        }
    }

    class SelectStatusWithListNumRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            IDictionaryManager dictionary = DefaultDictionaryManager.getInstance();

            list.setListstateStr(dictionary.dictionary(resultSet.getInt("listState"), "listState").getItemValue());
            list.setListstatetime(format.format(resultSet.getTimestamp("liststatetime")));

            return list;
        }
    }

    /**
     * 编辑报修单
     * @param list
     * @return
     */
    @Override
    public Boolean updateMaintenanceList(MaintenanceList list) {
        String sql = "UPDATE baoxiu_maintenancelist SET userTel = ?, repairGroupId = ?, distinctId = ?, buildingId = ?, roomId = ?, equipmentId = ?, listDescription = ? WHERE listNumber = ? AND deleteFlag = 0";
        Object[] args = {
                list.getUserTel(),
                list.getRepairGroupId(),
                list.getDistinctId(),
                list.getBuildingId(),
                list.getRoomId(),
                list.getEquipmentId(),
                list.getListDescription(),
                list.getListNumber()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[maintenanceList] update maintenanceList {} error with info {}.", list.getListNumber(), e.getMessage());

            return false;
        }
    }
}
