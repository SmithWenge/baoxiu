package nanqu.djtu.admin.maintenance.list.user.service.impl;


import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import nanqu.djtu.admin.maintenance.list.user.repository.UserMaintenanceListRepositoryI;
import nanqu.djtu.admin.maintenance.list.user.service.UserMaintenanceListServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserMaintenanceListServiceImpl implements UserMaintenanceListServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(UserMaintenanceListServiceImpl.class);
    @Autowired
    private UserMaintenanceListRepositoryI maintenanceListRepository;

    @Override
    public List<PlaceDistinct> query4ListPlaceDistinct() {
        return maintenanceListRepository.query4ListPlaceDistinct();
    }

    @Override
    public List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId) {
        return maintenanceListRepository.selectBuildingsByDistinctId(distinctId);
    }

    @Override
    public List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId) {
        return maintenanceListRepository.queryPlaceRoomByBuildingId(buildingId);
    }
    @Override
    public List<Equipment> queryEquipmentByRoomId(String roomId) {
        return maintenanceListRepository.queryEquipmentByRoomId(roomId);
    }
    @Transactional
    @Override
    public boolean saveNewMaintenanceList(MaintenanceList list) {


        try{
            list.setUserId(URLDecoder.decode("100", Charsets.UTF_8.displayName()));
            list.setUserTel(URLDecoder.decode("13456789", Charsets.UTF_8.displayName()));
            list.setListDescription(URLDecoder.decode(list.getListDescription(), Charsets.UTF_8.displayName()));

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        String equipmentId = list.getEquipmentId();
        String repairGroupId = maintenanceListRepository.selectRepairGroupId(equipmentId);
        if (Strings.isNullOrEmpty(repairGroupId)) {
            repairGroupId = ConstantFields.DEFAULT_GROUP_ID;
        }

        list.setRepairGroupId(repairGroupId);


        // 拼接维修单编号
        String distinctNumber = maintenanceListRepository.selectDistinctNumber(list.getDistinctId());
        String buildingNumber = maintenanceListRepository.selectBuildingNumber(list.getBuildingId());
        String roomNumber = maintenanceListRepository.selectRoomNumber(list.getRoomId());
        String equipmentNumber = maintenanceListRepository.selectEquipmentNumber(equipmentId);

        StringBuilder builder = new StringBuilder();

        builder.append(Strings.padStart(distinctNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(buildingNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(roomNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(equipmentNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));

        list.setListNumber(builder.toString());
        list.setListState("1");

        boolean notExit = maintenanceListRepository.selectIfExistMaintenanceList(builder.toString());


        if (notExit) {
            return maintenanceListRepository.insertNew(list) && maintenanceListRepository.insertNewListState(list);
        } else {
            return false;
        }
    }

}
