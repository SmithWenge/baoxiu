package nanqu.djtu.admin.equipment.repository.impl;

import com.google.common.base.Strings;
import nanqu.djtu.admin.equipment.repository.EquipmentRepositoryI;
import nanqu.djtu.pojo.Equipment;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.util.RepositoryUtils;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<Equipment> repositoryUtils;

    /**
     * 分页查询设备信息
     *
     * @param equipment 查询条件的信息
     * @param pageable 分页信息
     * @return 分页查询的数据
     */
    @Override
    public Page<Equipment> select4Page(Equipment equipment, Pageable pageable) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT E.equipmentId, E.equipmentName, E.equipmentNumber, RG.groupName,");
        builder.append(" RG.groupNumber FROM baoxiu_equipmentset ES LEFT JOIN baoxiu_equipment E ON ES.equipmentId =");
        builder.append(" E.equipmentId LEFT JOIN baoxiu_repairgroup RG ON E.repairGroupId = RG.repairGroupId WHERE");
        builder.append(" E.deleteFlag = 0");

        List<Object> argList = new ArrayList();

        if (!Strings.isNullOrEmpty(equipment.getSetId())) {
            builder.append(" AND ES.setId = ?");

            argList.add(equipment.getSetId());
        }

        Object[] args = argList.toArray();

        return repositoryUtils.select4Page(builder.toString(), pageable, args, new Select4PageRowMapper());
    }

    private class Select4PageRowMapper implements RowMapper<Equipment> {

        @Override
        public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {

            Equipment equipment = new Equipment();

            equipment.setEquipmentId(rs.getString("equipmentId"));
            equipment.setEquipmentName(rs.getString("equipmentName"));
            equipment.setEquipmentNumber(rs.getString("equipmentNumber"));
            equipment.setRepairGroupName(rs.getString("groupName"));
            equipment.setRepairGroupNumber(rs.getString("groupNumber"));

            return equipment;
        }
    }

    /**
     * 查询所有校区
     *
     * @return 所有校区数据
     */
    @Override
    public List<PlaceDistinct> selectAllPlaceDistincts() {
        String sql = "SELECT distinctId, distinctName FROM baoxiu_placedistinct WHERE deleteFlag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new SelectAllPlaceDistinctsRowMapper());
        } catch (Exception e) {
            LOG.error("[Equipment] select all place distincts error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectAllPlaceDistinctsRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet rs, int rowNum) throws SQLException {

            PlaceDistinct distinct = new PlaceDistinct();

            distinct.setDistinctId(rs.getString("distinctId"));
            distinct.setDistinctName(rs.getString("distinctName"));

            return distinct;
        }
    }
}
