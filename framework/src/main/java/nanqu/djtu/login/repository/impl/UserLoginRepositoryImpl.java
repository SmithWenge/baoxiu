package nanqu.djtu.login.repository.impl;

import nanqu.djtu.login.repository.UserLoginRepositoryI;
import nanqu.djtu.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserLoginRepositoryImpl implements UserLoginRepositoryI {
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
}
