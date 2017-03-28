package nanqu.djtu.dictionary.feature.repository.impl;

import nanqu.djtu.dictionary.feature.Dictionary;
import nanqu.djtu.dictionary.feature.repository.IDictionaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DictionaryRepositoryImpl implements IDictionaryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(DictionaryRepositoryImpl.class);

    public Dictionary selectById(String id) {
        String sql = "SELECT id, groupKey, groupKey, itemKey, itemValue, status, sort WHERE id=?";
        Object[] args = { id };

        return jdbcTemplate.queryForObject(sql, args, new DictionaryRowMapper());
    }

    public List<Dictionary> selectAll() {
        String sql = "SELECT id, groupKey, groupValue, itemKey, itemValue, status, sort FROM lgb_dictionary";
        Object[] args = {};

        //TODO Don't using query replace with other api or other JdbcTemplate
        return jdbcTemplate.query(sql, args, new DictionaryRowMapper());
    }

    private class DictionaryRowMapper implements RowMapper<Dictionary> {

        @Override
        public Dictionary mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dictionary dictionary = new Dictionary();

            dictionary.setId(rs.getInt("id"));
            dictionary.setGroupKey(rs.getInt("groupKey"));
            dictionary.setItemKey(rs.getInt("itemKey"));
            dictionary.setGroupValue(rs.getString("groupValue"));
            dictionary.setItemValue(rs.getString("itemValue"));
            dictionary.setStatus(rs.getInt("status"));
            dictionary.setSort(rs.getInt("sort"));

            return dictionary;
        }
    }
}
