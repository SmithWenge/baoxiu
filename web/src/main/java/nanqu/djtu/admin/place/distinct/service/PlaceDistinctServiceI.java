package nanqu.djtu.admin.place.distinct.service;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

public interface PlaceDistinctServiceI {
    List<PlaceDistinct> query4List();
    boolean saveNewPlaceDistinct(PlaceDistinct distinct, AdminUser user);
    boolean deleteDistinct(String distinctId, AdminUser user);
    PlaceDistinct query4Edit(String distinctId);
    boolean updatePlaceDistinct(PlaceDistinct distinct, AdminUser user);
    boolean query4PlaceDistinctNumberUnique(String distinctNumber);
}
