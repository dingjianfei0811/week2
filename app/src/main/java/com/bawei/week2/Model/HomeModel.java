package com.bawei.week2.Model;

import com.bawei.week2.Model.bean.Base;
import com.bawei.week2.icontract.IHomeContract;
import com.bawei.week2.netutil.NetUtil;
import com.google.gson.Gson;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  14:14
 * 类名：com.bawei.week2.Model
 */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getModedate(Homedate homedate) {
        String http="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=5&keyword=%E6%9D%BF%E9%9E%8B";

        NetUtil.getInstance().getjsonGet(http, new NetUtil.Mycallback() {
            @Override
            public void getjson(String json) {
                Base base = new Gson().fromJson(json, Base.class);
                homedate.onBean(base);
            }

            @Override
            public void onError(Throwable throwable) {
                homedate.onError(throwable);
            }
        });
    }
}
