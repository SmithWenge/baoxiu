package nanqu.djtu.pojo;

import java.util.List;

public class MaintenanceList {
    private String listNumber;
    private String userId;
    private String userTel;
    private String repairGroupId;
    private String roomId;
    private String buildingId;
    private String distinctId;
    private String equipmentId;
    private int listState;
    private String listDescription;
    private String listBigDescription;
    private String listTime;
    private String startListTime;
    private String stopListTime;
    private String equipmentName;
    private String groupName;
    private String distinctName;
    private String distinctNumber;
    private String roomName;
    private String buildingName;
    private String listPicture;
    private String liststatetime;
    private String liststateStr;
    private List<MaintenanceList> lists;
    private String listStatusTime;
    private int sum;

    private int eachDistinctMaintenanceListNumber;//每个校区有多少保修单
    private boolean maintenanceExit;//判断保修单是否存在
    private int waitToDoMaintenanceSum;//待办报修单数
    private int onDoMaintenanceSum;//在办报修单数
    private int allDoMaintenanceSum;//所有要工人处理的报修单数

    private String listStateFrontStyleColor; // 前台状态圆点颜色

    public void setListState(int listState) {
        this.listState = listState;
    }

    public void setListStatusTime(String listStatusTime) {
        this.listStatusTime = listStatusTime;
    }

    public void setListStateFrontStyleColor(String listStateFrontStyleColor) {
        this.listStateFrontStyleColor = listStateFrontStyleColor;
    }

    public String getListStateFrontStyleColor() {

        return listStateFrontStyleColor;
    }

    public int getOnDoMaintenanceSum() {
        return onDoMaintenanceSum;
    }

    public void setOnDoMaintenanceSum(int onDoMaintenanceSum) {
        this.onDoMaintenanceSum = onDoMaintenanceSum;
    }

    public int getWaitToDoMaintenanceSum() {
        return waitToDoMaintenanceSum;
    }

    public void setWaitToDoMaintenanceSum(int waitToDoMaintenanceSum) {
        this.waitToDoMaintenanceSum = waitToDoMaintenanceSum;
    }

    public int getAllDoMaintenanceSum() {
        return allDoMaintenanceSum;
    }

    public void setAllDoMaintenanceSum(int allDoMaintenanceSum) {
        this.allDoMaintenanceSum = allDoMaintenanceSum;
    }
    public boolean isMaintenanceExit() {
        return maintenanceExit;
    }
    public void setMaintenanceExit(boolean maintenanceExit) {
        this.maintenanceExit = maintenanceExit;
    }
    public int getEachDistinctMaintenanceListNumber() {
        return eachDistinctMaintenanceListNumber;
    }

    public String getListBigDescription() {
        return listBigDescription;
    }

    public void setListBigDescription(String listBigDescription) {
        this.listBigDescription = listBigDescription;
    }

    public void setEachDistinctMaintenanceListNumber(int eachDistinctMaintenanceListNumber) {
        this.eachDistinctMaintenanceListNumber = eachDistinctMaintenanceListNumber;
    }
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public List<MaintenanceList> getLists() {
        return lists;
    }

    public void setLists(List<MaintenanceList> lists) {
        this.lists = lists;
    }

    public String getListstatetime() {
        return liststatetime;
    }

    public void setListstatetime(String liststatetime) {
        this.liststatetime = liststatetime;
    }

    public String getListstateStr() {
        return liststateStr;
    }

    public void setListstateStr(String liststateStr) {
        this.liststateStr = liststateStr;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getListPicture() {
        return listPicture;
    }

    public void setListPicture(String listPicture) {
        this.listPicture = listPicture;
    }

    public String getEquipmentNumber() {
        return EquipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        EquipmentNumber = equipmentNumber;
    }

    private String EquipmentNumber;

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDistinctNumber() {
        return distinctNumber;
    }

    public void setDistinctNumber(String distinctNumber) {
        this.distinctNumber = distinctNumber;
    }

    private String buildingNumber;
    private String roomNumber;

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

    public String getListStatusTime() {
        return listStatusTime;
    }
}
