package nanqu.djtu.admin.home.repository.impl;


import com.google.common.base.Strings;
import nanqu.djtu.admin.home.repository.HomeRepositoryI;
import nanqu.djtu.admin.maintenance.list.manage.repository.impl.MaintenanceLisRepositoryImpl;
import nanqu.djtu.dictionary.feature.manager.IDictionaryManager;
import nanqu.djtu.dictionary.feature.manager.impl.DefaultDictionaryManager;
import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.util.RepositoryUtils;
import nanqu.djtu.utils.ConstantFields;
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
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT listNumber,listState,equipmentName,groupName,liststatetime FROM baoxiu_maintenancelist");
        builder.append(" AS M LEFT JOIN baoxiu_equipment AS E ON M.equipmentId = E.equipmentId LEFT JOIN");
        builder.append(" baoxiu_repairgroup AS R ON M.repairGroupId = R.repairGroupId WHERE M.deleteFlag = 0 AND M.listState IN (1, 5)");
        Object[] args = {};

        return repositoryUtils.select4Page(builder.toString(), pageable, args, new Query4PageRowMapper());

    }

    private class Query4PageRowMapper implements RowMapper<MaintenanceList> {

        @Override
        public MaintenanceList mapRow(ResultSet resultSet, int i) throws SQLException {
            MaintenanceList list = new MaintenanceList();

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            IDictionaryManager dictionary = DefaultDictionaryManager.getInstance();
            int listState = resultSet.getInt("listState");

            list.setListNumber(resultSet.getString("listNumber"));
            list.setListState(String.valueOf(listState));

            String equipmentName = resultSet.getString("equipmentName");
            if (Strings.isNullOrEmpty(equipmentName)) {
                list.setEquipmentName(ConstantFields.NO_EQUIPMENT_NAME_DEFAULT_NAME);
            } else {
                list.setEquipmentName(equipmentName);
            }

            String groupName = resultSet.getString("groupName");

            if (Strings.isNullOrEmpty(groupName)) {
                list.setGroupName(ConstantFields.NO_GROUP_NAME_DEFAULT_NAME);
            } else {
                list.setGroupName(groupName);
            }

            list.setListstatetime(format.format(resultSet.getTimestamp("liststatetime")));

            String listStateValue = dictionary.dictionary(listState, "listState").getItemValue();
            if (Strings.isNullOrEmpty(listStateValue)) {
                list.setListstateStr(ConstantFields.NO_LIST_SATE_ITEM_VALUE);
            } else {
                list.setListstateStr(listStateValue);
            }

            return list;
        }
    }
}
