package com.yang.user.service.impl;

import com.lanou.commons.CommonUtils;
import com.yang.user.bean.UserBean;
import com.yang.user.dao.impl.UserDaoImpl;
import com.yang.user.service.UserService;

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
public class UserServiceImpl implements UserService {
    UserDaoImpl userDao =new UserDaoImpl();
    //注册
    @Override
    public void registerUser(UserBean userBean) {
        //随机生成一个uid
        userBean.setUid(CommonUtils.uuid());
        userDao.registerUser(userBean);

        }
    @Override
    public boolean registerUser_username(UserBean userBean) {
        return userDao.queryByUsername(userBean.getUsername());
    }

    @Override
    public boolean registerUser_email(UserBean userBean) {
        return userDao.queryByEmail(userBean.getEmail());

    }

    @Override
    public boolean loginUser(UserBean userBean) {
        return userDao.queryLogin(userBean);
    }

    @Override
    public UserBean queryByusername(String username) {
        return userDao.queryByusername(username);
    }

    @Override
    public boolean updateUserByuid(String uid) {
        return userDao.updateUserByuid(uid);
    }

    @Override
    public boolean queryUserState1(String uid) {
        return userDao.queryUserState1(uid);
    }


}
