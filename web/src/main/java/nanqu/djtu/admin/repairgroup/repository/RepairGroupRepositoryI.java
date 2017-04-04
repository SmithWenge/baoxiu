package nanqu.djtu.admin.repairgroup.repository;

import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.RepairGroup;

import java.util.List;

public interface RepairGroupRepositoryI {
    /**
     * 查询维修小组列表
     *
     * @return 所有未删除报修小组的信息
     */
    List<RepairGroup> select4List();

    /**
     * 添加新的维修小组
     *
     * @param group 新维修小组的信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNewRepairGroup(RepairGroup group);
    /**
     * 删除维修小组
     *
     * @param repairGroupId 校区Id
     * @return 删除成功返回true, else false
     */

    boolean deleteRepairGroup(String repairGroupId);
    /**
     * 查询需要编辑的维修小组的信息
     *
     * @param repairGroupId 维修小组Id
     * @return 维修小组信息对象
     */
    RepairGroup select4Edit(String repairGroupId);

    /**
     * 更新校区信息
     *
     * @param group 新的更改后的校区信息
     * @return 更改成功返回true, else false
     */
    boolean updateRepairGroup(RepairGroup group);

    /**
     * 验证维修小组编号的唯一
     *
     * @param groupNumber 维修小组编号
     * @return 唯一返回true, else false
     */
    boolean select4RepairGroupNumberUnique(String groupNumber);
}
