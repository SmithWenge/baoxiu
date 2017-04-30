package nanqu.djtu.app.worker.repository.impl;

import com.google.common.base.Strings;
import nanqu.djtu.app.util.MaintenanceListStateToStringUtil;
import nanqu.djtu.app.worker.repository.WorkerRepositoryI;
import nanqu.djtu.dictionary.feature.manager.IDictionaryManager;
import nanqu.djtu.dictionary.feature.manager.impl.DefaultDictionaryManager;
import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Repository
public class WorkerRepositoryImpl implements WorkerRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 工人登录查询
     * @param workerTel
     * @param workerPass
     * @return
     */
    @Override
    public WorkerInfo workerLogin(String workerTel, String workerPass) {
        String sql = "SELECT userId,workerName,workerUnit,workerDepartment,workerJob,workerState,repairGroupId,typeId,workerTel,workerPass FROM baoxiu_workerinfo WHERE workerTel = ? AND workerPass = ? AND deleteFlag = 0";
        Object[] args = {
                workerTel,
                workerPass
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new WorkerLoginRowMapper());
        } catch (Exception e) {
            LOG.error("[WorkerInfo] select error with info {}.", e.getMessage());

            return null;
        }
    }

    private class WorkerLoginRowMapper implements RowMapper<WorkerInfo> {

        @Override
        public WorkerInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            WorkerInfo info = new WorkerInfo();

            info.setUserId(resultSet.getString("userId"));
            info.setWorkerName(resultSet.getString("workerName"));
            info.setWorkerUnite(resultSet.getString("workerUnit"));
            info.setWorkerDepartment(resultSet.getString("workerDepartment"));
            info.setWorkerJob(resultSet.getString("workerJob"));
            info.setWorkerStateInt(resultSet.getInt("workerState"));
            info.setRepairGroupId(resultSet.getString("repairGroupId"));
            info.setTypeId(resultSet.getString("typeId"));
            info.setWorkerTel(resultSet.getString("workerTel"));
            info.setWorkerPass(resultSet.getString("workerPass"));

            return info;
        }
    }

    /**
     * 根据状态查询维修单列表
     * @param listState, userId
     * @return
     */
    @Override
    public List<MaintenanceList> selectMaintenanceListByState(int listState, String userId) {
        String sql = "SELECT listNumber, liststatetime, listDescription FROM baoxiu_maintenancelist WHERE listState = ? AND repairGroupId IN (SELECT repairGroupId FROM baoxiu_workerinfo WHERE userId = ? AND deleteFlag = 0)";
        Object[] args = {
                listState,
                userId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectMaintenanceListByStateRowMapper());
        } catch (Exception e) {
            LOG.error("[workerManage] selectMaintenanceListByState error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    @Override
    public List<MaintenanceList> selectDoingMaintenanceList(String userId) {
        String sql = "SELECT listNumber,userTel, liststatetime, listState, listDescription, DI.distinctName, BU.buildingName, RO.roomName, EQ.equipmentName FROM baoxiu_maintenancelist AS MA LEFT JOIN baoxiu_placedistinct AS DI  ON MA.distinctId = DI.distinctId LEFT JOIN baoxiu_placebuilding AS BU ON MA.buildingId = BU.buildingId LEFT JOIN baoxiu_placeroom AS RO ON MA.roomId = RO.roomId LEFT JOIN baoxiu_equipment AS EQ ON MA.equipmentId = EQ.equipmentId WHERE MA.repairGroupId IN (SELECT baoxiu_workerinfo.repairGroupId FROM baoxiu_workerinfo WHERE baoxiu_workerinfo.userId = ? AND baoxiu_workerinfo.deleteFlag = 0) AND MA.deleteFlag = 0 AND MA.listState = 2";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectMaintenanceListByStateRowMapper());
        } catch (Exception e) {
            LOG.error("[workerManage] selectMaintenanceListByState error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    @Override
    public List<MaintenanceList> selectWaitingMaintenanceList(String userId) {
        String sql = "SELECT listNumber,userTel, liststatetime, listState, listDescription, DI.distinctName, BU.buildingName, RO.roomName, EQ.equipmentName FROM baoxiu_maintenancelist AS MA LEFT JOIN baoxiu_placedistinct AS DI  ON MA.distinctId = DI.distinctId LEFT JOIN baoxiu_placebuilding AS BU ON MA.buildingId = BU.buildingId LEFT JOIN baoxiu_placeroom AS RO ON MA.roomId = RO.roomId LEFT JOIN baoxiu_equipment AS EQ ON MA.equipmentId = EQ.equipmentId WHERE MA.repairGroupId IN (SELECT baoxiu_workerinfo.repairGroupId FROM baoxiu_workerinfo WHERE baoxiu_workerinfo.userId = ? AND baoxiu_workerinfo.deleteFlag = 0) AND MA.deleteFlag = 0 AND MA.listState = 1";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectMaintenanceListByStateRowMapper());
        } catch (Exception e) {
            LOG.error("[workerManage] selectMaintenanceListByState error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectMaintenanceListByStateRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            MaintenanceList list = new MaintenanceList();
            list.setListNumber(resultSet.getString("listNumber"));
            list.setUserTel(resultSet.getString("userTel"));
            list.setDistinctName(resultSet.getString("distinctName"));
            list.setBuildingName(resultSet.getString("buildingName"));
            list.setRoomName(resultSet.getString("roomName"));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setListstatetime(resultSet.getString("liststatetime"));
            list.setListBigDescription(resultSet.getString("listDescription"));

            String listState = resultSet.getString("listState");

            list.setListState(listState);
            list.setListStateFrontStyleColor(MaintenanceListStateToStringUtil.stateNumberToColorString(listState));

            return list;
        }
    }

//    /**
//     * 根据状态查询维修单列表
//     * @param listState,userId
//     * @return
//     */
//    @Override
//    public List<MaintenanceList> selectListStateTimesByState(int listState, String userId) {
//        String sql = "SELECT L.listNumber,L.liststatetime,L.listDescription FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_liststatetime AS L ON M.listNumber = L.listNumber WHERE L.listState = 1 AND M.listState = ? AND repairGroupId IN (SELECT repairGroupId FROM baoxiu_workerinfo WHERE userId = ? AND deleteFlag = 0)";
//        Object[] args = {
//                listState,
//                userId
//        };
//
//        try {
//            return jdbcTemplate.query(sql, args, new SelectListStateTimesByStateRowMapper());
//        } catch (Exception e) {
//            LOG.error("[workerManage] selectListStateTimesByState error with info {}.", e.getMessage());
//
//            return new ArrayList<>();
//        }
//    }
//
//    class SelectListStateTimesByStateRowMapper implements RowMapper<MaintenanceList> {
//
//        @Override
//        public MaintenanceList mapRow(ResultSet rs, int rowNum) throws SQLException {
//            MaintenanceList list = new MaintenanceList();
//            list.setListstatetime(rs.getString("liststatetime"));
//            list.setListNumber(rs.getString("listNumber"));
//            list.setListDescription(rs.getString("listDescription"));
//
//            return list;
//        }
//    }

    @Override
    public List<MaintenanceList> selectMaintenanceLists(String userId) {
        String sql = "SELECT listNumber,userTel, liststatetime, listState, listDescription, DI.distinctName, BU.buildingName, RO.roomName, EQ.equipmentName FROM baoxiu_maintenancelist AS MA LEFT JOIN baoxiu_placedistinct AS DI  ON MA.distinctId = DI.distinctId LEFT JOIN baoxiu_placebuilding AS BU ON MA.buildingId = BU.buildingId LEFT JOIN baoxiu_placeroom AS RO ON MA.roomId = RO.roomId LEFT JOIN baoxiu_equipment AS EQ ON MA.equipmentId = EQ.equipmentId WHERE MA.repairGroupId IN (SELECT baoxiu_workerinfo.repairGroupId FROM baoxiu_workerinfo WHERE baoxiu_workerinfo.userId = ? AND baoxiu_workerinfo.deleteFlag = 0) AND MA.deleteFlag = 0  ORDER BY MA.liststatetime DESC LIMIT 35";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectMaintenanceListsRowMapper());
        } catch (Exception e) {
            LOG.error("[workerManage] selectMaintenanceLists error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectMaintenanceListsRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            MaintenanceList list = new MaintenanceList();


            list.setListNumber(resultSet.getString("listNumber"));
            list.setUserTel(resultSet.getString("userTel"));
            list.setDistinctName(resultSet.getString("distinctName"));
            list.setBuildingName(resultSet.getString("buildingName"));
            list.setRoomName(resultSet.getString("roomName"));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setListstatetime(resultSet.getString("liststatetime"));
            list.setListBigDescription(resultSet.getString("listDescription"));

            String listState = resultSet.getString("listState");
            list.setListState(listState);
            list.setListStateFrontStyleColor(MaintenanceListStateToStringUtil.stateNumberToColorString(listState));

            return list;
        }
    }

//    @Override
//    public List<MaintenanceList> selectListStateTimes(String userId) {
//        String sql = "SELECT L.listNumber,L.liststatetime,L.listDescription FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_liststatetime AS L ON M.listNumber = L.listNumber WHERE L.listState = 1 AND repairGroupId IN (SELECT repairGroupId FROM baoxiu_workerinfo WHERE userId = ? AND deleteFlag = 0) ORDER BY L.liststatetime DESC LIMIT 35";
//        Object[] args = {
//                userId
//        };
//
//        try {
//            return jdbcTemplate.query(sql, args, new SelectListStateTimesRowMapper());
//        } catch (Exception e) {
//            LOG.error("[workerManage] selectListStateTimes error with info {}.", e.getMessage());
//
//            return new ArrayList<>();
//        }
//    }
//
//    class SelectListStateTimesRowMapper implements RowMapper<MaintenanceList> {
//
//        @Override
//        public MaintenanceList mapRow(ResultSet rs, int rowNum) throws SQLException {
//            MaintenanceList list = new MaintenanceList();
//            list.setListstatetime(rs.getString("liststatetime"));
//            list.setListNumber(rs.getString("listNumber"));
//            list.setListDescription(rs.getString("listDescription"));
//
//            return list;
//        }
//    }

    @Override
    public MaintenanceList select4details(String listNumber) {
        String sql = "SELECT listNumber,userTel,groupName,roomName,buildingName,distinctName,equipmentName,listState,listPicture,M.listDescription FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_repairgroup AS R ON M.repairGroupId = R.repairGroupId LEFT JOIN baoxiu_placeroom AS PR ON M.roomId = PR.roomId LEFT JOIN baoxiu_placebuilding AS PB ON M.buildingId = PB.buildingId LEFT JOIN baoxiu_placedistinct AS PD ON M.distinctId = PD.distinctId LEFT JOIN baoxiu_equipment AS E ON M.equipmentId = E.equipmentId WHERE listNumber = ?";
        Object[] args = {
                listNumber
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4detailsRowMapper());
        } catch (Exception e) {
            LOG.error("[workerMaintenanceList] query4details error with info {}.", e.getMessage());

            return null;
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
            String listPicture = resultSet.getString("listPicture");
            String listBigDescription = resultSet.getString("listDescription");

            list.setListBigDescription(Strings.isNullOrEmpty(listBigDescription) ? "无" : listBigDescription);
            list.setGroupName(Strings.isNullOrEmpty(groupName) ? "无" : groupName);
            list.setRoomName(Strings.isNullOrEmpty(roomName) ? "无" : roomName);
            list.setBuildingName(Strings.isNullOrEmpty(buildingName) ? "无" : buildingName);
            list.setDistinctName(Strings.isNullOrEmpty(distinctName) ? "无" : distinctName);
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
        String sql = "SELECT listState,liststatetime,listDescription FROM baoxiu_liststatetime WHERE listNumber = ? AND deleteFlag = 0 ORDER BY liststatetime DESC";
        Object[] args = {
                listNumber
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectStatusWithListNumRowMapper());
        } catch (Exception e) {
            LOG.error("[workerMaintenanceList] select status {}'s MaintenanceList error with info {}.", listNumber, e.getMessage());

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
            String listDescription = resultSet.getString("listDescription");

            list.setListDescription(Strings.isNullOrEmpty(listDescription) ? "无" : listDescription);
            list.setListstateStr(dictionary.dictionary(resultSet.getInt("listState"), "listState").getItemValue());
            list.setListstatetime(format.format(resultSet.getTimestamp("liststatetime")));

            return list;
        }
    }

    @Override
    public Boolean insertState(String time, MaintenanceList list) {
        String sql = "INSERT INTO baoxiu_liststatetime (liststatetimeid, listNumber, listState, liststatetime, listDescription) VALUES (?, ?, ?, ?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                list.getListNumber(),
                list.getListState(),
                time,
                list.getListDescription()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[liststatetime] add new state error with info {}.", e.getMessage());

            return false;
        }
    }

    @Override
    public Boolean update(String time, MaintenanceList list) {
        String sql = "UPDATE baoxiu_maintenancelist SET listState = ?, liststatetime = ? WHERE listNumber = ? AND deleteFlag = 0";
        Object[] args = {
                list.getListState(),
                time,
                list.getListNumber()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] delete place distinct error with info {}.", e.getMessage());

            return false;
        }
    }

    @Override
    public WorkerInfo selectWorkerInfo(String userId) {
        String sql = "SELECT workerName,workerUnit,workerDepartment,workerJob,workerState,groupName,typeName,workerTel FROM baoxiu_workerinfo AS I LEFT JOIN baoxiu_workertype AS T ON I.typeId = T.typeId LEFT JOIN baoxiu_repairgroup AS R ON I.repairGroupId = R.repairGroupId WHERE I.userId = ? AND I.deleteFlag = 0";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectWorkerInfoRowMapper());
        } catch (Exception e) {
            LOG.error("[WorkerInfo] select error with info {}.", e.getMessage());

            return null;
        }
    }

    class SelectWorkerInfoRowMapper implements RowMapper<WorkerInfo> {

        @Override
        public WorkerInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            WorkerInfo info = new WorkerInfo();
            IDictionaryManager dictionary = DefaultDictionaryManager.getInstance();

            info.setWorkerDepartment(dictionary.dictionary(resultSet.getInt("workerDepartment"), "workerDepartment").getItemValue());
            info.setWorkerState(dictionary.dictionary(resultSet.getInt("workerState"), "workerState").getItemValue());
            info.setWorkerName(resultSet.getString("workerName"));
            info.setWorkerUnite(resultSet.getString("workerUnit"));
            info.setWorkerJob(resultSet.getString("workerJob"));
            info.setGroupName(resultSet.getString("groupName"));
            info.setTypeName(resultSet.getString("typeName"));
            info.setWorkerTel(resultSet.getString("workerTel"));

            return info;
        }
    }

    @Override
    public Boolean changePass(WorkerInfo info) {
        String sql = "UPDATE baoxiu_workerinfo SET workerPass = ? WHERE userId = ? AND deleteFlag = 0";
        Object[] args = {
            info.getNewWorkerPass(),
            info.getUserId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[worker] change password  error", e.getMessage());

            return false;
        }
    }

    @Override
    public int selectTodoMiantenanceSum(String userId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT count(MA.listNumber) FROM baoxiu_maintenancelist AS MA  LEFT JOIN baoxiu_workerinfo AS INF ON(MA.repairGroupId = INF.repairGroupId) ");
        builder.append("WHERE INF.userId = ? AND MA.listState = 1");

        Object[] args = { userId};
        try{
            return jdbcTemplate.queryForInt(builder.toString(), args);
        }catch (Exception e){
            LOG.error("[worker] select todoMiantenanceSum  error", e.getMessage());

            return 0;
        }
    }

    /**
     * 查询所有需处理的报修单
     * @param userId
     * @return 报修单数
     */
    @Override
    public int selectallDoMaintenanceSum(String userId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT count(MA.listNumber) FROM baoxiu_maintenancelist AS MA  LEFT JOIN baoxiu_workerinfo AS INF ON(MA.repairGroupId = INF.repairGroupId) WHERE  INF.userId = ? ");
        Object[] args = { userId};
        try{
            return jdbcTemplate.queryForInt(builder.toString(), args);
        }catch (Exception e){
            LOG.error("[worker] select todoMiantenanceSum  error", e.getMessage());

            return 0;
        }
    }

    @Override
    public int selectOnDoMaintenanceSum(String userId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT count(MA.listNumber) FROM baoxiu_maintenancelist AS MA  LEFT JOIN baoxiu_workerinfo AS INF ON(MA.repairGroupId = INF.repairGroupId) ");
        builder.append("WHERE INF.userId = ? AND MA.listState = 2");

        Object[] args = { userId};
        try{
            return jdbcTemplate.queryForInt(builder.toString(), args);
        }catch (Exception e){
            LOG.error("[worker] select todoMiantenanceSum  error", e.getMessage());

            return 0;
        }
    }
}
