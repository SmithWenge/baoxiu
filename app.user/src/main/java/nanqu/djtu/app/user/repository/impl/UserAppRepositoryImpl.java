package nanqu.djtu.app.user.repository.impl;

import nanqu.djtu.app.user.repository.UserAppRepositoryI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAppRepositoryImpl implements UserAppRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(UserAppRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
