package com.example.bjcolor.myapplication.adapter;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bjcolor.myapplication.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;


public class TwoFragmentAdapter extends RecyclerArrayAdapter<String> {


    public TwoFragmentAdapter(Context context, String[] objects) {
        super(context, objects);
    }

    public TwoFragmentAdapter(Context context, List<String> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(parent);
    }

    public class MyViewHolder extends BaseViewHolder<String> {


        TextView textView;

        public MyViewHolder(ViewGroup itemView) {
            super(itemView, R.layout.item_textview);
            textView = $(R.id.list_item);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            textView.setText(data+"");
        }
    }
}
