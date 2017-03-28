package nanqu.djtu.log.repository.impl;

import com.google.common.base.Optional;
import nanqu.djtu.log.repository.ILogRepository;
import nanqu.djtu.pojo.LogContent;
import nanqu.djtu.util.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepositoryImpl implements ILogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils<LogContent> repositoryUtils;

    @Override
    public void insertLog(LogContent logContent) {
        String sql = "INSERT INTO lgb_log (logAction, logLevel, logContent, logUser) VALUES (?, ?, ?, ?)";
        Object[] args = {
                logContent.getLogAction(),
                logContent.getLogLevel(),
                logContent.getLogContent(),
                logContent.getLogUser()
        };

        jdbcTemplate.update(sql, args);
    }

    @Override
    public Page<LogContent> select4Page(LogContent logContent, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT logId, logAction, logLevel, logContent, logUser, logTime FROM lgb_log WHERE deleteFlag = 0");
        List<Object> list = new ArrayList<Object>();

        Optional<LogContent> optional = Optional.fromNullable(logContent);
        if (optional.isPresent()) {
            if (logContent.getLogUser() != null && logContent.getLogUser().length() > 0) {
                sql.append(" AND logUser = ?");
                list.add(logContent.getLogUser());
            }

            if (logContent.getLogLevel() > 0) {
                sql.append(" AND logLevel = ?");
                list.add(logContent.getLogLevel());
            }

            if (logContent.getLogAction() > 0) {
                sql.append(" AND logAction = ?");
                list.add(logContent.getLogAction());
            }

            if (logContent.getQueryStartTime() != null) {
                sql.append(" AND logTime > ?");
                list.add(logContent.getQueryStartTime());
            }

            if (logContent.getQueryEndTime() != null) {
                sql.append(" AND logTIme < ?");
                list.add(logContent.getQueryEndTime());
            }
        }

        Object[] args = list.toArray();

        sql.append(" ORDER BY logTime DESC");


        return repositoryUtils.select4Page(sql.toString(), pageable, args, new Select4PageRowMapper());
    }

    private class Select4PageRowMapper implements RowMapper<LogContent> {

        @Override
        public LogContent mapRow(ResultSet resultSet, int i) throws SQLException {
            LogContent logContent = new LogContent();

            logContent.setLogId(resultSet.getInt("logId"));
            logContent.setLogAction(resultSet.getInt("logAction"));
            logContent.setLogLevel(resultSet.getInt("logLevel"));
            logContent.setLogContent(resultSet.getString("logContent"));
            logContent.setLogUser(resultSet.getString("logUser"));
            logContent.setLogTime(resultSet.getTimestamp("logTime"));

            return logContent;
        }
    }
}
