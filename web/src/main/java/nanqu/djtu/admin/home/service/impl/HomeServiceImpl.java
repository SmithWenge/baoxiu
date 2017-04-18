package nanqu.djtu.admin.home.service.impl;

import nanqu.djtu.admin.home.repository.HomeRepositoryI;
import nanqu.djtu.admin.home.service.HomeServiceI;
import nanqu.djtu.admin.maintenance.list.manage.service.impl.MaintenanceListServiceImpl;
import nanqu.djtu.pojo.MaintenanceList;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeServiceImpl implements HomeServiceI {

    @Autowired
    private HomeRepositoryI HomeRepository;
    @Override
    public Page<MaintenanceList> query4Page(MaintenanceList list, Pageable pageable) {

        return HomeRepository.select4Page(list,pageable);
    }
}
