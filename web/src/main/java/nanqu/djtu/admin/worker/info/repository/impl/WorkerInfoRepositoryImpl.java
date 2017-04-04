package nanqu.djtu.admin.worker.info.repository.impl;

import nanqu.djtu.admin.worker.info.repository.WorkerInfoRepositoryI;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.RepairGroup;
import nanqu.djtu.pojo.WorkerInfo;
import nanqu.djtu.pojo.WorkerType;
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
public class WorkerInfoRepositoryImpl implements WorkerInfoRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerInfoRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询工人信息列表
     *
     * @return 所有未删除工人的信息
     */
    @Override
    public List<WorkerInfo> query4List() {
        String sql = "SELECT userId,workerName,workerUnit,workerDepartment,workerJob,workerState,groupName,typeName,baoxiu.baoxiu_workerinfo.repairGroupId,baoxiu.baoxiu_workerinfo.typeId FROM (baoxiu.baoxiu_workerinfo join baoxiu.baoxiu_repairgroup on(baoxiu.baoxiu_workerinfo.repairGroupId = baoxiu.baoxiu_repairgroup.repairGroupId) )join baoxiu.baoxiu_workertype on(baoxiu.baoxiu_workerinfo.typeId = baoxiu.baoxiu_workertype.typeId) where baoxiu.baoxiu_workerinfo.deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new Query4ListRowMapper());
        }catch (Exception e) {
            LOG.error("[WorkerInfo] query4List error with info {}.", e.getMessage());
            return new ArrayList<>();
        }
    }

    private class Query4ListRowMapper implements RowMapper<WorkerInfo> {

        @Override
        public WorkerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkerInfo info = new WorkerInfo();

            info.setUserId(rs.getString("userId"));
            info.setWorkerName(rs.getString("workerName"));
            info.setWorkerUnite(rs.getString("workerUnit"));
            info.setWorkerDepartment(Integer.toString(rs.getInt("workerDepartment")));
            info.setWorkerJob(rs.getString("workerJob"));
            info.setWorkerState(Integer.toString(rs.getInt("workerState")));
            info.setGroupName(rs.getString("groupName"));
            info.setTypeName(rs.getString("typeName"));
            info.setRepairGroupId(rs.getString("repairGroupId"));
            info.setTypeId(rs.getString("typeId"));

            return info;
        }
    }

    /**
     * 查询维修小组的信息列表
     *
     * @return 所未删维修小组的信息
     */

    @Override
    public List<RepairGroup> repairGroupQuery4List() {
        String sql = "SELECT repairGroupId,groupNumber,groupName,groupPrinterIp FROM baoxiu.baoxiu_repairgroup where deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new repairGroupQuery4ListRowMapper());
        }catch (Exception e) {

            LOG.error("[RepairGroup] query4List error with info {}.", e.getMessage());
            return new ArrayList<>();

        }
    }

    private class repairGroupQuery4ListRowMapper implements RowMapper<RepairGroup> {

        @Override
        public RepairGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
            RepairGroup repairGroup = new RepairGroup();

            repairGroup.setRepairGroupId(rs.getString("repairGroupId"));
            repairGroup.setGroupName(rs.getString("groupNumber"));
            repairGroup.setGroupName(rs.getString("groupName"));
            repairGroup.setGroupPrinterIp(rs.getString("groupPrinterIp"));

            return repairGroup;
        }
    }

    /**
     * 查询工种的信息列表
     *
     * @return 所有未删除工种的信息
     */
    @Override
    public List<WorkerType> workerTypeQuery4List() {
        String sql = "SELECT typeId,typeName FROM baoxiu.baoxiu_workertype where deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new WorkerTypeQuery4ListRowMapper() );
        }catch (Exception e) {

            LOG.error("[WorkerType] query4List error with info {}.", e.getMessage());
            return new ArrayList<>();
        }
    }



    private class WorkerTypeQuery4ListRowMapper implements RowMapper<WorkerType> {

        @Override
        public WorkerType mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkerType workerType = new WorkerType();

            workerType.setTypeId(rs.getString("typeId"));
            workerType.setTypeName(rs.getString("typeName"));

            return workerType;
        }
    }

    /**
     * 添加新的工人
     * @param info 工人信息
     * @return true ,else false
     */

    @Override
    public boolean insertNewWorkerInfo(WorkerInfo info) {
        String sql = "INSERT INTO  baoxiu.baoxiu_workerinfo(userId,workerName,workerUnit,workerDepartment,workerJob,workerState,repairGroupId,typeId,deleteFlag) VALUES(?,?,?,?,?,?,?,?,0);";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                info.getWorkerName(),
                info.getWorkerUnite(),
                Integer.parseInt(info.getWorkerDepartment()),
                info.getWorkerJob(),
                Integer.parseInt(info.getWorkerState()),
                info.getRepairGroupId(),
                info.getTypeId()
        };

        try {
            return jdbcTemplate.update(sql,args) == 1;

        }catch (Exception e){

            LOG.error("[WorkerInfo] add new place distinct error with info {}.", e.getMessage());
            return false;

        }
    }
    /**
     * 删除工人
     *
     * @param userId 工人Id
     * @return 删除成功返回true, else false
     */

    @Override
    public boolean deleteWorkerInfo(String userId) {
        String sql = "UPDATE baoxiu.baoxiu_workerinfo set deleteFlag = 1 WHERE userId = ? AND deleteFlag = 0";
        Object[] args = {userId};

        try {
            return jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {

            LOG.error("[WorkerInfo] delete worker info error with info {}.", e.getMessage());

            return false;
        }
    }
    /**
     * 查询需要编辑的工人的信息
     *
     * @param userId 工人Id
     * @return 工人信息对象
     */

    @Override
    public WorkerInfo query4Edit(String userId) {
        String sql = "SELECT userId,workerName,workerUnit,workerDepartment,workerJob,workerState,groupName,typeName,baoxiu.baoxiu_workerinfo.repairGroupId,baoxiu.baoxiu_workerinfo.typeId FROM (baoxiu.baoxiu_workerinfo join baoxiu.baoxiu_repairgroup on(baoxiu.baoxiu_workerinfo.repairGroupId = baoxiu.baoxiu_repairgroup.repairGroupId) )join baoxiu.baoxiu_workertype on(baoxiu.baoxiu_workerinfo.typeId = baoxiu.baoxiu_workertype.typeId) where baoxiu.baoxiu_workerinfo.deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.queryForObject(sql,args,new Query4ListRowMapper());

        }catch (Exception e) {

            LOG.error("[WorkerInfo] query4List error with info {}.", e.getMessage());

            return null;
        }

    }
    /**
     * 更新工人信息
     *
     * @param info 新的更改后的工人信息
     * @return 更改成功返回true, else false
     */

    @Override
    public boolean updateWorkerInfo(WorkerInfo info) {
        String sql = "UPDATE baoxiu.baoxiu_workerinfo SET workerName =?,workerUnit=?,workerDepartment=?,workerJob = ?,workerState = ?,repairGroupId = ?,typeId = ? WHERE userId= ? and deleteFlag=0";
        Object[] args = {
                info.getWorkerName(),
                info.getWorkerUnite(),
                Integer.parseInt(info.getWorkerDepartment()),
                info.getWorkerJob(),
                Integer.parseInt(info.getWorkerState()),
                info.getRepairGroupId(),
                info.getTypeId(),
                info.getUserId()};

        try {
                return jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {

            LOG.error("[WorkerInfo] query4List error with info {}.", e.getMessage());
            return false;
        }
    }
}
