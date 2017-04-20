package nanqu.djtu.app.user.service.impl;

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

        list.setListState("1");
        list.setRepairGroupId(repairGroupId);
        String distinctId = list.getDistinctId();
        String buildingId = list.getBuildingId();
        String roomId = list.getRoomId();
        //其他选项的判断
        if(distinctId.equals(ConstantFields.OTHER_SELECT) || buildingId.equals(ConstantFields.OTHER_SELECT)|| roomId.equals(ConstantFields.OTHER_SELECT) || equipmentId.equals(ConstantFields.OTHER_SELECT)){

            list.setListNumber(PrimaryKeyUtil.uuidPrimaryKey());

            if (userAppRepository.insertNew(list) && userAppRepository.insertNewListState(list)) {

                return list;

            }else {

                return null;

            }
        }else{
            // 拼接维修单编号
            String distinctNumber = userAppRepository.selectDistinctNumber(distinctId);
            String buildingNumber = userAppRepository.selectBuildingNumber(list.getBuildingId());
            String roomNumber = userAppRepository.selectRoomNumber(list.getRoomId());
            String equipmentNumber = userAppRepository.selectEquipmentNumber(equipmentId);

            StringBuilder builder = new StringBuilder();

            builder.append(Strings.padStart(distinctNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            builder.append(Strings.padStart(buildingNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            builder.append(Strings.padStart(roomNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            builder.append(Strings.padStart(equipmentNumber, ConstantFields.MIN_NUMBER_LENGTH, ConstantFields.PAD_NUMBER_CHAR));
            list.setListNumber(builder.toString());
            boolean notExit = userAppRepository.selectIfExistMaintenanceList(builder.toString());
            if (notExit) {
                if (userAppRepository.insertNew(list) && userAppRepository.insertNewListState(list)) {
                    return list;
                }
                return null;
            } else {

                list.setMaintenanceExit(true);
                return list;

            }
        }
    }
    @Override
    public List<Equipment> queryPlaceRoomByRoomId(String roomId) {
        return userAppRepository.queryPlaceRoomByRoomId(roomId);
    }

    @Override
    public MaintenanceList queryAllName(MaintenanceList maintenanceList) {
        return  otherService( maintenanceList);

    }

    @Override
    public List<MaintenanceList> queryMaintenanceListByTel(String userTel) {
        return userAppRepository.selectMaintenanceListByTel(userTel);
    }

    @Override
    public MaintenanceList queryOneMaintenance(String listNumber) {
        return userAppRepository.selectOneMaintenance(listNumber);
    }

    @Override
    public int sumOfMaintenance() {
        return userAppRepository.sumOfMaintenance();
    }

    @Override
    public String queryListStateTime(String listNumber) {

        return userAppRepository.selectListStateTime(listNumber);
    }

    @Override
    public List<MaintenanceList> queryAllState(String listNumber) {
        return userAppRepository.selectAllState(listNumber);
    }

    @Override
    public List<PlaceDistinct> queryDistincts() {
        return userAppRepository.queryDistincts();
    }

    @Override
    public int queryEachDistinctMaintenanceNumber(String distinctId) {
        return userAppRepository.queryEachDistinctMaintenanceNumber(distinctId);
    }

    /**
     * 有关其他的业务处理
     * @param maintenanceList
     * @return
     */
    public MaintenanceList otherService(MaintenanceList maintenanceList){
        if(maintenanceList.getBuildingId().equals(ConstantFields.OTHER_SELECT)){

            MaintenanceList list = userAppRepository.selectDistinctName(maintenanceList);
            list.setBuildingName(ConstantFields.QI_TA);
            list.setRoomName(ConstantFields.QI_TA);
            list.setEquipmentName(ConstantFields.QI_TA);

            return list;

        }else if(maintenanceList.getRoomId().equals(ConstantFields.OTHER_SELECT)){

            MaintenanceList list = userAppRepository.selectDistinctNameAndBuildingName(maintenanceList);

            list.setRoomName(ConstantFields.QI_TA);
            list.setEquipmentName(ConstantFields.QI_TA);

            return list;

        }else if(maintenanceList.getEquipmentId().equals(ConstantFields.OTHER_SELECT)){

            MaintenanceList list = userAppRepository.selectDistinctNameAndBuildingNameAndRoomName(maintenanceList);

            list.setEquipmentName(ConstantFields.QI_TA);

            return list;

        }else{

            return userAppRepository.selectAllName(maintenanceList);

        }
    }


}
