package com.ecaray.basicres.view.pop_window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.ecaray.basicres.R;

import java.util.ArrayList;

/**
 * 类描述:
 * 创建人: Eric_Huang
 * 创建时间: 2017/5/16 15:51
 */
public class PwAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mDataList;
    private int mChoosePos;

    public PwAdapter(Context context,
                     ArrayList<String> dataList, int choosePos) {
        mContext = context;
        mDataList = dataList;
        mChoosePos = choosePos;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String lCarTypeName = mDataList.get(position);
        PwAdapter.ViewHolder lViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.pub_item_pop,null);
            lViewHolder = new PwAdapter.ViewHolder();
            lViewHolder.RoadIcon = convertView.findViewById(R.id.choose_icon_apply_tv);
            lViewHolder.RoadName = convertView.findViewById(R.id.text_name_apply_tv);
            convertView.setTag(lViewHolder);
        }else{
            lViewHolder = (PwAdapter.ViewHolder) convertView.getTag();
        }

        if(mChoosePos == position){
            lViewHolder.RoadIcon.setVisibility(View.VISIBLE);
            lViewHolder.RoadName.setText(lCarTypeName);
            lViewHolder.RoadName.setTextColor(mContext.getResources().getColor(R.color.main_theme));
        }else{
            lViewHolder.RoadIcon.setVisibility(View.INVISIBLE);
            lViewHolder.RoadName.setText(lCarTypeName);
            lViewHolder.RoadName.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        return convertView;
    }

    class ViewHolder{
        TextView RoadIcon;
        TextView RoadName;
    }

}
