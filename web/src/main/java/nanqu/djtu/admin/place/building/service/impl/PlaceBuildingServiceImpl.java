package nanqu.djtu.admin.place.building.service.impl;


import nanqu.djtu.admin.place.building.repository.PlaceBuildingRepositoryI;
import nanqu.djtu.admin.place.building.service.PlaceBuildingServiceI;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceBuildingServiceImpl implements PlaceBuildingServiceI {
    @Autowired
    private PlaceBuildingRepositoryI placeBuildingRepository;

    @Override
    public List<PlaceBuilding> query4List() {
        return placeBuildingRepository.select4List();
    }

    @Override
    public List<PlaceDistinct> placeDistinctQuery4List() {
        return placeBuildingRepository.placeDistinctSelect4List();
    }


}
