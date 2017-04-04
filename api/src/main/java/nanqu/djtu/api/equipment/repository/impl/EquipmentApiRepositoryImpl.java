package nanqu.djtu.api.equipment.repository.impl;

import nanqu.djtu.api.equipment.repository.EquipmentApiRepositoryI;
import nanqu.djtu.pojo.Equipment;
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
public class EquipmentApiRepositoryImpl implements EquipmentApiRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(EquipmentApiRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询room下的equipment
     *
     * @param roomId roomId
     * @return 如果存在返回list, else size 0 list
     */
    @Override
    public List<Equipment> selectEquipmentByRoomId(String roomId) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT E.equipmentId, E.equipmentName FROM baoxiu_equipmentset ES LEFT JOIN baoxiu_equipment E");
        builder.append(" ON ES.equipmentId = E.equipmentId WHERE setId IN (SELECT setId FROM baoxiu_placeroom WHERE");
        builder.append(" roomId = ?)");
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.query(builder.toString(), args, new SelectEquipmentByRoomIdRowMapper());
        } catch (Exception e) {
            LOG.error("[API] select equipment by room {} error with info {}.", roomId, e.getMessage());

            return new ArrayList<>();
        }
    }

    private class SelectEquipmentByRoomIdRowMapper implements RowMapper<Equipment> {

        @Override
        public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {

            Equipment equipment = new Equipment();

            equipment.setEquipmentId(rs.getString("equipmentId"));
            equipment.setEquipmentName(rs.getString("equipmentName"));

            return equipment;
        }
    }
}
