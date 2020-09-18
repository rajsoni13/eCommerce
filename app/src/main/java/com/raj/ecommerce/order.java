package com.raj.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class order extends Fragment {

    GridView gridView;
    String[] strLang = {"Shirts","t-Shirts","pents","goggels","shoose","watch"};
    int imgData[] = { R.drawable.shirts, R.drawable.tshirts,
            R.drawable.pents, R.drawable.goggles, R.drawable.shoose, R.drawable.watch1};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.order, container, false);
        gridView=  myView.findViewById(R.id.gridview);

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        gridView.setAdapter(myBaseAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GridItemActivity.class);
                intent.putExtra("name",strLang[position]);
                intent.putExtra("image",imgData[position]);
                startActivity(intent);
            }
        });



        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("order");
    }

    private class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return imgData.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view1 = getLayoutInflater().inflate(R.layout.raw,null);
            TextView productname = view1.findViewById(R.id.raw_product);
            ImageView productimage = view1.findViewById(R.id.raw_img);

            productname.setText(strLang[position]);
            productimage.setImageResource(imgData[position]);

            return view1;
        }
    }
}
