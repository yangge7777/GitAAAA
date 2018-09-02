package com.yang.order.dao.Impl;

import com.lanou.jdbc.GxQueryRunner;
import com.yang.book.bean.BookBean;
import com.yang.order.bean.OrderBean;
import com.yang.order.bean.OrderItem;
import com.yang.order.dao.OrderDao;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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
public class OrderDaoImpl implements OrderDao {
    GxQueryRunner runner = new GxQueryRunner();

    @Override
    public void addtoOrders(OrderBean orderBean) {
        String sql = "insert into orders values(?,?,?,?,?,?)";
        //oid ordertime total state uid address
        try {
            runner.update(sql,
                    orderBean.getOid(),
                    orderBean.getOrdertime(),
                    orderBean.getTotal(),
                    orderBean.getState(),
                    orderBean.getUid(),
                    orderBean.getAddress());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addtoOrderItem(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            String sql = "insert into orderitem values(?,?,?,?,?)";
            //iid count subtotal oid bid
            try {
                runner.update(sql,
                        orderItem.getIid(),
                        orderItem.getCount(),
                        orderItem.getSubtotal(),
                        orderItem.getOid(),
                        orderItem.getBid());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<OrderBean> queryOrdersByuid(String uid) {
        String sql = "select * from orders where uid =?";
        try {

           return runner.query(sql,new BeanListHandler<OrderBean>(OrderBean.class),uid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderBean> queryOrders() {
        String sql = "select * from orders";
        try {
            return runner.query(sql,new BeanListHandler<OrderBean>(OrderBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem>  queryOrderItemByoid(String oid) {
        String sql = "select * from orderitem where oid =?";
        try {
            return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BookBean queryBookByBid(String bid) {
        String sql = "select * from book where bid =?";
        try {
          return   runner.query(sql , new BeanHandler<BookBean>(BookBean.class),bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateOrdersuidoid(String uid, String oid,String address,String state) {
        String sql = "update orders set address = ?,state=? where uid=? and oid=?";
        try {
          int fun =  runner.update(sql,address,state,uid,oid);
            return fun==1 ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderBean queryOrderBeanByoid(String oid) {
        String sql = "select * from orders where oid =?";
        try {
          return   runner.query(sql,new BeanHandler<OrderBean>(OrderBean.class),oid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void updateOrdersState2(String oid) {
        String sql = "update orders set state=2 where oid =? ";
        try {
            runner.update(sql,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateOrdersState3(String oid) {
        String sql = "update orders set state=3 where oid =? ";
        try {
            runner.update(sql,oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderBean> queryOrderBeanByState(String state) {
        String sql = "select * from orders where state = ?";
        try {
          return   runner.query(sql,new BeanListHandler<OrderBean>(OrderBean.class),state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
