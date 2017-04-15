package nanqu.djtu.login.repository.impl;

import nanqu.djtu.login.repository.UserLoginRepositoryI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserLoginRepositoryImpl implements UserLoginRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(UserLoginRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询通过shiro管理的登录用户的管理员信息
     *
     * @param adminId 登陆用户Id
     * @return 返回管理员详细信息
     */
    @Override
    public AdminUser selectAdminUser(String adminId) {
        String sql = "SELECT adminUserId, adminName FROM baoxiu_adminuser WHERE adminUserId = ? AND deleteFlag = 0";
        Object[] args = {
                adminId
        };


        try {
            return jdbcTemplate.queryForObject(sql, args, new SelectAdminUserRowMapper());
        } catch (Exception e) {
            return null;
        }
    }



    private class SelectAdminUserRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            AdminUser adminUser = new AdminUser();

            adminUser.setAdminUserId(rs.getString("adminUserId"));
            adminUser.setAdminName(rs.getString("adminName"));

            return adminUser;
        }
    }

    /**
     * 查询通过用户的id
     *
     * @param adminUserId 登陆用户Id
     * @return 返回管理员登录信息
     */
    @Override
    public AdminUser queryUserId(String adminUserId) {
        String sql = "SELECT userId , username,adminUserId  FROM baoxiu.shiro_user WHERE adminUserId = ?";
        Object[] args = {adminUserId};

        try {
            return jdbcTemplate.queryForObject(sql,args,new queryUserIdRowMapper());
        }catch (Exception e){
            return null;
        }

    }


    private class queryUserIdRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            AdminUser adminUser = new AdminUser();

            adminUser.setUserId(rs.getString("userId"));
            adminUser.setUsername(rs.getString("username"));
            adminUser.setAdminUserId(rs.getString("adminUserId"));

            return adminUser;
        }
    }

    /**
     * 更新密码
     * @param adminUser
     * @return
     */
    @Override
    public boolean updatePassword(AdminUser adminUser) {
       String sql = "UPDATE  baoxiu.shiro_user SET password = ? WHERE userId = ?";
        Object[] args = {
                            PasswordUtils.encrypt(adminUser.getPassword()),
                            adminUser.getUserId()
                        };

        try {
                return jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e){
            LOG.error("[AdminUser] update password error with info {}.", e.getMessage());
            return false;
        }
    }

}
