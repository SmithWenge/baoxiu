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


    @Override
    public boolean insertNew(MaintenanceList list) {
        return false;
    }

    @Override
    public String selectRepairGroupId(String equipmentId) {
        return null;
    }
}
