package nanqu.djtu.login.service;

import nanqu.djtu.pojo.AdminUser;

public interface UserLoginServiceI {
    AdminUser queryAdminUser(String adminId);
}
