package com.yang.book.dao;

import com.yang.book.bean.BookBean;

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
public interface BookDao {
    //查询所有图书/全部分类
    List<BookBean> queryAllBook();
    //查询SE分类的图书
    List<BookBean> querySEBook ();
    //查询EE分类的图书
    List<BookBean> queryEEBook ();
    //查询JS分类的图书
    List<BookBean> queryJSBook ();
    //查询BId
    BookBean queryByBid(String bid);

    //查询cid分类的图书
    List<BookBean> querycidBook(String cid);
    //更新book表信息
    boolean updateBook(BookBean bookBean);
    //删除bid Book
    boolean delBook(String bid);

    //添加book
    boolean addBook(BookBean bookBean);
}
