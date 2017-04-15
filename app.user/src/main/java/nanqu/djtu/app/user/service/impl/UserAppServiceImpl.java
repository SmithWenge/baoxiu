package nanqu.djtu.app.user.service.impl;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import nanqu.djtu.app.user.repository.UserAppRepositoryI;
import nanqu.djtu.app.user.service.UserAppServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Service
public class UserAppServiceImpl implements UserAppServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(UserAppServiceImpl.class);

    @Autowired
    private UserAppRepositoryI userAppRepository;

    @Override
    public List<PlaceDistinct> query4ListPlaceDistinct() {
        return userAppRepository.query4ListPlaceDistinct();
    }

    @Override
    public List<PlaceBuilding> queryBuildingsByDistinctId(String distinctId) {
        return userAppRepository.selectBuildingsByDistinctId(distinctId);
    }

    @Override
    public List<PlaceRoom> queryPlaceRoomByBuildingId(String buildingId) {
        return userAppRepository.queryPlaceRoomByBuildingId(buildingId);
    }


    @Transactional
    @Override
    public MaintenanceList saveNewMaintenanceList(MaintenanceList list) {

        list.setUserId(PrimaryKeyUtil.uuidPrimaryKey());
        list.setListDescription(list.getListDescription());

        String equipmentId = list.getEquipmentId();
        String repairGroupId = userAppRepository.selectRepairGroupId(equipmentId);

        if (Strings.isNullOrEmpty(repairGroupId)) {
            repairGroupId = ConstantFields.DEFAULT_GROUP_ID;
        }

        list.setRepairGroupId(repairGroupId);

        // 拼接维修单编号
        String distinctNumber = userAppRepository.selectDistinctNumber(list.getDistinctId());
        String buildingNumber = userAppRepository.selectBuildingNumber(list.getBuildingId());
        String roomNumber = userAppRepository.selectRoomNumber(list.getRoomId());
        String equipmentNumber = userAppRepository.selectEquipmentNumber(equipmentId);

        StringBuilder builder = new StringBuilder();

        builder.append(Strings.padStart(distinctNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(buildingNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(roomNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
        builder.append(Strings.padStart(equipmentNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));

        list.setListNumber(builder.toString());
        list.setListState("1");

        boolean notExit = userAppRepository.selectIfExistMaintenanceList(builder.toString());


        if (notExit) {
            if (userAppRepository.insertNew(list) && userAppRepository.insertNewListState(list)) {
                return list;
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public List<Equipment> queryPlaceRoomByRoomId(String roomId) {
        return userAppRepository.queryPlaceRoomByRoomId(roomId);
    }

    @Override
    public MaintenanceList selectAllName(MaintenanceList maintenanceList) {
        return userAppRepository. selectAllName(maintenanceList);
    }

    @Override
    public List<MaintenanceList> selectMaintenanceListByTel(String userTel) {
        return userAppRepository.selectMaintenanceListByTel(userTel);
    }

    @Override
    public MaintenanceList selectOneMaintenance(String listNumber) {
        return userAppRepository.selectOneMaintenance(listNumber);
    }

    @Override
    public int sumOfMaintenance() {
        return userAppRepository.sumOfMaintenance();
    }

    @Override
    public String selectListStateTime(String listNumber) {

        return userAppRepository.selectListStateTime(listNumber);
    }

    @Override
    public List<MaintenanceList> selectAllState(String listNumber) {
        return userAppRepository.selectAllState(listNumber);
    }


}
