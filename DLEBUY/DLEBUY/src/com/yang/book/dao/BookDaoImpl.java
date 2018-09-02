package com.yang.book.dao;

import com.lanou.commons.CommonUtils;
import com.lanou.jdbc.GxQueryRunner;
import com.yang.book.bean.BookBean;
import com.yang.user.bean.UserBean;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 18/6/21.
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
public class BookDaoImpl implements BookDao{
    GxQueryRunner runner =new GxQueryRunner();
    @Override
    public List<BookBean> queryAllBook() {
        List<BookBean> allBookBeanList =null;
        String sql ="select * from book";
        try {
            allBookBeanList  = runner.query
                    (sql,new BeanListHandler<BookBean>(BookBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
return allBookBeanList;

    }

    @Override
    public List<BookBean> querySEBook() {
        List<BookBean> SEBookBeanList =null;
        String sql ="select * from book where cid=1";
        try {
            SEBookBeanList  = runner.query
                    (sql,new BeanListHandler<BookBean>(BookBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SEBookBeanList;
    }

    @Override
    public List<BookBean> queryEEBook() {
        List<BookBean> EEBookBeanList =null;
        String sql ="select * from book where cid=2";
        try {
            EEBookBeanList  = runner.query
                    (sql,new BeanListHandler<BookBean>(BookBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EEBookBeanList;

    }

    @Override
    public List<BookBean> queryJSBook() {
        List<BookBean> JSBookBeanList =null;
        String sql ="select * from book where cid=3";
        try {
            JSBookBeanList  = runner.query
                    (sql,new BeanListHandler<BookBean>(BookBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return JSBookBeanList;
    }
    @Override
    public List<BookBean> querycidBook(String cid) {
        List<BookBean > cidBook =null;
        String sql = "select * from book where cid = ?";
        try {
         cidBook= runner.query(sql,new BeanListHandler<BookBean>(BookBean.class),cid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidBook;
    }

    @Override
    public boolean updateBook(BookBean bookBean) {
        String sql ="update book set  bname=?,price=?,author=?,cid=? where bid =?";
        try {
          int fun =  runner.update(sql,bookBean.getBname(),bookBean.getPrice(),bookBean.getAuthor(),bookBean.getCid(),bookBean.getBid());
            return  fun==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delBook(String bid) {
        String sql = "delete from book where bid=?";
        try {
          int fun =  runner.update(sql, bid);
            return fun==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addBook(BookBean bookBean) {
        String sql = "insert into book values (?,?,?,?,?,?)  ";
        try {
        int fun=    runner.update(sql,
                    bookBean.getBid(),bookBean.getBname(),
                    bookBean.getPrice(),bookBean.getAuthor(),bookBean.getImage(),bookBean.getCid());
            return fun==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BookBean queryByBid(String bid) {
        BookBean bookBean = new BookBean();
        String sql ="select * from book where bid =?";
        try {
            bookBean =runner.query(sql,new BeanHandler<BookBean>(BookBean.class),bid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookBean;
    }


}
