package nanqu.djtu.pojo;

public class LoginUser {
    private String adminId;
    private String adminName;
    private String adminLoginName;
    private String adminLoginPass;
    private int adminIsLock;
    private int adminRole;
    private int deleteFlag;
    private String adminEmail;
    private String adminLoginPassNew;
    private String adminLoginPassNewRe;
    private int adminIsChanged;
    private int adminIsReturn;

    public void setAdminIsChanged(int adminIsChanged) {
        this.adminIsChanged = adminIsChanged;
    }

    public void setAdminIsReturn(int adminIsReturn) {
        this.adminIsReturn = adminIsReturn;
    }

    public int getAdminIsChanged() {

        return adminIsChanged;
    }

    public int getAdminIsReturn() {
        return adminIsReturn;
    }

    public void setAdminLoginPassNew(String adminLoginPassNew) {
        this.adminLoginPassNew = adminLoginPassNew;
    }

    public void setAdminLoginPassNewRe(String adminLoginPassNewRe) {
        this.adminLoginPassNewRe = adminLoginPassNewRe;
    }

    public String getAdminLoginPassNew() {

        return adminLoginPassNew;
    }

    public String getAdminLoginPassNewRe() {
        return adminLoginPassNewRe;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminEmail() {

        return adminEmail;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName;
    }

    public void setAdminLoginPass(String adminLoginPass) {
        this.adminLoginPass = adminLoginPass;
    }

    public void setAdminIsLock(int adminIsLock) {
        this.adminIsLock = adminIsLock;
    }

    public void setAdminRole(int adminRole) {
        this.adminRole = adminRole;
    }

    public String getAdminId() {

        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public String getAdminLoginPass() {
        return adminLoginPass;
    }

    public int getAdminIsLock() {
        return adminIsLock;
    }

    public int getAdminRole() {
        return adminRole;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getDeleteFlag() {

        return deleteFlag;
    }
}
