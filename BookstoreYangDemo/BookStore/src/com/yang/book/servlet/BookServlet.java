package com.yang.book.servlet;

import com.yang.book.bean.BookBean;
import com.yang.book.service.BookServiceImpl;
import com.yang.category.bean.CategoryBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
@WebServlet(name = "BookServlet", urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String cid = request.getParameter("cid");
     //   System.out.println(cid);
        String cname = request.getParameter("cname");
        if ("queryAll".equals(method)){
            findAll(request, response);
        }else if ("load".equals(method)){
        //    System.out.println("bbbbb");
            load(request,response);
        }else {
           // System.out.println("aaaaaaa");
            findcid(request,response, cid);
        }
//        switch (method) {
//
//
//
//
//
//
//            case "queryAll":
//                findAll(request, response);
//                break;
//            case "queryJavaSE":
//                findSE(request, response);
//                break;
//            case "queryJavaEE":
//                findEE(request, response);
//                break;
//            case "queryJavascript":
//                findJS(request, response);
//                break;
//            case "load":
//                load(request,response);
//                break;
//        }
    }

    private void findcid(HttpServletRequest request, HttpServletResponse response, String cid) {
        List<BookBean> bookBeanList= bookService.cidBook(cid);
        request.setAttribute("BookBeanList",bookBeanList);
        try {
            request.getRequestDispatcher("jsps/book/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        List<BookBean> allBookBeanList = bookService.AllBook();
        request.setAttribute("BookBeanList", allBookBeanList);
        try {
            request.getRequestDispatcher("jsps/book/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private void findSE(HttpServletRequest request, HttpServletResponse response) {
//        List<BookBean> SEBookBeanList = bookService.SEBook();
//        request.setAttribute("BookBeanList", SEBookBeanList);
//        try {
//            request.getRequestDispatcher("jsps/book/list.jsp").forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private void findEE(HttpServletRequest request, HttpServletResponse response) {
//        List<BookBean> EEBookBeanList =bookService.EEBook();
//        request.setAttribute("BookBeanList",EEBookBeanList);
//        try {
//            request.getRequestDispatcher("jsps/book/list.jsp").forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void findJS(HttpServletRequest request, HttpServletResponse response) {
 //      List<BookBean> JSBookBeanList =bookService.JSBook();
//        request.setAttribute("BookBeanList",JSBookBeanList);
//        try {
//            request.getRequestDispatcher("jsps/book/list.jsp").forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    private void load(HttpServletRequest request, HttpServletResponse response) {
            String bid =request.getParameter("bid");
          BookBean bookBean = bookService.LoadBook(bid);
        request.setAttribute("bookBean",bookBean);
      //  System.out.println(bid);
     //   System.out.println(bookBean);
        request.getSession().setAttribute("bookBean",bookBean);
        try {
            request.getRequestDispatcher("jsps/book/desc.jsp").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
