package nanqu.djtu.api.maintenance.list.rest;

import nanqu.djtu.api.maintenance.list.service.MaintenanceListApiServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/maintenance/list")
public class RestMaintenanceListController {
    @Autowired
    private MaintenanceListApiServiceI maintenanceListApiService;

    @RequestMapping("/add")
    public boolean addNewMaintenanceList(MaintenanceList list) {
        boolean add = maintenanceListApiService.addNewMaintenanceList(list);

        return true;
    }
}
