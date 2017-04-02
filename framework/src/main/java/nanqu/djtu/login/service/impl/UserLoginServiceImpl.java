package nanqu.djtu.login.service.impl;

import nanqu.djtu.login.repository.UserLoginRepositoryI;
import nanqu.djtu.login.service.UserLoginServiceI;
import nanqu.djtu.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginServiceI {
    @Autowired
    private UserLoginRepositoryI userLoginRepository;

    @Override
    public AdminUser queryAdminUser(String adminId) {
        return userLoginRepository.selectAdminUser(adminId);
    }
}
