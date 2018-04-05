package service;

import entity.OrderRoom;
import entity.OrderRoomExample;
import mapper.OrderRoomMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yupi Li
 * @Date: Created in 15:03 2018/4/4
 * @Description:
 * @Modified By:
 */
@Service
public class OrderRoomServiceImpl implements OrderRoomService {
    @Autowired
    OrderRoomMapper OrderRoomMapper;

    @Override
    public void insertOrderRoom(OrderRoom orderRoom) {
        OrderRoomMapper.insertSelective(orderRoom);
    }

    @Override
    public void deleteOrderRoom(int id) {
        OrderRoomMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateOrderRoom(OrderRoom orderRoom) {
        OrderRoomMapper.updateByPrimaryKeySelective(orderRoom);
    }

    @Override
    public long getOrderRoomCount(int orderStatus) {
        OrderRoomExample OrderRoomExample = new OrderRoomExample();
        OrderRoomExample.createCriteria().andOrderStatusEqualTo(orderStatus);
        return OrderRoomMapper.countByExample(OrderRoomExample);
    }

    @Override
    public long getOrderRoomCountAll() {
        OrderRoomExample OrderRoomExample = new OrderRoomExample();
        return OrderRoomMapper.countByExample(OrderRoomExample);
    }

    @Override
    public List<OrderRoom> getOrderRoomByPage(int page, int limit, int orderStatus) {
        OrderRoomExample OrderRoomExample = new OrderRoomExample();
        OrderRoomExample.createCriteria().andOrderStatusEqualTo(orderStatus);
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return OrderRoomMapper.selectByExampleWithRowbounds(OrderRoomExample, rowBounds);
    }

    @Override
    public List<OrderRoom> getOrderRoomByPageAll(int page, int limit) {
        OrderRoomExample OrderRoomExample = new OrderRoomExample();
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return OrderRoomMapper.selectByExampleWithRowbounds(OrderRoomExample, rowBounds);
    }
}
