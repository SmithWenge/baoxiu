package nanqu.djtu.api.building.service.impl;

import nanqu.djtu.api.building.repository.BuildingApiRepositoryI;
import nanqu.djtu.api.building.service.BuildingApiServiceI;
import nanqu.djtu.pojo.PlaceBuilding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingApiServiceImpl implements BuildingApiServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(BuildingApiServiceImpl.class);

    @Autowired
    private BuildingApiRepositoryI buildingApiRepository;


    @Override
    public List<PlaceBuilding> queryDistinctBuilding(String distinctId) {
        return buildingApiRepository.selectDistinctBuilding(distinctId);
    }
}
