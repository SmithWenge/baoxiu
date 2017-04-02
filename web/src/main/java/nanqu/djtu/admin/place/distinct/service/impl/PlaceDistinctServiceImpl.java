package nanqu.djtu.admin.place.distinct.service.impl;

import nanqu.djtu.admin.place.distinct.repository.PlaceDistinctRepositoryI;
import nanqu.djtu.admin.place.distinct.service.PlaceDistinctServiceI;
import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceDistinctServiceImpl implements PlaceDistinctServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(PlaceDistinctServiceImpl.class);

    @Autowired
    private PlaceDistinctRepositoryI placeDistinctRepository;

    @Override
    public List<PlaceDistinct> query4List() {
        return placeDistinctRepository.select4List();
    }

    @Override
    public boolean saveNewPlaceDistinct(PlaceDistinct distinct, AdminUser user) {
        boolean insert = placeDistinctRepository.insertNewPlaceDistinct(distinct);

        if (insert) {
            LOG.info("[PlaceDistinct] add new place distinct success with user {}.", user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] add new place distinct failure with user {}.", user.getAdminName());
        }

        return insert;
    }

    @Override
    public boolean deleteDistinct(String distinctId, AdminUser user) {
        boolean delete = placeDistinctRepository.deletePlaceDistinct(distinctId);

        if (delete) {
            LOG.info("[PlaceDistinct] delete place distinct {} success with user {}.", distinctId, user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] delete place distinct {} failure with user {}.", distinctId, user.getAdminName());
        }

        return delete;
    }

    @Override
    public PlaceDistinct query4Edit(String distinctId) {
        return placeDistinctRepository.select4Edit(distinctId);
    }

    @Override
    public boolean updatePlaceDistinct(PlaceDistinct distinct, AdminUser user) {
        boolean update = placeDistinctRepository.updatePlaceDistinct(distinct);

        if (update) {
            LOG.info("[PlaceDistinct] update place distinct {} success with user {}.", distinct.getDistinctId(), user.getAdminName());
        } else {
            LOG.warn("[PlaceDistinct] update place distinct {} failure with user {}.", distinct.getDistinctId(), user.getAdminName());
        }

        return update;
    }

    @Override
    public boolean query4PlaceDistinctNumberUnique(String distinctNumber) {
        return placeDistinctRepository.select4PlaceDistinctNumberUnique(distinctNumber);
    }
}
