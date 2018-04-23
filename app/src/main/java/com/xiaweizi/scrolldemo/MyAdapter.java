package com.xiaweizi.scrolldemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.scrolldemo.MyAdapter
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/04/23
 *     desc   :
 * </pre>
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData;

    public MyAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    private @LayoutRes
    int getItemLayout() {
        // TODO: add item layout 
        return R.layout.item;
    }

    public void setData(List<String> data) {
        if (data != null) {
            this.mData = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getItemLayout(), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private RelativeLayout parent;
        ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            parent = itemView.findViewById(R.id.parent);

        }

        void bind(final String bean) {
            tvContent.setText(bean);
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), bean, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}