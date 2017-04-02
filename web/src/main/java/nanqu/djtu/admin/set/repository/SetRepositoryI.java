package nanqu.djtu.admin.set.repository;

import nanqu.djtu.pojo.EquipmentSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SetRepositoryI {
    /**
     * 分页查询设备组
     *
     * @param equipmentSet 设备组条件
     * @param pageable 分页查询条件
     * @return 返回设备组的分页查询数据
     */
    Page<EquipmentSet> select4Page(EquipmentSet equipmentSet, Pageable pageable);

    /**
     * 添加新的设备组
     *
     * @param set 新的设备组的信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNewPlaceDistinct(EquipmentSet set);

    /**
     * 查询需要编辑设备组的对象
     *
     * @param setId 设备组Id
     * @return 设备组的数据对象
     */
    EquipmentSet select4Edit(String setId);

    /**
     * 更新编辑后的设备组
     *
     * @param set 更新后的设备组信息
     * @return 如果更新成功返回true, else false
     */
    boolean updateSet(EquipmentSet set);

    /**
     * 删除设备组
     *
     * @param setId 设备组Id
     * @return 删除成功返回true, else false
     */
    boolean deleteSet(String setId);
}
