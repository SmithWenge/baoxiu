package nanqu.djtu.admin.repairgroup.repository.impl;

import nanqu.djtu.admin.place.building.repository.impl.PlaceBuildingRepositoryImpl;
import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
import nanqu.djtu.admin.repairgroup.repository.RepairGroupRepositoryI;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.RepairGroup;
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
public class RepairGroupRepositoryImpl implements RepairGroupRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(RepairGroupRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询报修小组信息列表
     *
     * @return 所有未删除维修小组的信息
     */


    @Override
    public List<RepairGroup> select4List() {

        String sql ="SELECT repairGroupId, groupNumber, groupName, groupPrinterIp FROM baoxiu_repairgroup where deleteFlag = 0";
        Object[] args ={};
        try {
            return jdbcTemplate.query(sql, args, new Select4ListRowMapper());

        } catch (Exception e) {
            LOG.error("[RepairGroup] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();

        }
    }


    private class Select4ListRowMapper implements RowMapper<RepairGroup> {

        @Override
        public RepairGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
          RepairGroup repairGroup = new RepairGroup();

            repairGroup.setRepairGroupId(rs.getString("repairGroupId"));
            repairGroup.setGroupNumber(rs.getString("groupNumber"));
            repairGroup.setGroupName(rs.getString("groupName"));
            repairGroup.setGroupPrinterIp(rs.getString("groupPrinterIp"));


            return repairGroup;
        }
    }

    /**
     * 添加新的维修小组
     *
     * @param group 新校区的信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewRepairGroup(RepairGroup group) {
        String sql = "INSERT INTO baoxiu_repairgroup (repairGroupId, groupNumber, groupName, groupPrinterIp) VALUES (?, ?, ?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                group.getGroupNumber(),
                group.getGroupName(),
                group.getGroupPrinterIp()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[RepairGroup] add new repairgroup error with info {}.", e.getMessage());

            return false;
        }
    }
    /**
     * 删除维修小组
     *
     * @param repairGroupId 维修小组Id
     * @return 删除成功返回true, else false
     */
    @Override
    public boolean deleteRepairGroup(String repairGroupId) {
        String sql = "UPDATE baoxiu_repairgroup SET deleteFlag = 1 WHERE repairGroupId = ? AND deleteFlag = 0";
        Object[] args = {
                repairGroupId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[RepairGroup] delete repairgroup error with info {}.", e.getMessage());

            return false;
        }
    }
    /**
     * 查询需要编辑的维修小组的信息
     *
     * @param repairGroupId 维修小组Id
     * @return 维修小组信息对象
     */

    @Override
    public RepairGroup select4Edit(String repairGroupId) {
        String sql = "SELECT repairGroupId, groupNumber, groupName, groupPrinterIp FROM baoxiu_repairgroup WHERE repairGroupId = ? AND deleteFlag = 0";
        Object[] args = {
                repairGroupId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4ListRowMapper());
        } catch (Exception e) {
            LOG.error("[RepairGroup] query4List error with info {}.", e.getMessage());

            return null;
        }
    }

    /**
     * 更新维修小组信息
     *
     * @param group 新的更改后的维修小组信息
     * @return 更改成功返回true, else false
     */
    @Override
    public boolean updateRepairGroup(RepairGroup group) {
        String sql = "UPDATE baoxiu_repairgroup SET groupName = ?, groupNumber = ?, groupPrinterIp = ? WHERE repairGroupId = ? AND deleteFlag = 0";
        Object[] args = {
                group.getGroupName(),
                group.getGroupNumber(),
                group.getGroupPrinterIp(),
                group.getRepairGroupId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[RepairGroup] update repairgroup error with info {}.", e.getMessage());

            return false;
        }

    }
    /**
     * 验证维修小组编号的唯一
     *
     * @param groupNumber 维修小组编号
     * @return 唯一返回true, else false
     */

    @Override
    public boolean select4RepairGroupNumberUnique(String groupNumber) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_repairgroup WHERE groupNumber = ?";
        Object[] args = {
                groupNumber
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            return false;
        }

    }

}
