package nanqu.djtu.admin.user.repository;

import nanqu.djtu.pojo.AdminUser;

import java.util.List;

/**
 * Created by zwz on 2017/4/9.
 */
public interface UserRepositoryI {
    /**
     * 查询管理员用户信息列表
     *
     * @return 所有未删除管理员用户的信息
     */
    List<AdminUser> selectAdminUserList();

    /**
     *添加新的管理员信息
     * @param adminUser 地点对象
     * @return true false
     */
    boolean saveNewAdminUser(AdminUser adminUser);

    /**
     * 编号唯一性判断
     * @param adminNumber 管理员编号
     * @return true or false
     */
    boolean query4AdminNumberUnique(String adminNumber);

    /**
     * 验证登录名的唯一
     *
     * @param username 管理员的名
     * @return 唯一返回true, else false
     */
    boolean query4UsernameUnique(String username);

    /**
     * 插入用户名密码表
     * @return true or false
     */
    boolean saveNewUser(AdminUser adminUser);

    /**
     * 查询管理员用户信息
     * @param adminUserId 管理用户id
     * @return 管理员用户对象
     */
    AdminUser query4Edit(String adminUserId);

    /**
     * 更新管理员用户信息
     * @param adminUser 管理员用户对象
     * @return true or false
     */
    boolean updateAdminUser(AdminUser adminUser);

    boolean updateUser(AdminUser adminUser);
    
    /**
     * 逻辑删除管理员用户信息
     * @param adminUserId 管理员用户Id
     * @return true or false
     */
    boolean deleteAdminUser(String adminUserId);

    boolean deleteUser(String userId);

    /**
     * 通过adminuserId 查userId
     * @return 管理员用户对象
     */
    AdminUser selectUserIdByAdminUserId(String adminUserId);

    /**
     * 修改管理员用户密码
     * @param adminUser
     * @return
     */
    boolean updatePassword(AdminUser adminUser);

    /**
     * 查询所有用户以及角色
     * @return
     */
    List<AdminUser> selectAdminUserRolesList();

    /**
     * 查询用户已有的角色
     * @param userId
     * @return
     */
    List<AdminUser> selectExitRolesById(String userId);

    /**
     * 查询用户没有的角色
     * @param
     * @return
     */
    List<AdminUser> selectUnExitRolesById(String userId);

    /**
     * 删除用户原来绑定的角色
     * @param userId
     * @return
     */
    Boolean deleteOldRole(String userId);

    /**
     * 为用户添加新的角色
     * @param userId
     * @param roles
     * @return
     */
    Boolean insertNewRoles(String userId,String[] roles);
}
