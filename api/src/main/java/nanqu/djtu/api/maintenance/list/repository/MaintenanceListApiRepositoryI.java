package nanqu.djtu.api.maintenance.list.repository;

public interface MaintenanceListApiRepositoryI {
    /**
     * 通过微信添加新的保修单
     *
     * @param list 保修单信息
     * @return 如果添加成功返回true, else false
     */
    boolean insertNew(MaintenanceList list);
}
