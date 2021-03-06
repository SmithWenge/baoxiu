package nanqu.djtu.pojo;

public class User {
    private int userId;
    private String username;
    private String password;
    private String adminUserId;
    private String bornTime;
    private String workTime;
    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public User(String adminUserId, String username, String password) {
        this.adminUserId = adminUserId;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public int getUserId() {

        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAdminUserId() {
        return adminUserId;
    }
}
