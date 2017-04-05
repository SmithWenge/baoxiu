package nanqu.djtu.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class MaintenanceList {
    private String listNumber;
    private String userId;
    private String userName;
    private String repairGroupId;
    private String roomId;
    private String buildingId;
    private String distinctId;
    private String equipmentId;
    private int listState;
    private String liststateStr;
    private String listDescription;
    private String listTime;
    private String startListTime;
    private String stopListTime;
    private String equipmentName;
    private String groupName;
    private String distinctName;
    private String roomName;
    private String buildingName;
    private String listStatusTime;
    private String listPicture;

    public String getListstateStr() {
        return liststateStr;
    }

    public void setListstateStr(String liststateStr) {
        this.liststateStr = liststateStr;
    }

    public String getListPicture() {
        return listPicture;
    }

    public void setListPicture(String listPicture) {
        this.listPicture = listPicture;
    }

    public String getListStatusTime() {
        return listStatusTime;
    }

    public void setListStatusTime(String listStatusTime) {
        this.listStatusTime = listStatusTime;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStopListTime() {
        return stopListTime;
    }

    public void setStopListTime(String stopListTime) {
        this.stopListTime = stopListTime;
    }

    public String getStartListTime() {
        return startListTime;
    }

    public void setStartListTime(String startListTime) {
        this.startListTime = startListTime;
    }

    public String getListTime() {
        return listTime;
    }

    public void setListTime(String listTime) {
        this.listTime = listTime;
    }

    public String getDistinctName() {
        return distinctName;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setListNumber(String listNumber) {
        this.listNumber = listNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRepairGroupId(String repairGroupId) {
        this.repairGroupId = repairGroupId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setListState(String listState) {
        this.listState = Integer.parseInt(listState);
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }

    public String getListNumber() {
        return listNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getRepairGroupId() {
        return repairGroupId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getDistinctId() {
        return distinctId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public int getListState() {
        return listState;
    }

    public String getListDescription() {
        return listDescription;
    }
}
