package nanqu.djtu.app.worker.repository.impl;

import nanqu.djtu.app.worker.repository.WorkerRepositoryI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerRepositoryImpl implements WorkerRepositoryI {
    private static final Logger LOG = LoggerFactory.getLogger(WorkerRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
}
