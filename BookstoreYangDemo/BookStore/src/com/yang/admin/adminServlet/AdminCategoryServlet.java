package com.yang.admin.adminServlet;

import com.lanou.commons.CommonUtils;
import com.yang.book.bean.BookBean;
import com.yang.book.service.BookServiceImpl;
import com.yang.category.bean.CategoryBean;
import com.yang.category.service.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 18/6/25.
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
@WebServlet(name = "AdminCategoryServlet",urlPatterns = "/adminCategoryServlet")
public class AdminCategoryServlet extends HttpServlet {
    CategoryServiceImpl categroyservice =new CategoryServiceImpl();
    BookServiceImpl bookService =new BookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method){
            case "queryAll":
                queryAllCategory(request, response);
                break;
            case "mod" :
                modCategory(request, response);
                break;
            case "del":
                delCategory(request, response);
                break;
            case "add":
                addCategory(request, response);
        }






    }
    private void queryAllCategory(HttpServletRequest request, HttpServletResponse response) {
       List<CategoryBean> categoryBeanList=  categroyservice.queryCategory();
        request.getSession().setAttribute("CategoryBeanList",categoryBeanList);
        try {
            request.getRequestDispatcher("/adminjsps/admin/category/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void modCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        //test!
        System.out.println(cname);
        //flag true 成功
     boolean flag
             =    categroyservice.updatecategorycnameBycid(cid,cname);
        if (flag){
            request.setAttribute("msg","修改成功") ;
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        }else {
            request.setAttribute("msg","修改失败") ;
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        }

    }

    private void delCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
      List<BookBean> bookBeanList =  bookService.cidBook(cid);
      if (bookBeanList==null||bookBeanList.isEmpty()){
          boolean flag = categroyservice.delcategoryBycid(cid);
          if (flag){
              request.setAttribute("msg","修改成功") ;
              request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
          }else {
              request.setAttribute("msg","修改失败") ;
              request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
          }
      }else {
          request.setAttribute("msg","需先清空分类中的图书");
          request.getRequestDispatcher("/adminjsps/admin/category/del.jsp").forward(request,response);
      }




    }
    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String uid = CommonUtils.uuid();
        boolean flag = categroyservice.addcategory(uid,cname);
        if (flag){
            request.setAttribute("msg","添加成功") ;
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        }else {
            request.setAttribute("msg","添加失败") ;
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
        }
    }
}
