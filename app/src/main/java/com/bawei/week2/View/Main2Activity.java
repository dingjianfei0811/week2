package com.bawei.week2.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week2.Bean.BeanAppivity;
import com.bawei.week2.Bean.BeanPresenter;
import com.bawei.week2.Model.bean.Beanssss;
import com.bawei.week2.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BeanAppivity {

    @BindView(R.id.tt)
    TextView tt;
    @BindView(R.id.but1)
    Button but1;
    @BindView(R.id.but2)
    Button but2;
    @BindView(R.id.bimg)
    ImageView bimg;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        CodeUtils.init(this);
        bimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(bimg, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, "成功————"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "——失败——", Toast.LENGTH_SHORT).show();

                    }
                });

                return true;
            }
        });


    }

    @Override
    protected BeanPresenter priPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }



    @OnClick({R.id.tt, R.id.but1, R.id.but2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tt:
                String s = tt.getText().toString();
                Bitmap image = CodeUtils.createImage(s, 200, 200, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                bimg.setImageBitmap(image);
                break;
            case R.id.but1:
                EventBus.getDefault().post("传值");
                break;
            case R.id.but2:
                EventBus.getDefault().post(new Beanssss(1,"bean传值"));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  a(String name){
        Toast.makeText(this, "点击了"+name, Toast.LENGTH_SHORT).show();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  aa(Beanssss beanssss){
        Toast.makeText(this, "点击了"+beanssss.s+beanssss.i, Toast.LENGTH_SHORT).show();

    }
}
