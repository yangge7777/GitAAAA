package com.yang.order.service;

import com.yang.cart.bean.CartListBean;
import com.yang.order.bean.OrderBean;
import com.yang.order.bean.OrderItem;
import com.yang.order.bean.OrderItemPlus;
import com.yang.order.bean.OrderShow;

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
public interface OrderService {
    //向orders表中添加信息
    void addOrders(OrderBean orderBean);
    //向orderitem表中添加信息
    void addOrderItem(List<OrderItem> orderItemList);
    //初始化生成一个Orderbean
    OrderBean createaOrderBean(String sumprice,String uid ,String address);
    //通过购物车生成一个OrderItemList
    List<OrderItem> cartToOrderItem(List<CartListBean> cartListBeanList,String oid);
    //uid查询orders表中信息
    List<OrderBean> queryOrdersByuid(String uid);
    //查询orders表中信息
    List<OrderBean> queryOrders();
    //oid查询orderItem表中信息
    List<OrderItem> queryOrderItemByoid(String oid);
    //将购物车信息添加到itemplus
    List<OrderItemPlus> cartToPlus(List<CartListBean> cartListBeanList);

    //拿到OrderShowList
    List<OrderShow> orderShow (List<OrderBean> orderBeanList);

    //通过uid oid 更改orders表中的address state
    boolean updateOrdersByuidoid(String uid, String oid ,String address,String state);

    //通过oid查出对应的OrderBean
    OrderBean queryOrderBeanByoid(String oid );
    //将orders表指定Oid state状态改为2
    boolean updateOrdersState2(String oid);
//3
    boolean updateOrdersState3(String oid);
    //state查询 orderbeanList
    List<OrderBean> queryOrderBeanByState (String state);


}
