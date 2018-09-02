package com.yang.user.dao.impl;

import com.lanou.jdbc.GxQueryRunner;
import com.yang.user.bean.UserBean;
import com.yang.user.dao.UserDao;
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
public class UserDaoImpl implements UserDao {
    GxQueryRunner runner =new GxQueryRunner();
    @Override
    public boolean registerUser(UserBean userBean) {
        //添加用户
        String sql ="insert into tb_user values(?,?,?,?,?,?,?)";
        try {
            int count = runner.update(
                    sql,userBean.getUid(),userBean.getUsername(),userBean.getPassword()
            ,userBean.getEmail(),userBean.getCode(),userBean.getState(),"user");

           return count ==1 ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false ;
    }

    @Override
    public boolean queryByUsername(String username) {
       //查看用户名
        String sql ="select * from tb_user where username =?";
        try {
//            List<UserBean> userBeanList =runner.query(
//                    sql,new BeanListHandler<UserBean>(UserBean.class),username);
//            return userBeanList.size()>0;
//
            UserBean userBean =runner.query(
                    sql,new BeanHandler<UserBean>(UserBean.class),username);

            return userBean!=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

return  false;
    }

    @Override
    public boolean queryByEmail(String email) {
        //查看邮箱是否存在
        String sql ="select * from tb_user where email =?";
        try {
            UserBean userBean =runner.query
                    (sql ,new BeanHandler<UserBean>(UserBean.class),email);
            return userBean!=null ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public boolean queryLogin(UserBean userBean) {
        String sql = "select * from tb_user where username=? and password =?";
        try {
            UserBean fun =runner.query(
                    sql,new BeanHandler<UserBean>(UserBean.class),
                    userBean.getUsername(),userBean.getPassword());
            return fun!=null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserBean queryByusername(String username) {
        String sql = "select * from tb_user where username=?";
        try {
            UserBean userBean =runner.query(sql, new BeanHandler<UserBean>(UserBean.class),username);
            return userBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUserByuid(String uid) {
        String sql = "update tb_user set state=1 where uid =?";
        try {
         int fun =   runner.update(sql, uid);
            return  fun==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }

    @Override
    public boolean queryUserState1(String uid) {
        String sql ="select state from tb_user where uid =?";
        try {
          UserBean bean =  runner.query(sql, new BeanHandler<UserBean>(UserBean.class), uid);
            return "1".equals(bean.getState());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
