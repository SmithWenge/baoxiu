package nanqu.djtu.shiro.manage.service;

import nanqu.djtu.pojo.Permission;
import nanqu.djtu.pojo.Role;
import nanqu.djtu.pojo.User;

import java.util.List;

public interface RoleManageServiceI {
    List<Role> queryAllRoles();
    List<Permission> queryPermissions();
    boolean addNewRole(String[] permissions, Role role, String logUser);
    List<Role> queryUserRoles(String adminUserId);
    List<Role> queryRoles();
    User queryUserInfo(String adminId);
    boolean updateUserRole(String[] roles, User user, String logUser);
    boolean query4RoleNameUnique(String roleName);
    List<Permission> queryRoleCheckPermissions(String roleId);
    List<Permission> queryRoleUnckeckedPermissions(String roleId);
    Role queryRoleInfo(String roleId);
    boolean updateRolePermissions(String[] editPermissions, Role role, String logUser);
}
