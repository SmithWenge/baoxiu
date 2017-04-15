package nanqu.djtu.api.maintenance.list.service.impl;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import nanqu.djtu.api.maintenance.list.repository.MaintenanceListApiRepositoryI;
import nanqu.djtu.api.maintenance.list.service.MaintenanceListApiServiceI;
import nanqu.djtu.pojo.MaintenanceList;
import nanqu.djtu.utils.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Repository
public class MaintenanceListApiServiceImpl implements MaintenanceListApiServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(MaintenanceListApiServiceImpl.class);

    @Autowired
    private MaintenanceListApiRepositoryI maintenanceListApiRepository;

    @Transactional
    @Override
    public boolean addNewMaintenanceList(MaintenanceList list) {

        try {
            list.setUserId(URLDecoder.decode(list.getUserId(), Charsets.UTF_8.displayName()));
            list.setListDescription(URLDecoder.decode(list.getListDescription(), Charsets.UTF_8.displayName()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String equipmentId = list.getEquipmentId();
        String repairGroupId = maintenanceListApiRepository.selectRepairGroupId(equipmentId);

        if (Strings.isNullOrEmpty(repairGroupId)) {
            repairGroupId = ConstantFields.DEFAULT_GROUP_ID;
        }

        list.setRepairGroupId(repairGroupId);

        // 拼接维修单编号
        String distinctNumber = maintenanceListApiRepository.selectDistinctNumber(list.getDistinctId());
        String buildingNumber = maintenanceListApiRepository.selectBuildingNumber(list.getBuildingId());
        String roomNumber = maintenanceListApiRepository.selectRoomNumber(list.getRoomId());
        String equipmentNumber = maintenanceListApiRepository.selectEquipmentNumber(equipmentId);

        StringBuilder builder = new StringBuilder();

        builder.append(Strings.padStart(distinctNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(buildingNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(roomNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(equipmentNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));

        list.setListNumber(builder.toString());

        boolean notExit = maintenanceListApiRepository.selectIfExistMaintenanceList(builder.toString());

        if (notExit) {
            return maintenanceListApiRepository.insertNew(list);
        } else {
            return false;
        }
    }
}
