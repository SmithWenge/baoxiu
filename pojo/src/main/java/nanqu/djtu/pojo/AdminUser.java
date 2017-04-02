package nanqu.djtu.pojo;

public class AdminUser {
    private String adminUserId;  // 主键Id
    private String adminName;  // 工作人员姓名
    private int adminGender;  // 工作人员性别
    private String adminEmail;  // 工作人员邮箱
    private String adminNumber;  // 工作人员工作证号
    private int adminState;  // 工作人员状态
    private String adminCard;  // 工作人员身份证号
    private String adminTelephone;  // 维修人员联系电话

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminGender(int adminGender) {
        this.adminGender = adminGender;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public void setAdminState(int adminState) {
        this.adminState = adminState;
    }

    public void setAdminCard(String adminCard) {
        this.adminCard = adminCard;
    }

    public void setAdminTelephone(String adminTelephone) {
        this.adminTelephone = adminTelephone;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminUserId() {

        return adminUserId;
    }

    public String getAdminName() {
        return adminName;
    }

    public int getAdminGender() {
        return adminGender;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public int getAdminState() {
        return adminState;
    }

    public String getAdminCard() {
        return adminCard;
    }

    public String getAdminTelephone() {
        return adminTelephone;
    }
}
