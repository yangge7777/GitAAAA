package com.yang.order.service.Impl;

import com.lanou.commons.CommonUtils;
import com.yang.book.bean.BookBean;
import com.yang.cart.bean.CartListBean;
import com.yang.order.bean.OrderBean;
import com.yang.order.bean.OrderItem;
import com.yang.order.bean.OrderItemPlus;
import com.yang.order.bean.OrderShow;
import com.yang.order.dao.Impl.OrderDaoImpl;
import com.yang.order.service.OrderService;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class OrderServiceImpl implements OrderService {
    OrderDaoImpl orderDao = new OrderDaoImpl();

    @Override
    public void addOrders(OrderBean orderBean) {
        orderDao.addtoOrders(orderBean);
    }

    @Override
    public void addOrderItem(List<OrderItem> orderItemList) {
        orderDao.addtoOrderItem(orderItemList);
    }

    public static void main(String[] args) {

    }

    @Override//初始化生成一个orderBean
    public OrderBean createaOrderBean(String sumprice, String uid, String address) {
        //oid  ordertime  total  state  uid  address
        OrderBean orderBean = new OrderBean();
        String webUrl = "http://www.taobao.com";
        String ordertime = getNetworkTime(webUrl);
        String state = "0";

        orderBean.setOid(CommonUtils.uuid());
        orderBean.setOrdertime(ordertime);
        orderBean.setTotal(sumprice);
        orderBean.setState(state);
        orderBean.setUid(uid);
        orderBean.setAddress(address);

        return orderBean;
    }

    //获取网络时间校准订单生成时间
    public static String getNetworkTime(String webUrl) {
        try {
            URL url = new URL(webUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public List<OrderItem> cartToOrderItem(List<CartListBean> cartListBeanList, String oid) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartListBean cartListBean : cartListBeanList) {
            OrderItem orderItem = new OrderItem();
            // iid,count,subtotal,oid,bid ;
            //单个商品的id 单个商品的数量 价格小计 订单id 书id
            orderItem.setIid(CommonUtils.uuid());
            orderItem.setCount(cartListBean.getCount());
            orderItem.setSubtotal(cartListBean.getSglprice());
            orderItem.setOid(oid);
            orderItem.setBid(cartListBean.getBid());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    @Override
    public List<OrderBean> queryOrdersByuid(String uid) {
        return orderDao.queryOrdersByuid(uid);
    }

    @Override
    public List<OrderBean> queryOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public List<OrderItem> queryOrderItemByoid(String oid) {
        return orderDao.queryOrderItemByoid(oid);
    }

    @Override
    public List<OrderItemPlus> cartToPlus(List<CartListBean> cartListBeanList) {
        List<OrderItemPlus> orderItemPlusList = new ArrayList<>();
        for (CartListBean cartListBean : cartListBeanList) {
            OrderItemPlus orderItemPlus = new OrderItemPlus();
            //    image;bname;author;sglprice;count;price;id;
            orderItemPlus.setImage(cartListBean.getImage());
            orderItemPlus.setBname(cartListBean.getBname());
            orderItemPlus.setAuthor(cartListBean.getAuthor());
            orderItemPlus.setSglprice(cartListBean.getSglprice());
            orderItemPlus.setCount(cartListBean.getCount());
            orderItemPlus.setPrice(cartListBean.getPrice());
            orderItemPlus.setBid(cartListBean.getBid());
            orderItemPlusList.add(orderItemPlus);
        }
        return orderItemPlusList;
    }

    @Override
    public List<OrderShow> orderShow(List<OrderBean> orderBeanList) {
        List<OrderShow> show = new ArrayList<>();

        for (OrderBean orderBean : orderBeanList) {
            OrderShow orderShow = new OrderShow();
            orderShow.setOrderBean(orderBean);
            List<OrderItem> orderItemList = orderDao.queryOrderItemByoid(orderBean.getOid());
            List<OrderItemPlus> orderItemPlusList = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                BookBean bookBean = orderDao.queryBookByBid(orderItem.getBid());
                OrderItemPlus orderItemPlus = new OrderItemPlus();
                orderItemPlus.setBid(orderItem.getBid());
                double a = (Integer.parseInt(orderItem.getCount())) * (Double.parseDouble(bookBean.getPrice()));
                BigDecimal decimal = new BigDecimal(String.valueOf(a));
                BigDecimal price = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
                orderItemPlus.setPrice(String.valueOf(price));
                orderItemPlus.setCount(orderItem.getCount());
                orderItemPlus.setSglprice(bookBean.getPrice());
                orderItemPlus.setImage(bookBean.getImage());
                orderItemPlus.setAuthor(bookBean.getAuthor());
                orderItemPlus.setBname(bookBean.getBname());
                orderItemPlusList.add(orderItemPlus);
            }
            orderShow.setOrderItemPlusList(orderItemPlusList);
            show.add(orderShow);
        }

        return show;
    }

    @Override
    public boolean updateOrdersByuidoid(String uid, String oid, String address, String state) {
        return orderDao.updateOrdersuidoid(uid, oid, address, state);
    }

    @Override
    public OrderBean queryOrderBeanByoid(String oid) {
        return orderDao.queryOrderBeanByoid(oid);
    }

    @Override
    public boolean updateOrdersState2(String oid) {
        orderDao.updateOrdersState2(oid);
        OrderBean orderBean = orderDao.queryOrderBeanByoid(oid);
        if ("2".equals(orderBean.getState())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateOrdersState3(String oid) {
        orderDao.updateOrdersState3(oid);
        OrderBean orderBean = orderDao.queryOrderBeanByoid(oid);
        if ("3".equals(orderBean.getState())){
            return true;
        }else {

            return false;
        }
    }

    @Override
    public List<OrderBean> queryOrderBeanByState(String state) {
        return orderDao.queryOrderBeanByState(state);
    }
}


