package nanqu.djtu.admin.worker.type.repository.impl;

import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
import nanqu.djtu.admin.worker.type.repository.WorkerTyeRepositoryI;
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
public class WorkerTypeRepositoryImpl implements WorkerTyeRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerTypeRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 查询工种信息列表
     *
     * @return 所有未删除工种的信息
     */
    public List<WorkerType> select4List() {
        String sql = "SELECT typeId, typeName FROM baoxiu_workertype WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectListRowMapper());
        } catch (Exception e) {
            LOG.error("[WorkerType] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }
     private class SelectListRowMapper implements RowMapper<WorkerType> {

        @Override
        public WorkerType mapRow(ResultSet rs, int rowNum) throws SQLException {
            WorkerType distinct = new WorkerType();

            distinct.setTypeId(rs.getString("typeId"));
            distinct.setTypeName(rs.getString("typeName"));


            return distinct;
        }
    }
    /**
     * 添加新的工种
     *
     * @param distinct 新工种的信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewWorkerType(WorkerType distinct) {
        String sql = "INSERT INTO baoxiu_placedistinct (typeId, typeName) VALUES (?, ?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                distinct.getTypeId(),
                distinct.getTypeName()
        };
        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[WorkerType] add new type distinct error with info {}.", e.getMessage());

            return false;
        }
    }
    /**
     * 删除工种
     *
     * @param typeId 工种Id
     * @return 删除成功返回true, else false
     */

    @Override
    public boolean deleteWorkerType(String typeId) {
        String sql = "UPDATE baoxiu_workertype SET deleteFlag = 1 WHERE typeId = ? AND deleteFlag = 0";
        Object[] args = {
                typeId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[WorkerType] delete type distinct error with info {}.", e.getMessage());

            return false;
        }
    }
    /**
     * 更新工种信息
     *
     * @param distinct 新的更改后的工种信息
     * @return 更改成功返回true, else false
     */
    @Override
    public boolean updateWorkerType(WorkerType distinct) {
        String sql = "UPDATE baoxiu_workertype SET typeName = ?,  WHERE typeId = ? AND deleteFlag = 0";
        Object[] args = {
                distinct.getTypeId(),
                distinct.getTypeName()

        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[WorkerType] update type distinct error with info {}.", e.getMessage());

            return false;
        }
    }

    @Override
    public WorkerType select4Edit(String typeId) {
        String sql = "SELECT typeId, typeName FROM baoxiu_workertype WHERE typeId = ? AND deleteFlag = 0";
        Object[] args = {
                typeId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectListRowMapper());
        } catch (Exception e) {
            LOG.error("[WorkerType] query4List error with info {}.", e.getMessage());

            return null;
        }
    }


}
