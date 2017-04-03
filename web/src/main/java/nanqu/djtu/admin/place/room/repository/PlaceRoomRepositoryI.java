package nanqu.djtu.admin.place.room.repository;

import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.PlaceRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaceRoomRepositoryI {
    /**
     * 查询位置信息列表
     *
     * @return 所有未删除位置的信息
     */
    Page<PlaceRoom> query4Page(PlaceRoom room,Pageable pageable);

    /**
     * 添加新的位置
     *
     * @param room 新位置的信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNewPlaceRoom(PlaceRoom room);

    /**
     * 删除位置
     *
     * @param roomId 位置Id
     * @return 删除成功返回true, else false
     */
    boolean deletePlaceRoom(String roomId);

    /**
     * 查询需要编辑的位置的信息
     *
     * @param roomId 位置Id
     * @return 位置信息对象
     */
    PlaceRoom select4Edit(String roomId);

    /**
     * 更新位置信息
     *
     * @param room 新的更改后的位置信息
     * @return 更改成功返回true, else false
     */
    boolean updatePlaceRoom(PlaceRoom room);

    /**
     * 验证位置编号的唯一
     *
     * @param roomNumber 位置编号
     * @return 唯一返回true, else false
     */
    boolean select4PlaceRoomNumberUnique(String roomNumber);

    /**
     * 查询地点
     * @return
     */
    List<PlaceRoom> selectBuildings();

    /**
     * 查询校区
     * @return
     */
    List<PlaceDistinct> selectDistincts();
}
