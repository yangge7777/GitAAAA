package com.yang.book.service;

import com.yang.book.bean.BookBean;
import com.yang.book.dao.BookDaoImpl;

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
public class BookServiceImpl implements BookService {
    BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public List<BookBean> AllBook() {
        return bookDao.queryAllBook();
    }

    @Override
    public List<BookBean> SEBook() {
        return bookDao.querySEBook();
    }

    @Override
    public List<BookBean> EEBook() {
        return bookDao.queryEEBook();
    }

    @Override
    public List<BookBean> JSBook() {
        return bookDao.queryJSBook();
    }

    @Override
    public BookBean LoadBook(String bid) {
        return bookDao.queryByBid(bid);
    }

    @Override
    public List<BookBean> cidBook(String cid) {
        return bookDao.querycidBook(cid);
    }

    @Override
    public boolean updateBook(BookBean bookBean) {
      return   bookDao.updateBook(bookBean);
    }

    @Override
    public boolean delBook(String bid) {
        return bookDao.delBook(bid);

    }

    @Override
    public boolean addBook(BookBean bookBean) {
        return bookDao.addBook(bookBean);
    }


}
