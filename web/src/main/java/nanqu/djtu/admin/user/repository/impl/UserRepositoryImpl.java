package nanqu.djtu.admin.user.repository.impl;

import nanqu.djtu.admin.place.distinct.repository.impl.PlaceDistinctRepositoryImpl;
import nanqu.djtu.admin.user.repository.UserRepositoryI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.utils.PasswordUtils;
import nanqu.djtu.utils.PrimaryKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepositoryI{
    private static final Logger LOG = LoggerFactory.getLogger(PlaceDistinctRepositoryImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AdminUser> queryAdminUserList() {
       String sql = "SELECT baoxiu.baoxiu_adminuser.adminUserId,username,adminName,userId,password,adminGender,adminEmail,adminNumber,adminState,adminCard,adminTelephone  FROM baoxiu.baoxiu_adminuser join baoxiu.shiro_user on (baoxiu.baoxiu_adminuser.adminUserId = baoxiu.shiro_user.adminUserId ) where baoxiu.baoxiu_adminuser.deleteflag = 0 and baoxiu.shiro_user.deleteFlag = 0 ;";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new queryAdminUserListRowMapper() );
        }catch(Exception e){
            LOG.error("[AdminUser] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }

    }

    private class queryAdminUserListRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
           AdminUser adminUser = new AdminUser();
            adminUser.setAdminUserId(rs.getString("adminUserId"));
            adminUser.setAdminName(rs.getString("adminName"));
            adminUser.setAdminGender(rs.getInt("adminGender"));
            adminUser.setAdminEmail(rs.getString("adminEmail"));
            adminUser.setAdminNumber(rs.getString("adminNumber"));
            adminUser.setAdminState(rs.getInt("adminState"));
            adminUser.setAdminCard(rs.getString("adminCard"));
            adminUser.setAdminTelephone(rs.getString("adminTelephone"));
            adminUser.setUsername(rs.getString("username"));
            adminUser.setPassword(PasswordUtils.encrypt(rs.getString("password")));
            adminUser.setUserId(rs.getString("userId"));

            return adminUser;
        }
    }
    @Override
    public boolean saveNewAdminUser(AdminUser adminUser) {
        String sql = "INSERT into baoxiu.baoxiu_adminuser(adminUserId,adminName,adminGender,adminEmail,adminNumber,adminState,adminCard,adminTelephone) VALUES (?,?,?,?,?,?,?,?)";
        Object[] args = {
                adminUser.getAdminUserId(),
                adminUser.getAdminName(),
                adminUser.getAdminGender(),
                adminUser.getAdminEmail(),
                adminUser.getAdminNumber(),
                adminUser.getAdminState(),
                adminUser.getAdminCard(),
                adminUser.getAdminTelephone(),
        };

        try {
                return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {
            LOG.error("[AdminUser] add new adminUser error with info {}.", e.getMessage());
            return false;
        }


    }
    /**
     * 编号唯一性判断
     * @param adminNumber 管理员编号
     * @return true or false
     */
    @Override
    public boolean query4AdminNumberUnique(String adminNumber) {
        String sql = "SELECT count(1) as NUM FROM baoxiu.baoxiu_adminuser where adminNumber = ?";
        Object[] args = {adminNumber};

        try{
            return jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * 验证登录名的唯一
     *
     * @param username 管理员的名
     * @return 唯一返回true, else false
     */
    @Override
    public boolean query4UsernameUnique(String username) {
       String sql = "SELECT count(1) as NUM  FROM baoxiu.shiro_user where username = ?";
        Object[] args = {username};

        try{
            return  jdbcTemplate.queryForObject(sql,args,Integer.class) == 0;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 插入用户名密码表
     * @return true or false
     */
    @Override
    public boolean saveNewUser(AdminUser adminUser) {
        String sql = "INSERT into baoxiu.shiro_user(userId, username,password,adminUserId) values(?,?,?,?)";
        Object[] args = {
                PrimaryKeyUtil.uuidPrimaryKey(),
                adminUser.getUsername(),
                PasswordUtils.encrypt(adminUser.getPassword()),
                adminUser.getAdminUserId()
        };

        try{

            return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {
            LOG.error("[AdminUser] add new user error with info {}.", e.getMessage());
            return false;
        }
    }

    @Override
    public AdminUser query4Edit(String adminUserId) {
        String sql = "SELECT baoxiu.baoxiu_adminuser.adminUserId,userId, username,password,adminName,adminGender,adminEmail,adminNumber,adminState,adminCard,adminTelephone  FROM baoxiu.baoxiu_adminuser join baoxiu.shiro_user on (baoxiu.baoxiu_adminuser.adminUserId = baoxiu.shiro_user.adminUserId ) where baoxiu.baoxiu_adminuser.adminUserId = ? ";
        Object[] args  = {adminUserId};
        try{
            return jdbcTemplate.queryForObject(sql,args,new queryAdminUserListRowMapper() );
        } catch(Exception e){
            LOG.error("[AdminUser] query4List error with info {}.", e.getMessage());

            return null;
        }

    }

    /**
     * 更新管理员用户信息
     * @param adminUser 管理员用户对象
     * @return true or false
     */
    @Override
    public boolean updateAdminUser(AdminUser adminUser) {
        String sql = "UPDATE baoxiu.baoxiu_adminuser set  adminName=?,adminGender=?,adminEmail = ?,adminNumber = ?,adminState = ? ,adminCard= ?,adminTelephone=? where adminUserId = ?";
        Object[] args = {
                adminUser.getAdminName(),
                adminUser.getAdminGender(),
                adminUser.getAdminEmail(),
                adminUser.getAdminNumber(),
                adminUser.getAdminState(),
                adminUser.getAdminCard(),
                adminUser.getAdminTelephone(),
                adminUser.getAdminUserId()
        };

        try {
            return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e) {
            LOG.error("[AdminUser] update adminUser error with info {}.", e.getMessage());
            return false;
        }


    }
    @Override
    public boolean updateUser(AdminUser adminUser) {
        String  sql = "UPDATE  baoxiu.shiro_user set username = ?,password = ?  WHERE deleteFlag = 0 AND UserId = ?";
        Object[] args = {
                adminUser.getUsername(),
                PasswordUtils.encrypt(adminUser.getPassword()),
                adminUser.getUserId()
        };
        try {
                 return  jdbcTemplate.update(sql,args) == 1;
             }catch (Exception e) {
                    LOG.error("[AdminUser] update user error with info {}.", e.getMessage());
                 return false;
             }
    }

    @Override
    public boolean deleteAdminUser(String adminUserId) {
       String sql = "UPDATE  baoxiu.baoxiu_adminuser set deleteFlag = 1 where adminUserId = ?";
        Object[] args = {adminUserId};

        try {
                return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e){
            LOG.error("[AdminUser] deleteAdminUser error with info {}.", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(String userId) {
       String sql = "UPDATE baoxiu.shiro_user SET deleteFlag = 1 WHERE userId =?";
        Object[] args = {userId};

        try {
            return  jdbcTemplate.update(sql,args) == 1;
        }catch (Exception e){
            LOG.error("[AdminUser] deleteUser error with info {}.", e.getMessage());
            return false;
        }

    }

    @Override
    public AdminUser selectUserIdByAdminUserId(String adminUserId) {
        String sql = "SELECT userId FROM baoxiu.shiro_user WHERE adminUserId = ?";
        Object[] args = {adminUserId};

        try{
            return  jdbcTemplate.queryForObject(sql,args,new selectUserIdByAdminUserIdRowMApper());
        }catch(Exception e){
            LOG.error("[AdminUser] selectUserIdByAdminUserId error with info {}.", e.getMessage());

            return null;
        }
    }

    private class selectUserIdByAdminUserIdRowMApper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setUserId(rs.getString("userId"));

            return adminUser;
        }
    }

}