package com.bawei.week2.View.adapterView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2.Model.bean.Base;
import com.bawei.week2.R;
import com.bawei.week2.netutil.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  14:52
 * 类名：com.bawei.week2.View.adapterView
 */
public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.Myviewhode> {

    private List<Base.ResultBean> result;

    public RViewAdapter(List<Base.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public Myviewhode onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.view, null);
        return new Myviewhode(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewhode holder, int position) {
        String commodityName = result.get(position).getCommodityName();
        String masterPic = result.get(position).getMasterPic();
            holder.name.setText(commodityName);
        NetUtil.getInstance().getPthr(masterPic,holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickItem.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class Myviewhode extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.name)
        TextView name;
        public Myviewhode(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnclickItem onclickItem;

    public void setOnclickItem(OnclickItem onclickItem) {
        this.onclickItem = onclickItem;
    }

    public interface  OnclickItem{
        void  onclick(int p);
    }

}
