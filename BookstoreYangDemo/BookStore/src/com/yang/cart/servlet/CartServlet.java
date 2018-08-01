package com.yang.cart.servlet;

import com.yang.cart.bean.CartListBean;
import com.yang.cart.bean.CartuidbidCountBean;
import com.yang.cart.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
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
@WebServlet(name = "CartServlet", urlPatterns = "/cartServlet")
public class CartServlet extends HttpServlet {
    CartServiceImpl cartService =new CartServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method) {
            case "buy":
                addToCart(request, response);
            //    queryBycart(request, response);
                break;
            case "clearAll":
                clearAllcartByUid(request,response);
                break;
            case "clearBybid":
                clearbybid(request, response);
                break;
            case "mycart":
                queryBycart(request,response);
                break;


        }


    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String bid = request.getParameter("bid");
        String count = request.getParameter("count");
        String uid = (String) session.getAttribute("uid");
        if (count==null||count.isEmpty()){
            try {
                request.setAttribute("msg","购物数量不能为空");
                request.getRequestDispatcher("/jsps/book/desc.jsp").forward(request,response);

                return;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else {
            cartService.addCart(uid,bid,count);
        }

        /////
        List<CartListBean> cartListBean = cartService.queryByUid(uid);
        List<CartuidbidCountBean> cartuidbidCountBeanList =cartService.querycartlistByuid(uid);
        request.setAttribute("cartListBean",cartListBean);
        //
        double suprice=0;
        for (CartListBean listBean : cartListBean) {
            suprice+=Double.parseDouble(listBean.getPrice());
        }
        String fun =String.valueOf(suprice);
        BigDecimal decimal = new BigDecimal(fun);
        BigDecimal sumprice = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);
        //!!!!!!!!
        session.setAttribute("sumprice",String.valueOf(sumprice));
        session.setAttribute("cartListBean",cartListBean);
        session.setAttribute("cartuidbidCountBean",cartuidbidCountBeanList);
        //
        try {
            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    //!!!
    private void queryBycart(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
      List<CartListBean> cartListBean = cartService.queryByUid(uid);
        request.setAttribute("cartListBean",cartListBean);
        //
        double suprice=0;
        for (CartListBean listBean : cartListBean) {
            suprice+=Double.parseDouble(listBean.getPrice());
        }
        String fun =String.valueOf(suprice);
        BigDecimal decimal = new BigDecimal(fun);
        BigDecimal sumprice = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);

        session.setAttribute("sumprice",String.valueOf(sumprice));
        session.setAttribute("cartListBean",cartListBean);
        //
        try {
            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void clearAllcartByUid(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
        cartService.clearAllCartByUid(uid);
        List<CartListBean> cartListBean = cartService.queryByUid(uid);
        request.setAttribute("cartListBean",cartListBean);
        session.setAttribute("cartListBean",cartListBean);
        session.setAttribute("sumprice","0");
        try {
            response.sendRedirect("jsps/cart/list.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void clearbybid(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
            String bid = request.getParameter("bid");
            cartService.clearBidCartByUid(uid,bid);
        List<CartListBean> cartListBean = cartService.queryByUid(uid);
        request.setAttribute("cartListBean",cartListBean);
        //
        double suprice=0;
        for (CartListBean listBean : cartListBean) {
            suprice+=Double.parseDouble(listBean.getPrice());
        }
        String fun =String.valueOf(suprice);
        BigDecimal decimal = new BigDecimal(fun);
        BigDecimal sumprice = decimal.setScale(2,BigDecimal.ROUND_HALF_DOWN);

        session.setAttribute("sumprice", String.valueOf(sumprice));
        session.setAttribute("cartListBean",cartListBean);

        try {
            request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request,response);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
}
