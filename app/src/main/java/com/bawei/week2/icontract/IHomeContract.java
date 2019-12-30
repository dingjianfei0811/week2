package com.bawei.week2.icontract;

import com.bawei.week2.Model.bean.Base;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  14:09
 * 类名：com.bawei.week2.icontract
 */
public interface IHomeContract {
        interface  IView{
      void  onBean(Base base);
      void  onError(Throwable throwable);
      }
      interface  IPresenter{
            void  getModedate();
      }
      interface  IModel{
          void  getModedate(Homedate homedate);
          interface  Homedate{
              void  onBean(Base base);
              void  onError(Throwable throwable);
          }
      }
}
