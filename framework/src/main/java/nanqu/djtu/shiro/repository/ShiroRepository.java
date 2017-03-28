package nanqu.djtu.shiro.repository;

import nanqu.djtu.pojo.Permission;
import nanqu.djtu.pojo.Role;
import nanqu.djtu.pojo.User;

import java.util.List;

public interface ShiroRepository {
    /**
     *  更具用户的登录名查询用户信息
     *
     * @param username 用户登录名
     * @return 如果用户存在返回用户对象, else null
     */
    User selectByUsername(String username);

    /**
     * 查询这个用户的所有角色
     *
     * @param userId 登陆用户的Id
     * @return 返回这个用户的所拥有的角色
     */
    List<Role> selectRoles(int userId);

    /**
     * 查询这个角色的所有权限
     *
     * @param roleId 用户拥有的角色
     * @return 这个角色的所有权限
     */
    List<Permission> selectPermissions(String roleId);
}
