package nanqu.djtu.admin.place.distinct.repository.impl;

import nanqu.djtu.admin.place.distinct.repository.PlaceDistinctRepositoryI;
import nanqu.djtu.pojo.PlaceDistinct;
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
public class PlaceDistinctRepositoryImpl implements PlaceDistinctRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceDistinctRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询校区信息列表
     *
     * @return 所有未删除校区的信息
     */
    @Override
    public List<PlaceDistinct> select4List() {
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
     * 添加新的校区
     *
     * @param distinct 新校区的信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewPlaceDistinct(PlaceDistinct distinct) {
        String sql = "INSERT INTO baoxiu_placedistinct (distinctId, distinctName, distinctNumber) VALUES (?, ?, ?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                distinct.getDistinctName(),
                distinct.getDistinctNumber()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] add new place distinct error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 删除校区
     *
     * @param distinctId 校区Id
     * @return 删除成功返回true, else false
     */
    @Override
    public boolean deletePlaceDistinct(String distinctId) {
        String sql = "UPDATE baoxiu_placedistinct SET deleteFlag = 1 WHERE distinctId = ? AND deleteFlag = 0";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] delete place distinct error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 查询需要编辑的校区的信息
     *
     * @param distinctId 校区Id
     * @return 校区信息对象
     */
    @Override
     public PlaceDistinct select4Edit(String distinctId) {
        String sql = "SELECT distinctId, distinctName, distinctNumber FROM baoxiu_placedistinct WHERE distinctId = ? AND deleteFlag = 0";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4ListRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] query4List error with info {}.", e.getMessage());

            return null;
        }
    }


    @Override
    public boolean updatePlaceDistinct(PlaceDistinct distinct) {
        String sql = "UPDATE baoxiu_placedistinct SET distinctName = ?, distinctNumber = ? WHERE distinctId = ? AND deleteFlag = 0";
        Object[] args = {
                distinct.getDistinctName(),
                distinct.getDistinctNumber(),
                distinct.getDistinctId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] update place distinct error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 验证校区编号的唯一
     *
     * @param distinctNumber 校区编号
     * @return 唯一返回true, else false
     */
    @Override
    public boolean select4PlaceDistinctNumberUnique(String distinctNumber) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_placedistinct WHERE distinctNumber = ?";
        Object[] args = {
                distinctNumber
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            return false;
        }
    }
}
