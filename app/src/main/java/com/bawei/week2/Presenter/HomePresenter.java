package com.bawei.week2.Presenter;

import com.bawei.week2.Bean.BeanPresenter;
import com.bawei.week2.Model.HomeModel;
import com.bawei.week2.Model.bean.Base;
import com.bawei.week2.icontract.IHomeContract;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  14:14
 * 类名：com.bawei.week2.Presenter
 */
public class HomePresenter extends BeanPresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initMode() {
        homeModel = new HomeModel();
    }

    @Override
    public void getModedate() {
            homeModel.getModedate(new IHomeContract.IModel.Homedate() {
                @Override
                public void onBean(Base base) {
                    view.onBean(base);
                }

                @Override
                public void onError(Throwable throwable) {
                    view.onError(throwable);
                }
            });
    }
}
