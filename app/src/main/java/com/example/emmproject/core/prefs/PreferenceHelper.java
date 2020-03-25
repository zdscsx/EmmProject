package com.example.emmproject.core.prefs;

public interface PreferenceHelper {

    void setToken(String token);

    String getToken();

    void setUesrPhone(String uesrname);

    String getUserPhone();

    void setId(Integer id);

    Integer getId();

    void saverefreshToken(String cookie);

    String getrefreshToken();



}
