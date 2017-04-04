package nanqu.djtu.api.building.repository.impl;

import nanqu.djtu.api.building.repository.BuildingApiRepositoryI;
import nanqu.djtu.pojo.PlaceBuilding;
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
public class BuildingApiRepositoryImpl implements BuildingApiRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(BuildingApiRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据校区Id
     *
     * @param distinctId 校区Id
     * @return 校区下的地点
     */
    @Override
    public List<PlaceBuilding> selectDistinctBuilding(String distinctId) {
        String sql = "SELECT buildingId, buildingName FROM baoxiu_placebuilding WHERE distinctId = ? AND deleteFlag = 0";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.query(sql, args, new SelectDistinctBuildingRowMapper());
        } catch (Exception e) {
            LOG.error("[API] query distinct {}'s buildings error with info {}.", distinctId, e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectDistinctBuildingRowMapper implements RowMapper<PlaceBuilding> {

        @Override
        public PlaceBuilding mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceBuilding building = new PlaceBuilding();

            building.setBuildingId(rs.getString("buildingId"));
            building.setBuildingName(rs.getString("buildingName"));

            return building;
        }
    }
}
