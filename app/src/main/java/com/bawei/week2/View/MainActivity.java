package com.bawei.week2.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2.Bean.BeanAppivity;
import com.bawei.week2.Model.bean.Base;
import com.bawei.week2.Presenter.HomePresenter;
import com.bawei.week2.R;
import com.bawei.week2.View.adapterView.RViewAdapter;
import com.bawei.week2.icontract.IHomeContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BeanAppivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.view)
    RecyclerView view;

    @Override
    protected void initDate() {
        mPresenter.getModedate();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter priPresenter() {
        return new HomePresenter();
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBean(Base base) {
        List<Base.ResultBean> result = base.getResult();

        view.setLayoutManager(new LinearLayoutManager(this));
        RViewAdapter rViewAdapter = new RViewAdapter(result);
        view.setAdapter(rViewAdapter);

        rViewAdapter.setOnclickItem(new RViewAdapter.OnclickItem() {
            @Override
            public void onclick(int p) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(Throwable throwable) {

    }


}
