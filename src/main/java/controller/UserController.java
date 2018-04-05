package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yupi Li
 * @Date: Created in 22:48 2018/4/2
 * @Description:
 * @Modified By:
 */

@Controller
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public Map<String, Object> userLogin(int userId, String userPassword) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                User user1 = userService.getUserByIdAndPassword(userId, userPassword);
                if (user1 != null) {
                    if (user1.getUserStatus() != 0) {
                        map.put("code", 0);//成功
                    } else {
                        map.put("code", 4);//未通过审核
                    }
                } else {
                    map.put("code", 1);//密码错误
                }
            } else {
                map.put("code", 2);//未注册
            }
        } catch (Exception e) {
            map.put("code", 3);//异常
        }
        return map;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public Map<String, Object> userRegister(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (userService.getUserById(user.getUserId()) == null) {
                user.setUserStatus(0);//初始状态值
                user.setUserRegisterDate(new Date());//注册日期
                userService.insertUser(user);
                map.put("code", 0);//成功
            } else {
                map.put("code", 2);//已注册
            }
        } catch (Exception e) {
            map.put("code", 3);//异常
        }
        return map;
    }

    @RequestMapping("/userGetRegisterInfoByPage")
    @ResponseBody
    public Map<String, Object> userGetRegisterInfoByPage(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = userService.getUserRegisterCount();
            map.put("count", count);
            if (count != 0) {
                List<User> userList = userService.getUserRegisterInfoByPage(page, limit);
                if (userList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", userList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无注册申请");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无注册申请");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/userDelete")
    @ResponseBody
    public Map<String, Object> userDelete(int userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.deleteUser(userId);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/userUpdate")
    @ResponseBody
    public Map<String, Object> userUpdate(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.updateUser(user);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }
}
