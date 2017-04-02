package nanqu.djtu.admin.set.repository.impl;

import nanqu.djtu.admin.set.repository.SetRepositoryI;
import nanqu.djtu.pojo.EquipmentSet;
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

@Repository
public class SetRepositoryImpl implements SetRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(SetRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<EquipmentSet> repositoryUtils;

    /**
     * 分页查询设备组
     *
     * @param equipmentSet 设备组条件
     * @param pageable 分页查询条件
     * @return 返回设备组的分页查询数据
     */
    @Override
    public Page<EquipmentSet> select4Page(EquipmentSet equipmentSet, Pageable pageable) {
        String sql = "SELECT setId, setName FROM baoxiu_set WHERE deleteFlag = 0 ORDER BY setId";
        Object[] args = {};

        return repositoryUtils.select4Page(sql, pageable, args, new Select4PageRowMapper());
    }

    private class Select4PageRowMapper implements RowMapper<EquipmentSet> {

        @Override
        public EquipmentSet mapRow(ResultSet rs, int rowNum) throws SQLException {

            EquipmentSet set = new EquipmentSet();

            set.setSetId(rs.getString("setId"));
            set.setSetName(rs.getString("setName"));

            return set;
        }
    }

    /**
     * 添加新的设备组
     *
     * @param set 新的设备组的信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewPlaceDistinct(EquipmentSet set) {
        String sql = "INSERT INTO baoxiu_set (setId, setName) VALUES (?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                set.getSetName()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[EquipmentSet] add new equipment set error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 查询需要编辑设备组的对象
     *
     * @param setId 设备组Id
     * @return 设备组的数据对象
     */
    @Override
    public EquipmentSet select4Edit(String setId) {
        String sql = "SELECT setId, setName FROM baoxiu_set WHERE deleteFlag = 0 AND setId = ?";
        Object[] args = {
                setId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4PageRowMapper());
        } catch (Exception e) {
            LOG.error("[EquipmentSet] select edit equipment set error with info {}.", e.getMessage());

            return null;
        }
    }

    /**
     * 更新编辑后的设备组
     *
     * @param set 更新后的设备组信息
     * @return 如果更新成功返回true, else false
     */
    @Override
    public boolean updateSet(EquipmentSet set) {
        String sql = "UPDATE baoxiu_set SET setName = ? WHERE setId = ? AND deleteFlag = 0";
        Object[] args = {
                set.getSetName(),
                set.getSetId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[EquipmentSet] update equipment set error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 删除设备组
     *
     * @param setId 设备组Id
     * @return 删除成功返回true, else false
     */
    @Override
    public boolean deleteSet(String setId) {
        String sql = "UPDATE baoxiu_set SET deleteFlag = 1 WHERE setId = ? AND deleteFlag = 0";
        Object[] args = {
                setId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[EquipmentSet] delete equipment set error with info {}.", e.getMessage());

            return false;
        }
    }
}
