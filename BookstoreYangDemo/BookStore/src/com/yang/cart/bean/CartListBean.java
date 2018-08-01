package com.yang.cart.bean;

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
public class CartListBean {
    private String image;
    private String bname;
    private String author;
    private String sglprice;
    private String count;
    private String price;
    private String bid;
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }



    @Override
    public String toString() {
        return "CartListBean{" +
                "image='" + image + '\'' +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", sglprice='" + sglprice + '\'' +
                ", count='" + count + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public CartListBean() {
    }

    public CartListBean(String image, String bname, String author, String sglprice, String count, String price) {

        this.image = image;
        this.bname = bname;
        this.author = author;
        this.sglprice = sglprice;
        this.count = count;
        this.price = price;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSglprice() {
        return sglprice;
    }

    public void setSglprice(String sglprice) {
        this.sglprice = sglprice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
