package nanqu.djtu.admin.home.repository;

import nanqu.djtu.pojo.MaintenanceList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HomeRepositoryI {
    /**
     * 分页查询维修单信息列表
     * @param list
     * @param pageable
     * @return
     */
    Page<MaintenanceList> select4Page(MaintenanceList list, Pageable pageable);

}
