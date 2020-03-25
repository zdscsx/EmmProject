package com.example.emmproject.core.db;

import com.example.emmproject.app.EmmApplication;
import com.example.emmproject.core.bean.mine.DaoSession;
import com.example.emmproject.core.bean.mine.User;
import com.example.emmproject.core.bean.mine.UserDao;

import javax.inject.Inject;

public class DbHelperImpl implements DbHelper {

    private DaoSession mDaoSession;
    private UserDao mUSerDao;
    @Inject
    public DbHelperImpl() {

        mDaoSession = EmmApplication.getInstance().getDaoSession();
        mUSerDao=mDaoSession.getUserDao();
    }

    @Override
    public void saveUser(User user) {
        mUSerDao.insertOrReplace(user);
    }

    @Override
    public User getUser(String phone) {
        return mUSerDao.queryBuilder().where(UserDao.Properties.Phone.eq(phone)).unique();
    }
}
