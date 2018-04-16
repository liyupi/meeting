package service;

import entity.OrderRoom;

import java.util.List;

public interface OrderRoomService {
    void insertOrderRoom(OrderRoom orderRoom);

    void deleteOrderRoom(int id);

    void updateOrderRoom(OrderRoom orderRoom);

    long getOrderRoomCount(int orderStatus);

    long getOrderRoomCountAll();

    List<OrderRoom> getOrderRoomByPage(int page, int limit, int orderStatus);

    List<OrderRoom> getOrderRoomByPageAll(int page, int limit);
}
