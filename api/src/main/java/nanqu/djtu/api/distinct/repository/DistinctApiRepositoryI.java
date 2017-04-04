package nanqu.djtu.api.distinct.repository;

import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

public interface DistinctApiRepositoryI {
    /**
     * 查询所有的校区对象
     *
     * @return 校区对象List或者size 0 list
     */
    List<PlaceDistinct> selectAllDistincts();
}
