package com.yang.order.servlet;

import com.yang.cart.bean.CartListBean;
import com.yang.cart.service.impl.CartServiceImpl;
import com.yang.order.bean.OrderBean;
import com.yang.order.bean.OrderItem;
import com.yang.order.bean.OrderItemPlus;
import com.yang.order.bean.OrderShow;
import com.yang.order.service.Impl.OrderServiceImpl;

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
    @WebServlet(name = "OrderServlet", urlPatterns = "/orderServlet")
public class OrderServlet extends HttpServlet {
    OrderServiceImpl orderService = new OrderServiceImpl();
    CartServiceImpl cartService = new CartServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "add":
                addcart(request, response);
                break;
            case "myorder":
                showallorder(request, response);
                break;
            case "pay":
                updateaddressState(request, response);
                break;
            //付款
            case "load":
                loadorder(request,response);
                break;
            //确认收货
            case "ABR":
                ABOUTCINFIRMRECEIPT(request,response);
                break;

        }

    }



    private void addcart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //拿到购物车里信息
        List<CartListBean> cartlistbeanList =
                (List<CartListBean>) session.getAttribute("cartListBean");
        if (cartlistbeanList==null||cartlistbeanList.isEmpty()){
            request.setAttribute("msg","购物车里还没有东西哦(づ￣3￣)づ╭❤～");
            try {
                request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }


        //将购物车信息添加到itemplus
        List<OrderItemPlus> orderItemPlusList = orderService.cartToPlus(cartlistbeanList);
        session.setAttribute("orderItemPlusList", orderItemPlusList);

        //拿到总价
        String sumprice = (String) session.getAttribute("sumprice");
        //拿到uid
        String uid = (String) session.getAttribute("uid");
        //拿到地址
        String address = (String) session.getAttribute("address");
        //当前orderbean(订单)
        OrderBean orderBean = orderService.createaOrderBean(sumprice, uid, address);
        //添加信息到Orders表中
        orderService.addOrders(orderBean);
        //获取一个orderItem List
        List<OrderItem> orderItemList = orderService.cartToOrderItem(
                cartlistbeanList, orderBean.getOid());
        //添加信息到orderItem表中
        orderService.addOrderItem(orderItemList);
        //订单生成后 清除购物车所有内容
        cartService.clearAllCartByUid(uid);
        session.setAttribute("orderBeanCurrent", orderBean);
        session.setAttribute("orderItemListCurrent", orderItemList);

        try {
            request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showallorder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //拿到uid
        String uid = (String) session.getAttribute("uid");
        List<OrderBean> orderBeanList = orderService.queryOrdersByuid(uid);
      //  session.setAttribute("orderBeanList", orderBeanList);
        List<OrderShow> orderShowList = orderService.orderShow(orderBeanList);
//        for (OrderShow orderShow : orderShowList) {
//            System.out.println(orderShow);
//        }

        session.setAttribute("ordershowlist",orderShowList);
        try {
            request.getRequestDispatcher("/jsps/order/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateaddressState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = (String) request.getSession().getAttribute("uid");
        String oid= request.getParameter("oid");
            String address =request.getParameter("address");
            String satate = "1";
         boolean flag = orderService.updateOrdersByuidoid(uid,oid,address,satate);
                if (flag){
                    request.setAttribute("msg","付款成功");
                    request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
                }else {
                    request.setAttribute("msg","付款失败");
                    request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
                }

    }
    private void loadorder(HttpServletRequest request, HttpServletResponse response) {
        String oid = request.getParameter("aaa");
        //String uid = (String) request.getSession().getAttribute("uid");
        OrderBean orderBean = orderService.queryOrderBeanByoid(oid);
        List<OrderBean> orderBeanList =new ArrayList<>();
        orderBeanList.add(orderBean);
//        System.out.println(oid);
//        for (OrderBean bean : orderBeanList) {
//            System.out.println(bean);
//        }
      List<OrderShow> ordershowList=  orderService.orderShow(orderBeanList);
        List<OrderItemPlus> orderItemPlusList =new ArrayList<>();
        for (OrderShow orderShow : ordershowList) {
          orderItemPlusList =  orderShow.getOrderItemPlusList();
        }
        request.getSession().setAttribute("orderBeanCurrent",orderBean);
        request.getSession().setAttribute("orderItemPlusList",orderItemPlusList);
        try {
            request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void ABOUTCINFIRMRECEIPT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("aaa");
       boolean flag = orderService.updateOrdersState2(oid);
        if (flag){
            request.setAttribute("msg","收货成功");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","收货确认失败");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
        }
    }
}
