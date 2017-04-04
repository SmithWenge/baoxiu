package nanqu.djtu.pojo;

/**
 * Created by DELL on 2017/4/3.
 */
public class RepairGroup {
    private String repairGroupId;
    private String groupNumber;
    private String groupName;
    private String groupPrinterIp;
    private String hiddenGroupNumber;

    public String getHiddenGroupNumber() {
        return hiddenGroupNumber;
    }

    public void setHiddenGroupNumber(String hiddenGroupNumber) {
        this.hiddenGroupNumber = hiddenGroupNumber;
    }



    public String getRepairGroupId() {  return repairGroupId; }
    public void setRepairGroupId(String repairGroupId) {
        this.repairGroupId = repairGroupId;
    }
    public String getGroupNumber() {  return groupNumber; }
    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
    public String getGroupName() {  return groupName; }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupPrinterIp() { return groupPrinterIp; }
    public void setGroupPrinterIp(String groupPrinterIp) {
        this.groupPrinterIp = groupPrinterIp;
    }
}
