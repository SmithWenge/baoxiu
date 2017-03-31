package nanqu.djtu.shiro.service.impl;

import nanqu.djtu.pojo.Permission;
import nanqu.djtu.pojo.Role;
import nanqu.djtu.pojo.User;
import nanqu.djtu.shiro.repository.ShiroRepositoryI;
import nanqu.djtu.shiro.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private ShiroRepositoryI shiroRepository;

    @Override
    public User queryByName(String username) {
        return shiroRepository.selectByUsername(username);
    }

    @Override
    public List<Role> queryRoles(int userId) {
        return shiroRepository.selectRoles(userId);
    }

    @Override
    public List<Permission> queryRolePermissions(String roleId) {
        return shiroRepository.selectPermissions(roleId);
    }
}
