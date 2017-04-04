package nanqu.djtu.api.building.service;

import nanqu.djtu.pojo.PlaceBuilding;

import java.util.List;

public interface BuildingApiServiceI {
    List<PlaceBuilding> queryDistinctBuilding(String distinctId);
}
