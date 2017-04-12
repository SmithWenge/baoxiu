package nanqu.djtu.admin.user.service;

import nanqu.djtu.pojo.AdminUser;

import java.util.List;

/**
 * Created by zwz on 2017/4/9.
 */
public interface UserServiceI {
    List<AdminUser> queryAdminUserList();

    boolean saveNewAdminUser(AdminUser adminUser, AdminUser user);

    boolean query4AdminNumberUnique(String adminNumber);

    boolean query4UsernameUnique(String username);


    AdminUser query4Edit(String adminUserId);

    boolean updateAdminUser(AdminUser adminUser, AdminUser user);

    boolean deleteAdminUser(AdminUser adminUser, AdminUser user);

    AdminUser selectUserIdByAdminUserId(String adminUserId);

    boolean updatePassword(AdminUser adminUser, AdminUser user);
}
