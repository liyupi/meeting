package controller;

import entity.OrderRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrderRoomService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yupi Li
 * @Date: Created in 14:59 2018/4/4
 * @Description:
 * @Modified By:
 */
@Controller
@CrossOrigin
public class OrderRoomController {
    @Autowired
    OrderRoomService orderRoomService;

    @RequestMapping("/addOrderRoom")
    @ResponseBody
    public Map<String, Object> insertOrderRoom(OrderRoom orderRoom) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderRoom.setOrderStatus(0);
            orderRoom.setOrderDate(new Date());
            orderRoomService.insertOrderRoom(orderRoom);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getOrderRoomInfoByPage")
    @ResponseBody
    public Map<String, Object> getOrderRoomByPage(int page, int limit, int orderStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = orderRoomService.getOrderRoomCount(orderStatus);
            if (count > 0) {
                List<OrderRoom> orderRoomList = orderRoomService.getOrderRoomByPage(page, limit, orderStatus);
                if (orderRoomList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", orderRoomList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无会议室预约");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无会议室预约");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getOrderRoomInfoByPageAll")
    @ResponseBody
    public Map<String, Object> getOrderRoomByPageAll(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = orderRoomService.getOrderRoomCountAll();
            if (count > 0) {
                List<OrderRoom> orderRoomList = orderRoomService.getOrderRoomByPageAll(page, limit);
                if (orderRoomList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", orderRoomList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无会议室预约");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无会议室预约");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/deleteOrderRoom")
    @ResponseBody
    public Map<String, Object> deleteOrderRoom(int applyId) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderRoomService.deleteOrderRoom(applyId);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/updateOrderRoom")
    @ResponseBody
    public Map<String, Object> updateOrderRoom(OrderRoom orderRoom) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderRoomService.updateOrderRoom(orderRoom);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }
}

