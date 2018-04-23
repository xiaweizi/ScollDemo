package com.xiaweizi.scrolldemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.scrolldemo.ContentFragment
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/04/23
 *     desc   :
 * </pre>
 */

public class ContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_content, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("data " + i);
        }
        adapter.setData(data);
        return view;
    }


}
