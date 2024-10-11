package com.example.travelactivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AadapterGetAllCategoryDetails extends BaseAdapter {


    List<POJOGetAllCategoryDetails> pojoGetAllCategoryDetails;
    Activity activity;

    public AadapterGetAllCategoryDetails(List<POJOGetAllCategoryDetails> list, Activity activity) {
        this.pojoGetAllCategoryDetails = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pojoGetAllCategoryDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return pojoGetAllCategoryDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.lv_get_all_category, null);
            holder.ivCategoryImage = view.findViewById(R.id.ivcategoryimage);
            holder.tvCategoryName = view.findViewById(R.id.tvCategoryName);
            holder.cvCategoryList = view.findViewById(R.id.cvCategoryList);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }

        final POJOGetAllCategoryDetails obj = pojoGetAllCategoryDetails.get(position);
        holder.tvCategoryName.setText(obj.getCategoryName());

        Glide.with(activity)
                .load("http://192.168.0.107:80/TravelAPI/images/" + obj.getCategoryImage())
                .skipMemoryCache(true)
                .error(R.drawable.image_not_found)
                .into(holder.ivCategoryImage);

        holder.cvCategoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, CategorywisePlaceActivity.class);
                i.putExtra("categoryname", obj.getCategoryName());
                activity.startActivity(i);

            }
        });


        return view;
    }

    class ViewHolder {
        ImageView ivCategoryImage;
        TextView tvCategoryName;
        CardView cvCategoryList;
    }


    //baseadapter = multiple view load show
    //AadapterGetAllCategoryDetails show multiple view collect show listview


}
