package nanqu.djtu.api.building.repository;

import nanqu.djtu.pojo.PlaceBuilding;

import java.util.List;

public interface BuildingApiRepositoryI {
    /**
     * 根据校区Id
     *
     * @param distinctId 校区Id
     * @return 校区下的地点
     */
    List<PlaceBuilding> selectDistinctBuilding(String distinctId);
}
