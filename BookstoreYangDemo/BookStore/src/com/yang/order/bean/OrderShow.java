package com.yang.order.bean;

import java.util.List;

/**
 * Created by dllo on 18/6/23.
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
public class OrderShow {
    private OrderBean orderBean ;
    private List<OrderItemPlus> orderItemPlusList ;

    @Override
    public String toString() {
        return "OrderShow{" +
                "orderBean=" + orderBean +
                ", orderItemPlusList=" + orderItemPlusList +
                '}';
    }

    public OrderShow() {
    }

    public OrderShow(OrderBean orderBean, List<OrderItemPlus> orderItemPlusList) {

        this.orderBean = orderBean;
        this.orderItemPlusList = orderItemPlusList;
    }

    public OrderBean getOrderBean() {

        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public List<OrderItemPlus> getOrderItemPlusList() {
        return orderItemPlusList;
    }

    public void setOrderItemPlusList(List<OrderItemPlus> orderItemPlusList) {
        this.orderItemPlusList = orderItemPlusList;
    }
}

