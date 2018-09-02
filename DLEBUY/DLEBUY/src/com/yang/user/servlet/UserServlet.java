package com.yang.user.servlet;

import com.lanou.commons.CommonUtils;
import com.yang.category.bean.CategoryBean;
import com.yang.category.service.CategoryServiceImpl;
import com.yang.user.bean.UserBean;
import com.yang.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
@WebServlet(name = "UserServlet",urlPatterns = "/userSevlet")
public class UserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    CategoryServiceImpl categoryService =new CategoryServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method =request.getParameter("method");
        switch (method){
            case "login": login(request,response);
                break;
            case "quit":  quit(request,response);
                break;
        }





    }


    private void login(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("CategoryBeanList",categoryService.queryCategory());
        //请求信息放到对象中
        UserBean userBean = CommonUtils.toBean(request.getParameterMap(), UserBean.class);
        //判断是否数据库中有这个对象
        boolean flag = userService.loginUser(userBean);
        if (flag){
            //判断是否激活
            UserBean fun= userService.queryByusername(userBean.getUsername());



            boolean state = userService.queryUserState1(fun.getUid());
            if (!state){
                request.setAttribute("msg","请阅读您的邮件,激活后登录");

                try {
                    request.getRequestDispatcher("/jsps/user/login.jsp").forward(request,response);
                    return;
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //如果是管理员 进入管理员界面
          UserBean user= userService.queryByusername(userBean.getUsername());
            if ("Admin".equals(user.getAdmin())){
                try {
                    request.getSession().setAttribute("uid",user.getUid());
                    request.getSession().setAttribute("username",user.getUsername());

                    request.getSession().setAttribute("userBean",user);
                    response.sendRedirect("adminjsps/admin/index.jsp");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            HttpSession session =request.getSession();
            session.setAttribute("userBean",userBean);
           UserBean bean = userService.queryByusername(userBean.getUsername());
            session.setAttribute("uid",bean.getUid());
            try {
                Cookie cookie =new Cookie("username", URLEncoder.encode(userBean.getUsername(),"utf8"));
                cookie.setMaxAge(60);
                response.addCookie(cookie);
                response.sendRedirect("jsps/main.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

            request.setAttribute("msg","用户名或密码错误 请重新输入");
            try {
                request.getRequestDispatcher("/jsps/user/login.jsp").forward(request,response);

            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void quit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session =request.getSession();
        session.removeAttribute("userBean");
        session.removeAttribute("username");
        session.removeAttribute("uid");
        session.removeAttribute("bookBean");
        session.removeAttribute("sumprice");
        session.removeAttribute("cartuidbidCountBean");
        session.removeAttribute("cartListBean");
        session.removeAttribute("orderItemPlusList");
        session.removeAttribute("address");
        session.removeAttribute("orderBeanList");
        session.removeAttribute("orderItemList");
        session.removeAttribute("orderBeanCurrent");
        session.removeAttribute("orderItemListCurrent");
        session.removeAttribute("ordershowlist");
        session.removeAttribute("CategoryBeanList");


        try {
            request.setAttribute("msg","退出成功");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
