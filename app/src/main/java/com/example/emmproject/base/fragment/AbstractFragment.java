package com.example.emmproject.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.emmproject.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbstractFragment extends Fragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(getLayoutId(),container,false);


        unbinder= ButterKnife.bind(this,view);
        initView();
        initEventAndData();
        return view;
    }



    @Override
    public void onDestroyView() {

        super.onDestroyView();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
    }


    protected abstract void initView() ;

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();


}
