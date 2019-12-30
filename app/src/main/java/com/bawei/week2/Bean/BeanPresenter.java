package com.bawei.week2.Bean;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  14:05
 * 类名：com.bawei.week2.Bean
 */
public abstract class BeanPresenter <V> {
    protected  V view;

    public void attach(V view) {
        this.view = view;
    }
    public void dttdch( ) {
     view=null;
    }

    public BeanPresenter() {
        initMode();
    }

    protected abstract void initMode();
}
