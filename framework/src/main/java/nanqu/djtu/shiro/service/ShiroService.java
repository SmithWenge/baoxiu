package nanqu.djtu.shiro.service;

import nanqu.djtu.pojo.Permission;
import nanqu.djtu.pojo.Role;
import nanqu.djtu.pojo.User;

import java.util.List;

public interface ShiroService {
    User queryByName(String username);
    List<Role> queryRoles(int userId);
    List<Permission> queryRolePermissions(String roleId);
}
