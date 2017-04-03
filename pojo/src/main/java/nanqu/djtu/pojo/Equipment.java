package nanqu.djtu.pojo;

import java.util.List;

public class Equipment {
    private String equipmentId;  // 设备Id
    private String equipmentName;  // 设备名称
    private String equipmentNumber;  // 设备的唯一编号

    private String repairGroupId;  // 设备维修组的Id
    private String repairGroupName;  // 维修小组名
    private String repairGroupNumber;  // 维修小组编号

    private List<EquipmentSet> sets;  // 设备组
    private String setId;  // 设备组Id

    public void setRepairGroupNumber(String repairGroupNumber) {
        this.repairGroupNumber = repairGroupNumber;
    }

    public String getRepairGroupNumber() {

        return repairGroupNumber;
    }

    public void setRepairGroupName(String repairGroupName) {
        this.repairGroupName = repairGroupName;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getRepairGroupName() {

        return repairGroupName;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setSets(List<EquipmentSet> sets) {
        this.sets = sets;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public List<EquipmentSet> getSets() {

        return sets;
    }

    public String getSetId() {
        return setId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setRepairGroupId(String repairGroupId) {
        this.repairGroupId = repairGroupId;
    }

    public String getEquipmentId() {

        return equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getRepairGroupId() {
        return repairGroupId;
    }
}
