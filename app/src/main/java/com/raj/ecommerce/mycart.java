package com.raj.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class mycart extends Fragment {

    ListView listView;
    String[] strLang = {"Shirts","t-Shirts","pents","goggels","shoose","watch"};
    int imgData[] = { R.drawable.shirts, R.drawable.tshirts,
            R.drawable.pents, R.drawable.goggles, R.drawable.shoose, R.drawable.watch1};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.mycart, container, false);
        listView=  myView.findViewById(R.id.listview);

        mycart.MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ListViewItem.class);
                intent.putExtra("namelist",strLang[position])   ;
                intent.putExtra("imagelist",imgData[position]);
                startActivity(intent);
            }
        });
        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("home");
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

            View view1 = getLayoutInflater().inflate(R.layout.list_raw,null);
            TextView productname = view1.findViewById(R.id.raw_list_product);
            ImageView productimage = view1.findViewById(R.id.raw_list_img);

            productname.setText(strLang[position]);
            productimage.setImageResource(imgData[position]);

            return view1;
        }
    }

}
