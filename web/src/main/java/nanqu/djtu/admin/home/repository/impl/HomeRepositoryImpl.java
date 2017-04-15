package nanqu.djtu.admin.home.repository.impl;


import com.google.common.base.Strings;
import nanqu.djtu.admin.home.repository.HomeRepositoryI;
import nanqu.djtu.admin.maintenance.list.manage.repository.impl.MaintenanceLisRepositoryImpl;
import nanqu.djtu.dictionary.feature.manager.IDictionaryManager;
import nanqu.djtu.dictionary.feature.manager.impl.DefaultDictionaryManager;
import nanqu.djtu.pojo.MaintenanceList;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Repository
public class HomeRepositoryImpl implements HomeRepositoryI {

    @Autowired
    private RepositoryUtils<MaintenanceList> repositoryUtils;
    @Override
    public Page<MaintenanceList> select4Page(MaintenanceList list, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT listNumber,listState,equipmentName,groupName,listStatusTime FROM baoxiu_maintenancelist AS M LEFT JOIN baoxiu_equipment AS E ON M.equipmentId = E.equipmentId LEFT JOIN baoxiu_repairgroup AS R ON M.repairGroupId = R.repairGroupId WHERE R.deleteFlag = 0");
        List<Object> argsList = new ArrayList<>();
        if (!Strings.isNullOrEmpty(list.getStartListTime())) {
            sql.append(" AND M.listStatusTime>= ?");
            argsList.add(list.getStartListTime());
        }

        if (!Strings.isNullOrEmpty(list.getStopListTime())) {
            sql.append(" AND M.listStatusTime <= ?");
            argsList.add(list.getStopListTime());
        }

        if (!Strings.isNullOrEmpty(list.getBuildingId())) {
            sql.append(" AND M.buildingId = ?");
            argsList.add(list.getBuildingId());
        }

        if (!Strings.isNullOrEmpty(list.getRoomId())) {
            sql.append(" AND M.roomId = ?");
            argsList.add(list.getRoomId());
        }

        if (!Strings.isNullOrEmpty(list.getEquipmentId())) {
            sql.append(" AND M.equipmentId = ?");
            argsList.add(list.getEquipmentId());
        }

        if (list.getListState() > 0) {
            sql.append(" AND M.listState = ?");
            argsList.add(list.getListState());
        }

        if (!Strings.isNullOrEmpty(list.getRepairGroupId())) {
            sql.append(" AND M.repairGroupId = ?");
            argsList.add(list.getRepairGroupId());
        }

        sql.append(" ORDER BY M.listStatusTime DESC");
        Object[] args = argsList.toArray();

        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Query4PageRowmapper());

    }
    class Query4PageRowmapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            IDictionaryManager dictionary = DefaultDictionaryManager.getInstance();
            int listState = resultSet.getInt("listState");

            list.setListNumber(resultSet.getString("listNumber"));
            list.setListState(String.valueOf(listState));
            list.setEquipmentName(resultSet.getString("equipmentName"));
            list.setGroupName(resultSet.getString("groupName"));
            list.setListstatetime(format.format(resultSet.getTimestamp("listStatusTime")));
            list.setListstateStr(dictionary.dictionary(listState, "listState").getItemValue());

            return list;
        }
    }
}
