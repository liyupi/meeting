package controller;

import entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import service.UserService;


@Controller
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    @RequestMapping("/userLogin")
    @ResponseBody
    public Map<String, Object> userLogin(int userId, String userPassword) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                User user1 = userService.getUserByIdAndPassword(userId, userPassword);
                if (user1 != null) {
                    if (user1.getUserStatus() == 1 || user1.getUserStatus() == 3) {
                        Map<String, String> jedisMap = new HashMap<>();
                        String token = UUID.randomUUID().toString().replace("-", "");
                        jedisMap.put("userId", String.valueOf(user1.getUserId()));
                        jedis.hmset(token, jedisMap);
                        jedis.expire(token, 5400);
                        map.put("code", 0);
                        map.put("token", token);
                        if (user1.getUserStatus() == 3) {
                            map.put("admin", true);
                        }else{
                            map.put("userType",user1.getUserType());
                        }
                    } else {
                        map.put("code", 4);
                    }
                } else {
                    map.put("code", 1);
                }
            } else {
                map.put("code", 2);
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/userLogout")
    @ResponseBody
    public Map<String, Object> userLogout(int userId, String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (jedis.exists(token)) {
                if (jedis.hmget(token, "userId").get(0).equals(String.valueOf(userId))) {
                    jedis.del(token);
                    map.put("code", 0);
                } else {
                    map.put("code", 2);
                }
            } else {
                map.put("code", 2);
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public Map<String, Object> userRegister(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (userService.getUserById(user.getUserId()) == null) {
                user.setUserStatus(0);
                user.setUserRegisterDate(new Date());
                userService.insertUser(user);
                map.put("code", 0);
            } else {
                map.put("code", 2);
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/userGetRegisterInfoByPage")
    @ResponseBody
    public Map<String, Object> userGetRegisterInfoByPage(int page, int limit, int userStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = userService.getUserRegisterCount(userStatus);
            map.put("count", count);
            if (count != 0) {
                List<User> userList = userService.getUserRegisterInfoByPage(page, limit, userStatus);
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
            this.userService.updateUser(user);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }
}
