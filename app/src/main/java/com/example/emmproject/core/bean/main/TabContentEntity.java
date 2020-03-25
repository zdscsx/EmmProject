package com.example.emmproject.core.bean.main;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabContentEntity implements CustomTabEntity {
    private String title;
    private int tabSelectIcon;
    private int tabUnselectIcon;

    public TabContentEntity(String title,int tabSelectIcon,int tabUnselectIcon) {
        this.tabSelectIcon=tabSelectIcon;
        this.tabUnselectIcon=tabUnselectIcon;
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return tabSelectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return tabUnselectIcon;
    }
}
