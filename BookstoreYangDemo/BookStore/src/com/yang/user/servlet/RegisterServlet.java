package com.yang.user.servlet;

import com.lanou.commons.CommonUtils;
import com.yang.user.bean.UserBean;
import com.yang.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
//    public static String myEmailAccount = "";
//    public static String myEmailPassword = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用于存放前端数据
        UserBean userBean = CommonUtils.toBean(request.getParameterMap(), UserBean.class);
        boolean flag2 = userService.registerUser_username(userBean);
        boolean flag3 = userService.registerUser_email(userBean);

        if (flag3 &&!flag2) {
            request.setAttribute("msg", "注册失败,邮箱已存在");
            request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
        }  if (flag2&&!flag3) {
            request.setAttribute("msg", "注册失败,用户名已存在");
            request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
        }  if (flag2 && flag3) {
            request.setAttribute("msg", "注册失败,用户名邮箱已存在");
            request.getRequestDispatcher("/jsps/user/regist.jsp").forward(request, response);
        } if (!flag2&&!flag3){
            userService.registerUser(userBean);
         UserBean userBean1=   userService.queryByusername(userBean.getUsername());
         String uid =   userBean1.getUid();
            //发送激活邮件
            // 发送到的地址
            String targetemail = request.getParameter("email");
            //!!!!!!!

         //   System.out.println(targetemail);
            //发送到的邮件地址  发送邮件的标题 发送邮件的内容
            Send send= new Send();
            send.sendEmail(targetemail
                    ,"我是用来激活大型激情购物买书平台的邮件","<a href=\"http://172.16.16.156:8080/bookstore/email?method=comeon&uid="+uid+"\">点我激活</a>");

            request.setAttribute("msg","恭喜,注册成功 :激活邮件已发送您的邮箱 请进行确认");
            request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
        }


    }
}
