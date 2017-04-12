package nanqu.djtu.login.service.impl;

import nanqu.djtu.login.repository.UserLoginRepositoryI;
import nanqu.djtu.login.service.UserLoginServiceI;
import nanqu.djtu.pojo.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(UserLoginServiceImpl.class);
    @Autowired
    private UserLoginRepositoryI userLoginRepository;

    @Override
    public AdminUser queryAdminUser(String adminId) {
        return userLoginRepository.selectAdminUser(adminId);
    }

    @Override
    public AdminUser queryUserId(String adminUserId) {
        return userLoginRepository.queryUserId(adminUserId);
    }

    @Override
    public boolean updatePassword(AdminUser adminUser, AdminUser user) {
        boolean update = userLoginRepository.updatePassword(adminUser);

        if (update) {
            LOG.info("[AdminUser] update adminUser {} success with user {}.", user.getAdminUserId(), user.getAdminName());
        } else {
            LOG.warn("[AdminUser] update adminUser {} failure with user {}.", user.getAdminUserId(), user.getAdminName());
        }

        return update;
    }
}
