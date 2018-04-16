package service;

import entity.User;
import entity.UserExample;
import entity.UserExample.Criteria;

import java.util.List;

import mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public void deleteUser(int id) {
        userMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

    public User getUserByIdAndPassword(int id, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(Integer.valueOf(id)).andUserPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public long getUserRegisterCount(int userStatus) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserStatusEqualTo(userStatus);
        return userMapper.countByExample(userExample);
    }

    public long getUserRegisterCountAll() {
        UserExample userExample = new UserExample();
        return userMapper.countByExample(userExample);
    }

    public List<User> getUserRegisterInfoByPage(int page, int limit, int userStatus) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserStatusEqualTo(userStatus);
        userExample.setOrderByClause("userRegisterDate asc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return userMapper.selectByExampleWithRowbounds(userExample, rowBounds);
    }

    public List<User> getUserRegisterInfoByPageAll(int page, int limit) {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("userRegisterDate asc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return userMapper.selectByExampleWithRowbounds(userExample, rowBounds);
    }
}
