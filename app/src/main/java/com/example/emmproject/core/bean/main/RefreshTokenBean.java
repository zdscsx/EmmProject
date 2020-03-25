package com.example.emmproject.core.bean.main;

import com.google.gson.Gson;

public class RefreshTokenBean {

    /**
     * refresh_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJBdXRob3JpemF0aW9uIjoiNjNjZmY2MzItODg2Yy00MzMxLWEwZDAtYmM2YTkxMDQ1NmJjIiwicGhvbmUiOiIxMzEyODUzNjA1MSIsImV4cCI6MTU4NDEwMDUyNCwiaWF0IjoxNTgxNTA4NTI0fQ.hzNW6SxYgQvPRATqYHejzEcM-3BErgiVoNpfTl7P3es
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJBdXRob3JpemF0aW9uIjoiMDFlYjA1YjgtZGU4My00NjI4LWJkYTgtZWI4ZDM0MDNjNzAxIiwicGhvbmUiOiIxMzEyODUzNjA1MSIsImV4cCI6MTU4MTUxMjEzMywidXNlcklkIjoxMywiaWF0IjoxNTgxNTA4NTMzfQ.C3J0lKZs76h--lAfIlpjwfaqFR5plrOyU__5XjMl8rE
     */

    private String refresh_token;
    private String token;


    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
