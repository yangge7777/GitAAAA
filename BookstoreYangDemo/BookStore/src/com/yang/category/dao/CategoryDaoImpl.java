package com.yang.category.dao;

import com.lanou.jdbc.GxQueryRunner;
import com.yang.category.bean.CategoryBean;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
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
public class CategoryDaoImpl implements CategoryDao {
    GxQueryRunner runner =new GxQueryRunner();
    @Override
    public List<CategoryBean> queryCategory() {
        String sql = "select * from category";
        try {
       return      runner.query(sql,new BeanListHandler<CategoryBean>(CategoryBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean pdatecategorycnameBycid(String cid ,String cname) {
        String sql = "update category set cname = ? where cid = ?";
        try {
         int fun=    runner.update(sql, cname,cid);
            return  fun==1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public CategoryBean queryCategoryBeanBycid(String cid) {
        String sql = "select cname from category where cid =?";
        try {
            return    runner.query(sql,new BeanHandler<CategoryBean>(CategoryBean.class),cid);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean delcategoryBycid(String cid) {
        String sql = "delete from category where cid =?";
        try {
         int fun =   runner.update(sql, cid);
            return  fun==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addcategory(String uid, String cname) {
        String sql = "insert into  category  values(?,?)";
        try {
        int fun =    runner.update(sql,uid, cname);
          return  fun==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
