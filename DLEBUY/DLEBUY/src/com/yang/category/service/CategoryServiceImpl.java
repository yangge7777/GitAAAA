package com.yang.category.service;

import com.yang.category.bean.CategoryBean;
import com.yang.category.dao.CategoryDaoImpl;

import java.util.List;

/**
 * Created by dllo on 18/6/24.
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
public class CategoryServiceImpl implements CategoryService {
    CategoryDaoImpl categoryDao =new CategoryDaoImpl();
    @Override
    public List<CategoryBean> queryCategory() {
   return categoryDao.queryCategory();
    }

    @Override
    public boolean updatecategorycnameBycid(String cid,String cname ) {
        return categoryDao.pdatecategorycnameBycid(cid, cname);
    }

    @Override
    public CategoryBean queryCategoryBeanBycid(String cid) {
      return   categoryDao.queryCategoryBeanBycid(cid);
    }

    @Override
    public boolean delcategoryBycid(String cid) {
        return categoryDao.delcategoryBycid(cid);
    }

    @Override
    public boolean addcategory(String uid,String cname) {
        return categoryDao.addcategory(uid,cname);
    }
}
