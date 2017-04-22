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
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    public List<AdminUser> selectAdminUserList() {
        String sql = "SELECT A.adminUserId,username,adminName,userId,adminGender,adminEmail,adminNumber,adminState,adminCard,adminTelephone FROM baoxiu_adminuser AS A LEFT JOIN shiro_user AS U on A.adminUserId = U.adminUserId where A.deleteflag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new SelectAdminUserListRowMapper() );
        }catch(Exception e){
            LOG.error("[AdminUser] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }

    }

    private class SelectAdminUserListRowMapper implements RowMapper<AdminUser> {

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
            adminUser.setUserId(rs.getString("userId"));

            return adminUser;
        }
    }
    @Override
    public boolean saveNewAdminUser(AdminUser adminUser) {
        String sql = "INSERT INTO baoxiu_adminuser(adminUserId,adminName,adminGender,adminEmail,adminNumber,adminState,adminCard,adminTelephone) VALUES (?,?,?,?,?,?,?,?)";
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
                return  jdbcTemplate.update(sql, args) == 1;
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
        String sql = "SELECT count(1) as NUM FROM baoxiu_adminuser where adminNumber = ?";
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
       String sql = "SELECT count(1) as NUM  FROM shiro_user where username = ?";
        Object[] args = {username};

        try{
            return  jdbcTemplate.queryForObject(sql, args, Integer.class) == 0;
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
        String sql = "INSERT INTO shiro_user(userId, username,password,adminUserId) VALUES (?,?,?,?)";
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
        String sql = "SELECT baoxiu_adminuser.adminUserId, userId, username, adminName, adminGender, adminEmail, adminNumber, adminState, adminCard, adminTelephone  FROM baoxiu_adminuser JOIN shiro_user ON (baoxiu_adminuser.adminUserId = shiro_user.adminUserId ) WHERE baoxiu_adminuser.adminUserId = ? ";
        Object[] args  = {adminUserId};
        try{
            return jdbcTemplate.queryForObject(sql,args,new SelectAdminUserListRowMapper() );
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
        String sql = "UPDATE baoxiu_adminuser SET adminName=?, adminGender=?, adminEmail=?, adminNumber = ?, adminState = ? , adminCard= ?, adminTelephone= ? WHERE adminUserId = ?";
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
        String  sql = "UPDATE shiro_user SET username = ? WHERE deleteFlag = 0 AND UserId = ?";
        Object[] args = {
                adminUser.getUsername(),
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
       String sql = "UPDATE  baoxiu_adminuser SET deleteFlag = 1 WHERE adminUserId = ?";
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
       String sql = "UPDATE shiro_user SET deleteFlag = 1 WHERE userId =?";
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
        String sql = "SELECT userId FROM shiro_user WHERE adminUserId = ?";
        Object[] args = {adminUserId};

        try{
            return  jdbcTemplate.queryForObject(sql, args, new selectUserIdByAdminUserIdRowMApper());
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

    /**
     * 修改管理员用户密码
     * @param adminUser
     * @return
     */
    @Override
    public boolean updatePassword(AdminUser adminUser) {
        String sql = "UPDATE  shiro_user SET password = ? WHERE userId = ?";
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

    /**
     * 查询所有的用户以及角色
     * @return
     */
    @Override
    public List<AdminUser> selectAdminUserRolesList() {
        String sql = "SELECT A.adminUserId,adminName,userId,adminNumber FROM baoxiu_adminuser AS A LEFT JOIN shiro_user AS U on A.adminUserId = U.adminUserId where A.deleteflag = 0";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql,args,new SelectAdminUserRolesListRowMapper() );
        }catch(Exception e){
            LOG.error("[AdminUser] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectAdminUserRolesListRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminUser adminUser = new AdminUser();

            adminUser.setAdminUserId(rs.getString("adminUserId"));
            adminUser.setAdminName(rs.getString("adminName"));
            adminUser.setAdminNumber(rs.getString("adminNumber"));
            adminUser.setUserId(rs.getString("userId"));

            return adminUser;
        }
    }

    /**
     * 查询用户已选的角色
     * @param userId
     * @return
     */
    @Override
    public List<AdminUser> selectExitRolesById(String userId) {
        String sql = "SELECT U.userId,R.roleId,R.roleName FROM shiro_userrole AS U LEFT JOIN shiro_role AS R ON U.roleId = R.roleId WHERE U.userId = ?";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.query(sql,args,new SelectExitRolesByIdRowMapper() );
        }catch(Exception e){
            LOG.error("[AdminUser] select roles error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectExitRolesByIdRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminUser user = new AdminUser();
            user.setRoleId(rs.getString("roleId"));
            user.setRoleName(rs.getString("roleName"));
            user.setUserId(rs.getString("userId"));

            return user;
        }
    }

    /**
     * 查询用户没有的角色
     * @param
     * @return
     */
    @Override
    public List<AdminUser> selectUnExitRolesById(String userId) {
        String sql = "SELECT roleId,roleName FROM shiro_role WHERE roleId NOT IN (SELECT roleId FROM shiro_userrole WHERE userId = ?)";

        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.query(sql,args,new SelectUnExitRolesByIdRowMapper() );
        }catch(Exception e){
            LOG.error("[AdminUser] select roles error with info {}.", e.getMessage());

            return new ArrayList<>();
        }
    }

    class SelectUnExitRolesByIdRowMapper implements RowMapper<AdminUser> {

        @Override
        public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminUser user = new AdminUser();
            user.setRoleId(rs.getString("roleId"));
            user.setRoleName(rs.getString("roleName"));

            return user;
        }
    }

    /**
     * 删除用户原来绑定的角色
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteOldRole(String userId) {
        String sql = "DELETE FROM shiro_userrole WHERE userId = ?";
        Object[] args = {
                userId
        };

        try {
            return jdbcTemplate.update(sql, args) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 为用户新添加角色
     * @param userId
     * @param roles
     * @return
     */
    @Override
    public Boolean insertNewRoles(final String userId, final String[] roles) {
        int[] result = jdbcTemplate.batchUpdate("INSERT INTO shiro_userrole (userId, roleId) VALUES (?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, roles[i]);
            }

            @Override
            public int getBatchSize() {
                return roles.length;
            }
        });

        return result.length == roles.length;
    }
}