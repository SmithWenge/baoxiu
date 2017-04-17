package nanqu.djtu.admin.home.service;

import nanqu.djtu.pojo.MaintenanceList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HomeServiceI {
    Page<MaintenanceList> query4Page(MaintenanceList list, Pageable pageable);
}
