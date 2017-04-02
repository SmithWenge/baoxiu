package nanqu.djtu.admin.place.building.repository.impl;

import nanqu.djtu.admin.place.building.repository.PlaceBuildingRepositoryI;
import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
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
 * Created by Administrator on 2017/4/2.
 */
@Repository
public class PlaceBuildingRepositoryImpl implements PlaceBuildingRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceDistinctRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询地点信息列表
     *
     * @return 所有未删除地点的信息
     */
    @Override
    public List<PlaceBuilding> select4List() {
        String sql ="SELECT  buildingId,buildingName,distinctId,setId,buildingNumber FROM baoxiu.baoxiu_placebuilding where deleteFlag = 0";
        Object[] args ={};
        try {
            return jdbcTemplate.query(sql, args, new Select4ListRowMapper());

        } catch (Exception e) {
            LOG.error("[PlaceBuilding] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();

        }
    }



    private class Select4ListRowMapper implements RowMapper<PlaceBuilding> {

        @Override
        public PlaceBuilding mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaceBuilding placeBuilding = new PlaceBuilding();

            placeBuilding.setBuildingId(rs.getString("buildingId"));
            placeBuilding.setBuidingName(rs.getString("buildingName"));
            placeBuilding.setSetId(rs.getString("setId"));
            placeBuilding.setBuildingId(rs.getString("distinctId"));
            placeBuilding.setBuildingNumber(rs.getString("buildingNumber"));

            return placeBuilding;
        }
    }

    @Override
    public List<PlaceDistinct> placeDistinctSelect4List() {
        String sql = "SELECT distinctId,distinctName,distinctNumber FROM baoxiu.baoxiu_placedistinct where deleteFlag = 0";
        Object[] args = {};
        try {
            return jdbcTemplate.query(sql, args, new placeDistinctSelect4ListRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] query4List error with info {}.", e.getMessage());
            return new ArrayList<>();
        }
    }

    private class placeDistinctSelect4ListRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaceDistinct placeDistinct = new PlaceDistinct();

            placeDistinct.setDistinctId(rs.getString("distinctId"));
            placeDistinct.setDistinctName(rs.getString("distinctName"));
            placeDistinct.setDistinctNumber(rs.getString("distinctNumber"));


            return placeDistinct;
        }
    }
}
