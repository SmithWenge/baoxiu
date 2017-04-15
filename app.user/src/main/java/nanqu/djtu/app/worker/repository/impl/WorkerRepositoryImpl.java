package nanqu.djtu.app.worker.repository.impl;

import nanqu.djtu.app.worker.repository.WorkerRepositoryI;
import nanqu.djtu.pojo.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class WorkerRepositoryImpl implements WorkerRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    class WorkerLoginRowMapper implements RowMapper<WorkerInfo> {

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
}
