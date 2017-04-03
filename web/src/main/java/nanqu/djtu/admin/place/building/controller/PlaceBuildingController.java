package nanqu.djtu.admin.place.building.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.place.building.service.PlaceBuildingServiceI;

import nanqu.djtu.pojo.AdminUser;
import nanqu.djtu.pojo.EquipmentSet;
import nanqu.djtu.pojo.PlaceBuilding;
import nanqu.djtu.pojo.PlaceDistinct;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zhangwenyue
 */
@Controller
@RequestMapping("/admin/place/building")
public class PlaceBuildingController {
    @Autowired
    private PlaceBuildingServiceI placeBuildingService;

    /**
     *查询地点信息
     *
     * @return地点信息的列表和数据
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<PlaceBuilding> building = placeBuildingService.query4List();

        ModelAndView mav = new ModelAndView("admin/place/building/list");

        mav.addObject("building",building);

        return  mav;
    }

    /**
     * 路由到地点添加页面
     *
     * @return 地点添加页面地址
     */
    @RequestMapping("/add/route")
    public ModelAndView routeAdd() {
        List<PlaceDistinct>  placeDistincts = placeBuildingService.placeDistinctQuery4List();
        List<EquipmentSet>  equipmentSets = placeBuildingService.equipmentSetQuery4List();

        ModelAndView modelAndView = new ModelAndView("admin/place/building/add");

        modelAndView.addObject("placeDistincts",placeDistincts);
        modelAndView.addObject("equipmentSets",equipmentSets);

        return modelAndView;
    }

    /**
     * 保存添加新地点
     *
     * @param building 新地点信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(PlaceBuilding building, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);
        String buildingId = building.getBuildingId();
        String setId = building.getSetId();

        boolean save = placeBuildingService.saveNewPlaceDistinct(building, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/building/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/building/add/route.action";
        }
    }

    /**
     * 删除校区
     *
     * @param buildingId 地点Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{buildingId}")
    public String delete(@PathVariable String buildingId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = placeBuildingService.deleteDistinct(buildingId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/building/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/building/list.action";
        }
    }

    /**
     * 验证地点编号的唯一
     *
     * @param building 地点编号的地点对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/buildingNumber", method = RequestMethod.POST)
    public boolean buildingNumberUnique(PlaceBuilding building) {
        String buildingNumber = building.getBuildingNumber();
        String hiddenBuildingNumber = building.getHiddenBuildingNumber();

        return buildingNumber.equalsIgnoreCase(hiddenBuildingNumber) ||
                placeBuildingService.query4PlaceBuildingNumberUnique(building);
    }

    /**
     * 路由到地点编辑页面
     *
     * @param buildingId 地点Id
     * @return 地点编辑页面和相对地点信息
     */
    @RequestMapping("/edit/route/{buildingId}")
    public ModelAndView routeEdit(@PathVariable String buildingId) {
        PlaceBuilding building = placeBuildingService.query4Edit(buildingId);
        List<PlaceDistinct>  placeDistincts = placeBuildingService.placeDistinctQuery4List();
        List<EquipmentSet>  equipmentSets = placeBuildingService.equipmentSetQuery4List();

        if (Optional.fromNullable(building).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/place/building/edit");

            mav.addObject("building", building);
            mav.addObject("placeDistincts",placeDistincts);
            mav.addObject("equipmentSets",equipmentSets);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/place/building/list.action");
        }
    }
    /**
     * 保存地点信息修改
     *
     * @param placeBuilding 地点信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditPlaceDistinct(PlaceBuilding placeBuilding, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = placeBuildingService.updatePlaceBuilding(placeBuilding, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/building/list.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/building/edit/route/" + placeBuilding.getBuildingId() + ".action";
        }
    }





}
