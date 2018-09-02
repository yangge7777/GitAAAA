package com.yang.cart.service.impl;

import com.yang.cart.bean.CartBean;
import com.yang.cart.bean.CartListBean;
import com.yang.cart.bean.CartuidbidCountBean;
import com.yang.cart.dao.impl.CartDaoImpl;
import com.yang.cart.service.CartService;

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
public class CartServiceImpl implements CartService {
    CartDaoImpl cartDao =new CartDaoImpl();
    @Override
    public void addCart(String uid, String bid, String count) {
        //有了flag为true 执行改变 没有事false执行添加
      boolean flag =  cartDao.queryByUidBid(uid,bid);
        if (flag){
        cartDao.updateToCart(uid,bid,count);
        }else {
            cartDao.addToCart(uid,bid,count);
        }
    }

    @Override
    public List<CartListBean> queryByUid(String uid) {
        List<CartListBean> cartListBeanList = cartDao.queryByCart(uid);

        for (CartListBean listBean : cartListBeanList) {

         String fun =  String.valueOf(listBean.getSglprice());
        String funprice =String.valueOf(listBean.getPrice());
            BigDecimal decimal = new BigDecimal(fun);
            BigDecimal decimalprice = new BigDecimal(funprice);

            BigDecimal aaa = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal bbb = decimalprice.setScale(2,BigDecimal.ROUND_HALF_UP);
            listBean.setSglprice(String.valueOf(aaa));
            listBean.setPrice(String.valueOf(bbb));
        }
        return cartListBeanList;
    }

    @Override
    public List<CartuidbidCountBean> querycartlistByuid(String uid) {
     return    cartDao.queryuidbidcountByuid(uid);
    }

    @Override
    public void clearAllCartByUid(String uid) {
        cartDao.delAll(uid);
    }

    @Override
    public void clearBidCartByUid(String uid, String bid) {
        cartDao.delbid(uid,bid);
    }
}
