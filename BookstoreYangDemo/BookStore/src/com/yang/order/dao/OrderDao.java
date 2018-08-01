package com.yang.order.dao;

import com.yang.book.bean.BookBean;
import com.yang.order.bean.OrderBean;
import com.yang.order.bean.OrderItem;

import java.util.List;

/**
 * Created by dllo on 18/6/22.
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * My Dear Taoism's Friend .Please SitDown.
 */
public interface OrderDao {
    //添加信息到orders表中
    void addtoOrders(OrderBean orderBean);
    //添加信息到orderItem表中
    void addtoOrderItem(List<OrderItem> orderItemList);
    //uid查询orders表中信息
    List<OrderBean> queryOrdersByuid(String uid );
    //查询orders表中信息
    List<OrderBean> queryOrders();
    //oid查询orderItem表中信息
 List< OrderItem>   queryOrderItemByoid(String oid );

    BookBean queryBookByBid(String  bid);
    //通过uid oid更改orders表中数据
    boolean updateOrdersuidoid(String uid,String  oid ,String address,String state);

    //通过Oid查出返回一个对应orderBean
    OrderBean queryOrderBeanByoid(String oid);
    //指定oid订单更改orders state为2
    void updateOrdersState2(String oid);
//3
    void updateOrdersState3(String oid);
    //state查询 orderbeanList
    List<OrderBean> queryOrderBeanByState (String state);


}
