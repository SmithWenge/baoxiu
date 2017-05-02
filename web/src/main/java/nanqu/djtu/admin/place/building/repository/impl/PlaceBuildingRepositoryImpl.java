package nanqu.djtu.admin.place.building.repository.impl;

import nanqu.djtu.admin.place.building.repository.PlaceBuildingRepositoryI;
import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
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
        String sql ="SELECT buildingId, buildingName, PD.distinctId, buildingNumber, distinctName, distinctNumber FROM baoxiu_placebuilding PB join baoxiu_placedistinct PD on PB.distinctId = PD.distinctId where PB.deleteFlag = 0";
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
            placeBuilding.setBuildingName(rs.getString("buildingName"));
            placeBuilding.setDistinctId(rs.getString("distinctId"));
            placeBuilding.setBuildingNumber(rs.getString("buildingNumber"));
            placeBuilding.setDistinctName(rs.getString("distinctName"));
            placeBuilding.setDistinctNumber(rs.getString("distinctNumber"));

            return placeBuilding;
        }
    }

    /**
     * 查询校区信息列表
     *
     * @return 所有未删除校区的信息
     */
    @Override
    public List<PlaceDistinct> placeDistinctSelect4List() {
        String sql = "SELECT distinctId,distinctName,distinctNumber FROM baoxiu_placedistinct where deleteFlag = 0";
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

    /**
     * 查询设备组信息列表
     *
     * @return 所有未删除设备组的信息
     */
    @Override
    public List<EquipmentSet> equipmentSetSelect4List() {
        String sql = "SELECT setId, setName FROM baoxiu_set where deleteFlag = 0";
        Object[] args = {};

        try {
            return  jdbcTemplate.query(sql, args, new equipmentSetSelect4ListRowMapper());
        } catch (Exception e){
            LOG.error("[EquipmentSet] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }

    }

    private class equipmentSetSelect4ListRowMapper implements RowMapper<EquipmentSet> {

        @Override
        public EquipmentSet mapRow(ResultSet rs, int rowNum) throws SQLException {
            EquipmentSet equipmentSet = new EquipmentSet();

            equipmentSet.setSetId(rs.getString("setId"));
            equipmentSet.setSetName(rs.getString("setName"));

            return equipmentSet;
        }
    }

    /**
     * 添加新的校区
     *
     * @param building 新地点的信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNewPlaceDistinct(PlaceBuilding building) {
        String sql = "INSERT  INTO baoxiu_placebuilding(buildingId, buildingName, distinctId, buildingNumber) values(?,?,?,?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                building.getBuildingName(),
                building.getDistinctId(),
                building.getBuildingNumber()
        };

        try {
            return jdbcTemplate.update(sql,args) == 1;
        } catch (Exception e) {
            LOG.error("[PlaceBuilding] add new place building error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 验证地点编号的唯一
     *
     * @param building 地点编号
     * @return 唯一返回true, else false
     */
    @Override
    public boolean select4PlaceBuildingNumberUnique(PlaceBuilding building) {
        String sql = "SELECT count(1) as NUM  FROM baoxiu_placebuilding where buildingNumber = ? and distinctId = ? ";
        Object[] args = {building.getBuildingNumber(),building.getDistinctId()};

        try {
            return  jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除地点
     *
     * @param buildingId 地点Id
     * @return 删除成功返回true, else false
     */
    @Override
    public boolean deletePlaceBuilding(String buildingId) {
        String sql = "UPDATE baoxiu_placebuilding set deleteFlag = 1 WHERE buildingId = ? AND deleteFlag = 0";
        Object[] args = {buildingId};

        try {
                return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {

            LOG.error("[PlaceBuilding] delete place building error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 查询需要编辑的地点的信息
     *
     * @param buildingId 地点Id
     * @return 地点信息对象
     */
    @Override
    public PlaceBuilding select4Edit(String buildingId) {
        String sql = "SELECT  buildingId, buildingName, distinctId, buildingNumber FROM baoxiu_placebuilding where buildingId =? AND deleteFlag = 0";
        Object[] args = {
                buildingId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, new Select4EditRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceBuilding] query4List error with info {}.", e.getMessage());

            return null;
        }
    }

    private class Select4EditRowMapper implements RowMapper<PlaceBuilding> {

        @Override
        public PlaceBuilding mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaceBuilding placeBuilding = new PlaceBuilding();

            placeBuilding.setBuildingId(rs.getString("buildingId"));
            placeBuilding.setBuildingName(rs.getString("buildingName"));
            placeBuilding.setDistinctId(rs.getString("distinctId"));
            placeBuilding.setBuildingNumber(rs.getString("buildingNumber"));

            return placeBuilding;
        }
    }

    /**
     * 更新地点信息
     *
     * @param placeBuilding 新的更改后的地点信息
     * @return 更改成功返回true, else false
     */
    @Override
    public boolean updatePlaceBuilding(PlaceBuilding placeBuilding) {
        String sql = "UPDATE baoxiu_placebuilding SET distinctId=?, buildingName = ? where deleteFlag = 0 AND buildingId =? ";
        Object[] args = {
                placeBuilding.getDistinctId(),
                placeBuilding.getBuildingName(),
                placeBuilding.getBuildingId()
        };

        try {
            return  jdbcTemplate.update(sql,args) ==1;

        }catch (Exception e) {
            LOG.error("[PlaceBuilding] update place building error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 查询这个校区下地点的总数
     *
     * @param distinctId 校区Id
     * @return 返回这个校区下地点的总数
     */
    @Override
    public int select4BuildingCount(String distinctId) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_placebuilding WHERE distinctId = ?";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class);
        } catch (Exception e) {
            LOG.error("[PlaceBuilding] query place building count for distinctId {} and error with info {}.", distinctId, e.getMessage());

            return -1;
        }
    }

}
