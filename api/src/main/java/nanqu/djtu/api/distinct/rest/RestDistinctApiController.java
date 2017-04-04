package nanqu.djtu.api.distinct.rest;

import nanqu.djtu.api.distinct.service.DistinctApiServiceI;
import nanqu.djtu.pojo.PlaceDistinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pingfan
 */

@RestController
@RequestMapping("/api/v1/distinct")
public class RestDistinctApiController {

    @Autowired
    private DistinctApiServiceI distinctService;

    /**
     * 查询所有校区
     *
     * @return 校区List's json
     */
    @RequestMapping("/get/all")
    public Map<String, List<PlaceDistinct>> getAllDistincts() {
        Map<String, List<PlaceDistinct>> mapData = new HashMap<>();

        List<PlaceDistinct> distincts = distinctService.queryAllDistincts();

        mapData.put("distincts", distincts);

        return mapData;
    }
}
