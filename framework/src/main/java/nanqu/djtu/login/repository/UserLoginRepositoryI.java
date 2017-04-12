package nanqu.djtu.login.repository;

import nanqu.djtu.pojo.AdminUser;

public interface UserLoginRepositoryI {
    /**
     * 查询通过shiro管理的登录用户的管理员信息
     *
     * @param adminId 登陆用户Id
     * @return 返回管理员详细信息
     */
    AdminUser selectAdminUser(String adminId);
    /**
     * 查询通过用户的id
     *
     * @param adminUserId 登陆用户Id
     * @return 返回管理员登录信息
     */

    AdminUser queryUserId(String adminUserId);

    /**
     * 更新密码
     * @param adminUser
     * @return
     */

    boolean updatePassword(AdminUser adminUser);
}
