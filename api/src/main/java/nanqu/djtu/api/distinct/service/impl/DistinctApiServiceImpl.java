package nanqu.djtu.api.distinct.service.impl;

import nanqu.djtu.api.distinct.repository.DistinctApiRepositoryI;
import nanqu.djtu.api.distinct.service.DistinctApiServiceI;
import nanqu.djtu.pojo.PlaceDistinct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistinctApiServiceImpl implements DistinctApiServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(DistinctApiServiceImpl.class);

    @Autowired
    private DistinctApiRepositoryI distinctRepository;

    @Override
    public List<PlaceDistinct> queryAllDistincts() {
        return distinctRepository.selectAllDistincts();
    }
}
