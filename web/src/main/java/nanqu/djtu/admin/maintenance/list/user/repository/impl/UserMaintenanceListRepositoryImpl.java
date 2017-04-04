package nanqu.djtu.admin.maintenance.list.user.repository.impl;


import nanqu.djtu.admin.maintenance.list.user.repository.UserMaintenanceListRepositoryI;
import nanqu.djtu.pojo.PlaceBuilding;
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

/**
 * Created by Administrator on 2017/4/4.
 */
@Repository
public class UserMaintenanceListRepositoryImpl implements UserMaintenanceListRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(UserMaintenanceListRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询校区信息列表
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
}
