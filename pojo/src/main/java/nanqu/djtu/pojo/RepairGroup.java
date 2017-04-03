package nanqu.djtu.pojo;

public class RepairGroup {
    private String repairGroupId;
    private String groupNumber;
    private String groupName;
    private String groupPrinterIP;

    public void setRepairGroupId(String repairGroupId) {
        this.repairGroupId = repairGroupId;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupPrinterIP(String groupPrinterIP) {
        this.groupPrinterIP = groupPrinterIP;
    }

    public String getRepairGroupId() {

        return repairGroupId;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupPrinterIP() {
        return groupPrinterIP;
    }
}
