package service;

import entity.User;
import entity.UserExample;
import mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Yupi Li
 * @Date: Created in 22:49 2018/4/2
 * @Description:
 * @Modified By:
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByIdAndPassword(int id, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(id).andUserPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public long getUserRegisterCount() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserStatusEqualTo(0);
        return userMapper.countByExample(userExample);
    }

    @Override
    public List<User> getUserRegisterInfoByPage(int page, int limit) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserStatusEqualTo(0);
        userExample.setOrderByClause("userRegisterDate desc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return userMapper.selectByExampleWithRowbounds(userExample, rowBounds);
    }
}
