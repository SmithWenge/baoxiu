package nanqu.djtu.admin.worker.type.repository;

import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.pojo.WorkerType;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WorkerTyeRepositoryI {
    /**
     * 查询工种信息列表
     *
     * @return 所有未删除工种的信息
     */
    List<WorkerType> select4List();
    /**
     * 添加新的工种
     *
     * @param type 新工种的信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNewWorkerType(WorkerType type);
    /**
     * 删除工种
     *
     * @param typeId 工种Id
     * @return 删除成功返回true, else false
     */
    boolean deleteWorkerType(String typeId);
    /**
     * 更新工种信息
     *
     * @param type 新的更改后的工种信息
     * @return 更改成功返回true, else false
     */
    boolean updateWorkerType(WorkerType type);
    /**
     * 查询需要编辑的工种的信息
     *
     * @param typeId 工种Id
     * @return 工种信息对象
     */
    WorkerType select4Edit(String typeId);
}
