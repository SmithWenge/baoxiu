package nanqu.djtu.admin.place.room.controller;

import com.google.common.base.Optional;
import nanqu.djtu.admin.place.room.service.PlaceRoomServiceI;
import nanqu.djtu.pojo.*;
import nanqu.djtu.utils.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenge
 *
 */
@Controller
@RequestMapping("/admin/place/room")
public class PlaceRoomController {

    @Autowired
    private PlaceRoomServiceI placeRoomService;

    /**
     * 位置首页
     *
     * @return 位置首页地址
     */
    @RequestMapping("/index")
    public String index() {
        return "admin/place/room/list";
    }

    /**
     * 查询位置信息列表
     *
     * @return 位置信息列表的页面地址和数据
     */
    @ResponseBody
    @RequestMapping("/route/page")
    public Map<String, Page<PlaceRoom>> listFirstPage(PlaceRoom room,@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY);

        Map<String, Page<PlaceRoom>> mapData = new HashMap<>();

        Page<PlaceRoom> page = placeRoomService.query4Page(room, pageable);

        mapData.put(ConstantFields.PAGE_KEY, page);

        return mapData;
    }

    /**
     * 查询位置信息列表(分页)
     *
     * @return 位置信息列表的页面地址和数据
     */
    @ResponseBody
    @RequestMapping("/page")
    public Map<String, Page<PlaceRoom>> listPage(PlaceRoom room,@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        PlaceRoom searchObj = (PlaceRoom)session.getAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY);

        Map<String, Page<PlaceRoom>> mapData = new HashMap<>();

        Optional<PlaceRoom> optional = Optional.fromNullable(searchObj);
        if (optional.isPresent()) {
            room = searchObj;
        }

        Page<PlaceRoom> page = placeRoomService.query4Page(room, pageable);

        mapData.put(ConstantFields.PAGE_KEY, page);

        return mapData;
    }

    /**
     * 条件查询位置信息列表
     *
     * @return 位置信息列表的页面地址和数据
     */
    @ResponseBody
    @RequestMapping("/pageSearch")
    public Map<String, Page<PlaceRoom>> searchPage(PlaceRoom room,@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable, HttpSession session) {
        PlaceRoom searchObj = (PlaceRoom)session.getAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY);

        Map<String, Page<PlaceRoom>> mapData = new HashMap<>();

        Optional<PlaceRoom> optional = Optional.fromNullable(searchObj);
        if (optional.isPresent()) {
            session.setAttribute(ConstantFields.SESSION_ROOM_SEARCH_KEY, room);
        }

        Page<PlaceRoom> page = placeRoomService.query4Page(room, pageable);

        mapData.put(ConstantFields.PAGE_KEY, page);

        return mapData;
    }

    /**
     * 路由到位置添加页面
     *ModelAndView mav = new ModelAndView();
     * @return 位置添加页面地址
     */
    @RequestMapping("/add/route")
    public ModelAndView routeAdd() {
        ModelAndView mav = new ModelAndView("admin/place/room/add");

        List<EquipmentSet> sets = placeRoomService.querySets();
        List<PlaceDistinct> distincts = placeRoomService.queryDistincts();
        mav.addObject("distincts", distincts);
        mav.addObject("sets", sets);

        return mav;
    }

    /**
     * 保存添加新位置
     *
     * @param room 新位置信息
     * @param redirectAttributes 添加操作提示信息
     * @return 添加成功返回list页面,else add页面
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    public String save(PlaceRoom room, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean save = placeRoomService.saveNewPlaceRoom(room, user);

        if (save) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/room/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/room/add/route.action";
        }
    }

    /**
     * 删除位置
     *
     * @param roomId 位置Id
     * @param redirectAttributes 删除操作提示信息
     * @return 返回到list页面
     */
    @RequestMapping("/delete/{roomId}")
    public String delete(@PathVariable String roomId, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean delete = placeRoomService.deleteRoom(roomId, user);

        if (delete) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/room/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/room/index.action";
        }
    }

    /**
     * 路由到位置编辑页面
     *
     * @param roomId 位置Id
     * @return 位置编辑页面和相位置信息
     */
    @RequestMapping("/edit/route/{roomId}")
    public ModelAndView routeEdit(@PathVariable String roomId) {
        PlaceRoom room = placeRoomService.query4Edit(roomId);

        if (Optional.fromNullable(room).isPresent()) {
            ModelAndView mav = new ModelAndView("admin/place/room/edit");

            List<PlaceBuilding> buildings = placeRoomService.queryBuildings4Edit();
            List<EquipmentSet> sets = placeRoomService.querySets();
            mav.addObject("room", room);
            mav.addObject("buildings", buildings);
            mav.addObject("sets", sets);

            return mav;
        } else {
            return new ModelAndView("redirect:/admin/place/room/list.action");
        }
    }

    /**
     * 保存位置信息修改
     *
     * @param room 位置信息
     * @param redirectAttributes 修改操作提示信息
     * @return 保存成功返回list, else edit page
     */
    @RequestMapping(value = "/edit/do", method = RequestMethod.POST)
    public String saveEditPlaceRoom(PlaceRoom room, RedirectAttributes redirectAttributes, HttpSession session) {
        AdminUser user = (AdminUser) session.getAttribute(ConstantFields.SESSION_LOGIN_KEY);

        boolean update = placeRoomService.updatePlaceRoom(room, user);

        if (update) {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.SUCCESS_MESSAGE);

            return "redirect:/admin/place/room/index.action";
        } else {
            redirectAttributes.addFlashAttribute(ConstantFields.OPERATION_MESSAGE_KEY, ConstantFields.FAILURE_MESSAGE);

            return "redirect:/admin/place/room/edit/route/" + room.getRoomId() + ".action";
        }
    }

    /**
     * 验证位置编号的唯一
     *
     * @param room 位置编号的位置对象
     * @return 唯一返回true, else false
     */
    @ResponseBody
    @RequestMapping(value = "/unique/roomNumber", method = RequestMethod.POST)
    public boolean roomNumberUnique(PlaceRoom room) {
        String roomNumber = room.getRoomNumber();
        String hiddenRoomNumber = room.getHiddenRoomNumber();

        return  roomNumber.equalsIgnoreCase(hiddenRoomNumber) ||
                placeRoomService.query4PlaceRoomNumberUnique(room.getRoomNumber());
    }

    /**
     * 二级联动根据校区查询地点
     * @param placeDistinct
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buildings", method = RequestMethod.POST)
    public Map<String, List<PlaceBuilding>> buildings(PlaceDistinct placeDistinct) {
        Map<String, List<PlaceBuilding>> map = new HashMap<>();
        map.put("buildings", placeRoomService.queryBuildingsByDistinctId(placeDistinct.getDistinctId()));

        return map;
    }
}
