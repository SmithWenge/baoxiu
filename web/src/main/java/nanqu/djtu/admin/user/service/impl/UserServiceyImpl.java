package nanqu.djtu.admin.user.service.impl;

import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
import nanqu.djtu.admin.user.repository.UserRepositoryI;
import nanqu.djtu.admin.user.service.UserServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceyImpl implements UserServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceDistinctRepositoryImpl.class);
    @Autowired
    private UserRepositoryI userRepository;

    @Override
    public List<AdminUser> queryAdminUserList() {
        return userRepository.queryAdminUserList();
    }

    @Transactional
    @Override
    public boolean saveNewAdminUser(AdminUser adminUser, AdminUser user) {
        adminUser.setAdminUserId(PrimaryKeyUtil.uuidPrimaryKey());
        boolean insert = userRepository.saveNewAdminUser(adminUser) && userRepository.saveNewUser(adminUser);
        if(insert) {
            LOG.info("[AdminUser] add new adminUser success with user {}.", user.getAdminName());
        }else {
            LOG.warn("[AdminUser] add new adminUser failure with user {}.", user.getAdminName());
        }
        return insert;
    }

    @Override
    public boolean query4AdminNumberUnique(String adminNumber) {
        return userRepository.query4AdminNumberUnique(adminNumber);
    }

    @Override
    public boolean query4UsernameUnique(String username) {
        return userRepository.query4UsernameUnique(username );
    }

    @Override
    public AdminUser query4Edit(String adminUserId) {
        return userRepository.query4Edit(adminUserId);
    }

    @Transactional
    @Override
    public boolean updateAdminUser(AdminUser adminUser, AdminUser user) {
        boolean update = userRepository.updateAdminUser(adminUser) && userRepository.updateUser(adminUser);

        if(update) {
            LOG.info("[AdminUser] update adminUser success with user {}.", user.getAdminName());
        }else {
            LOG.warn("[AdminUser] update adminUser failure with user {}.", user.getAdminName());
        }
        return update;
    }

    @Transactional
    @Override
    public boolean deleteAdminUser(AdminUser adminUser, AdminUser user) {
        boolean delete = userRepository.deleteAdminUser(adminUser.getAdminUserId()) && userRepository.deleteUser(adminUser.getUserId());
        if(delete) {
            LOG.info("[AdminUser] delete adminUser success with user {}.", user.getAdminName());
        }else {
            LOG.warn("[AdminUser] delete adminUser failure with user {}.", user.getAdminName());
        }
        return delete;
    }

    @Override
    public AdminUser selectUserIdByAdminUserId(String adminUserId) {
        return userRepository.selectUserIdByAdminUserId(adminUserId);
    }

}
