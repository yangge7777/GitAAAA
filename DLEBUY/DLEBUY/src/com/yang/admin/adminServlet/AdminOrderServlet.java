package com.yang.admin.adminServlet;

import com.yang.order.bean.OrderBean;
import com.yang.order.bean.OrderShow;
import com.yang.order.service.Impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
@WebServlet(name = "AdminOrderServlet",urlPatterns = "/adminOrderServlet")
public class AdminOrderServlet extends HttpServlet {
    OrderServiceImpl orderService =new OrderServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method){
            case "queryAll" :
                queryAllOrder(request, response);
                break;
            //确认发货
            case "send" :
                send(request, response);
                break;
            case "queryBystate":
                queryBystate(request,response);
                break;
        }




    }

    private void queryBystate(HttpServletRequest request, HttpServletResponse response) {
        String state = request.getParameter("state");
      List<OrderBean> orderBeanList=   orderService.queryOrderBeanByState(state);
         List<OrderShow> ordershowList =   orderService.orderShow(orderBeanList);
        request.getSession().setAttribute("ordershowlist",ordershowList);
        try {
            request.getRequestDispatcher("/adminjsps/admin/order/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("aaa");
        boolean flag=   orderService.updateOrdersState3(oid);
        if (flag){
            request.setAttribute("msg","发货成功");
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","发货失败");
            request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request,response);
        }

    }

    private void queryAllOrder(HttpServletRequest request, HttpServletResponse response) {
      String uid = (String) request.getSession().getAttribute("uid");
      List<OrderBean> orderBeanList=  orderService.queryOrders();
     List<OrderShow> orderShowList =   orderService.orderShow(orderBeanList);
        request.getSession().setAttribute("ordershowlist",orderShowList);
        try {
            request.getRequestDispatcher("/adminjsps/admin/order/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
