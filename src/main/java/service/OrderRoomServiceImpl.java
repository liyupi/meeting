package service;

import entity.OrderRoom;
import entity.OrderRoomExample;

import java.util.List;

import mapper.OrderRoomMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderRoomServiceImpl
        implements OrderRoomService {
    @Autowired
    OrderRoomMapper orderRoomMapper;

    public void insertOrderRoom(OrderRoom orderRoom) {
        orderRoomMapper.insertSelective(orderRoom);
    }

    public void deleteOrderRoom(int id) {
        orderRoomMapper.deleteByPrimaryKey(id);
    }

    public void updateOrderRoom(OrderRoom orderRoom) {
        orderRoomMapper.updateByPrimaryKeySelective(orderRoom);
    }

    public long getOrderRoomCount(int orderStatus) {
        OrderRoomExample orderRoomExample = new OrderRoomExample();
        orderRoomExample.createCriteria().andOrderStatusEqualTo(orderStatus);
        return orderRoomMapper.countByExample(orderRoomExample);
    }

    public long getOrderRoomCountAll() {
        OrderRoomExample orderRoomExample = new OrderRoomExample();
        return orderRoomMapper.countByExample(orderRoomExample);
    }

    public List<OrderRoom> getOrderRoomByPage(int page, int limit, int orderStatus) {
        OrderRoomExample orderRoomExample = new OrderRoomExample();
        orderRoomExample.setOrderByClause("orderDate asc");
        orderRoomExample.createCriteria().andOrderStatusEqualTo(orderStatus);
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return orderRoomMapper.selectByExampleWithRowbounds(orderRoomExample, rowBounds);
    }

    public List<OrderRoom> getOrderRoomByPageAll(int page, int limit) {
        OrderRoomExample orderRoomExample = new OrderRoomExample();
        orderRoomExample.setOrderByClause("orderDate asc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return orderRoomMapper.selectByExampleWithRowbounds(orderRoomExample, rowBounds);
    }
}