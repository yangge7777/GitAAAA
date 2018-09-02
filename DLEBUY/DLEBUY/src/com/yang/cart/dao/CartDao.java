package com.yang.cart.dao;

import com.yang.cart.bean.CartListBean;
import com.yang.cart.bean.CartuidbidCountBean;

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
public interface CartDao {
    //查询购物车列表中是否存在uid,bid
    boolean queryByUidBid(String uid ,String bid);
    //向购物车列表中添加
    void addToCart(String uid,String bid,String count);
    //向购物车列表中修改
    void updateToCart(String uid,String bid,String count);
    //购物车列表中查询数据
    List<CartListBean> queryByCart(String uid);
    //购物车列表中查询uidbidcount数据
    List<CartuidbidCountBean> queryuidbidcountByuid(String uid );
    //删除uid购物车列表中所有数据
    void delAll(String uid);
    //删除uid购物车列表中指定的bid数据
    void delbid(String uid ,String bid );

}
