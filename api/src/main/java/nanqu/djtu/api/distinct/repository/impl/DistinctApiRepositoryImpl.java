package nanqu.djtu.api.distinct.repository.impl;

import nanqu.djtu.api.distinct.repository.DistinctApiRepositoryI;
import nanqu.djtu.pojo.PlaceDistinct;
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
public class DistinctApiRepositoryImpl implements DistinctApiRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(DistinctApiRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有的校区对象
     *
     * @return 校区对象List或者size 0 list
     */
    @Override
    public List<PlaceDistinct> selectAllDistincts() {
        String sql = "SELECT distinctId, distinctName FROM baoxiu_placedistinct WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllDistinctsRowMapper());
        } catch (Exception e) {
            LOG.error("[API] all distinct select error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectAllDistinctsRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceDistinct distinct = new PlaceDistinct();

            distinct.setDistinctId(rs.getString("distinctId"));
            distinct.setDistinctName(rs.getString("distinctName"));

            return distinct;
        }
    }
}
