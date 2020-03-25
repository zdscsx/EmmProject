package com.example.emmproject.core.db;

import com.example.emmproject.core.bean.mine.User;

public interface DbHelper {

     void saveUser(User user);

     User getUser(String phone);
}
