package nanqu.djtu.api.maintenance.list.repository.impl;

import nanqu.djtu.api.maintenance.list.repository.MaintenanceListApiRepositoryI;
import nanqu.djtu.pojo.MaintenanceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceListApiRepositoryImpl implements MaintenanceListApiRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceListApiRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过微信添加新的保修单
     *
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */
    @Override
    public boolean insertNew(MaintenanceList list) {
        String sql = "INSERT INTO baoxiu_maintenancelist (listNumber, userId, userName, repairGroupId, roomId, buildingId, distinctId, listDescription, equipmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                list.getListNumber(),
                list.getUserId(),
                list.getUserName(),
                list.getRepairGroupId(),
                list.getRoomId(),
                list.getBuildingId(),
                list.getDistinctId(),
                list.getListDescription(),
                list.getEquipmentId()
        };

        try {
            return jdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            LOG.error("[API] add new maintenance error with info {}.", e.getMessage());

            return false;
        }
    }

    /**
     * 查看这个设备的维修组
     *
     * @param equipmentId 设备Id
     * @return 返回这个设备的维修组Id
     */
    @Override
    public String selectRepairGroupId(String equipmentId) {
        String sql = "SELECT repairGroupId FROM baoxiu_equipment WHERE equipmentId = ?";
        Object[] args = {
                equipmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query equipment {}'s repair group id error with info {}.", equipmentId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个校区的编号
     *
     * @param distinctId 校区Id
     * @return 返回校区编号
     */
    @Override
    public String selectDistinctNumber(String distinctId) {
        String sql = "SELECT distinctNumber FROM baoxiu_placedistinct WHERE distinctId = ?";
        Object[] args = {
                distinctId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query distinct {}'s number error with info {}.", distinctId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个地点的编号
     *
     * @param buildingId 地点Id
     * @return 这个地点的编号
     */
    @Override
    public String selectBuildingNumber(String buildingId) {
        String sql = "SELECT buildingNumber FROM baoxiu_placebuilding WHERE buildingId = ?";
        Object[] args = {
                buildingId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query building {}'s number error with info {}.", buildingId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个位置的编号
     *
     * @param roomId 位置Id
     * @return 这个位置的编号
     */
    @Override
    public String selectRoomNumber(String roomId) {
        String sql = "SELECT roomNumber FROM baoxiu_placeroom WHERE roomId = ?";
        Object[] args = {
                roomId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query room {}'s number error with info {}.", roomId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个设备的编号
     *
     * @param equipmentId 设备Id
     * @return 这个设备的编号
     */
    @Override
    public String selectEquipmentNumber(String equipmentId) {
        String sql = "SELECT equipmentNumber FROM baoxiu_equipment WHERE equipmentId = ?";
        Object[] args = {
                equipmentId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception e) {
            LOG.error("[API] query equipment {}'s number error with info {}.", equipmentId, e.getMessage());

            return null;
        }
    }

    /**
     * 查询这个报修单是否存在
     *
     * @param listId 报修单编号
     * @return 如果不存在返回true, else false
     */
    @Override
    public boolean selectIfExistMaintenanceList(String listId) {
        String sql = "SELECT COUNT(1) AS NUM FROM baoxiu_maintenancelist WHERE listNumber = ? AND deleteFLag = 0 AND listState != 1";
        Object[] args = {
                listId
        };

        try {
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        } catch (Exception e) {
            LOG.error("[API] query exit list error with info {}.", e.getMessage());

            return false;
        }
    }
}
