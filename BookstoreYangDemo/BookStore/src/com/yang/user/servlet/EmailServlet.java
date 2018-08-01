package com.yang.user.servlet;

import com.yang.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dllo on 18/6/26.
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
@WebServlet(name = "EmailServlet",urlPatterns = "/email")
public class EmailServlet extends HttpServlet {
    UserServiceImpl userService =new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method){
            case "comeon":
                comeon(request, response);
                break;
        }





    }

    private void comeon(HttpServletRequest request, HttpServletResponse response) {
        try {
          //  response.getWriter().write("<h1>恭喜激活成功<h1/>");
            response.getWriter().write("<script >alert(\"恭喜您成为大型激情购书平台荣誉VIP用户\")</script>");
            response.getWriter().write("<a href=\"http://172.16.16.156:8080/bookstore/jsps/user/login.jsp\">点我跳转登录界面</a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uid = request.getParameter("uid");
     userService.updateUserByuid(uid);

    }
}
