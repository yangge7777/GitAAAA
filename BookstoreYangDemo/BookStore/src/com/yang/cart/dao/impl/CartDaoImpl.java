package com.yang.cart.dao.impl;

import com.lanou.jdbc.GxQueryRunner;
import com.yang.cart.bean.CartBean;
import com.yang.cart.bean.CartListBean;
import com.yang.cart.bean.CartuidbidCountBean;
import com.yang.cart.dao.CartDao;
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
public class CartDaoImpl implements CartDao {
        GxQueryRunner runner =new GxQueryRunner();

    @Override
    public boolean queryByUidBid(String uid, String bid) {
        //有了返true
        String sql = "select * from cartlist where uid =? and bid =?";
        try {
         CartBean cartBean =   runner.query(sql,new BeanHandler<CartBean>(CartBean.class),uid,bid);
            return  cartBean!=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addToCart(String uid, String bid, String count) {
        String sql = "insert into cartlist values(?,?,?)";
        try {
            runner.update(sql,uid,bid,count);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateToCart(String uid,String bid,String count) {
        String sql ="update  cartlist  set count=?  where uid=? and bid= ?";
        String countsql = "select * from cartlist where uid=? and bid =? ";
        try {

             CartBean cartbean =runner.query(countsql,new BeanHandler<CartBean>(CartBean.class),uid,bid);
            String currentCount =cartbean.getCount();
            int newcount =Integer.parseInt(currentCount)+Integer.parseInt(count);
            String.valueOf(newcount);
            runner.update(sql,newcount,uid,bid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartListBean> queryByCart(String uid) {
        String sql ="select b.bid, b.image,b.bname,b.author,b.price as sglprice,c.count,c.count*b.price as price from cartlist c left join book b on c.bid=b.bid where c.uid=?";
        try {
        return     runner.query(sql,new BeanListHandler<CartListBean>(CartListBean.class),uid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CartuidbidCountBean> queryuidbidcountByuid(String uid) {
        String sql = "select * from cartlist where uid= ?";
        try {
        return runner.query(sql,
                new BeanListHandler<CartuidbidCountBean>(CartuidbidCountBean.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delAll(String uid) {
        String sql = "delete from cartlist where uid =?";
        try {
            runner.update(sql,uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delbid(String uid, String bid) {
        String sql ="delete from cartlist where uid=? and bid =?";
        try {
            runner.update(sql,uid,bid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
