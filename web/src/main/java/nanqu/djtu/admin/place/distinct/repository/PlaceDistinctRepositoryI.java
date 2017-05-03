package nanqu.djtu.admin.place.distinct.repository;

import nanqu.djtu.pojo.PlaceDistinct;

import java.util.List;

public interface PlaceDistinctRepositoryI {
    /**
     * 查询校区信息列表
     *
     * @return 所有未删除校区的信息
     */
    List<PlaceDistinct> select4List();

    /**
     * 添加新的校区
     *
     * @param distinct 新校区的信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNewPlaceDistinct(PlaceDistinct distinct);

    /**
     * 删除校区
     *
     * @param distinctId 校区Id
     * @return 删除成功返回true, else false
     */
    boolean deletePlaceDistinct(String distinctId);

    /**
     * 查询需要编辑的校区的信息
     *
     * @param distinctId 校区Id
     * @return 校区信息对象
     */
    PlaceDistinct select4Edit(String distinctId);

    /**
     * 更新校区信息
     *
     * @param distinct 新的更改后的校区信息
     * @return 更改成功返回true, else false
     */
    boolean updatePlaceDistinct(PlaceDistinct distinct);

    /**
     * 验证校区编号的唯一
     *
     * @param distinctNumber 校区编号
     * @return 唯一返回true, else false
     */
    boolean select4PlaceDistinctNumberUnique(String distinctNumber);

    /**
     * 查询校区的总数,编号是总数加一
     *
     * @return 校区的总数
     */
    int select4DistinctCount();
}
