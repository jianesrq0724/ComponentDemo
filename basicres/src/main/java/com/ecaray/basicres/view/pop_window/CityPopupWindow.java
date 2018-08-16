package com.ecaray.basicres.view.pop_window;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecaray.basicres.R;


/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/5/7 15:04
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/5/7 15:04
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class CityPopupWindow {

    public PopupWindow mCityPop;
    private OnSelectCity mOnSecectCallback;
    private Context mContext;

    public CityPopupWindow(Context context, OnSelectCity callback) {
        mContext = context;
        mOnSecectCallback = callback;
        initCitySelector();
    }

    //初始化城市选择控件
    private void initCitySelector() {
        if (mCityPop == null) {
            View view = View.inflate(mContext, R.layout.pub_pop_city_name, null);
            TextView tv_none_num = (TextView) view.findViewById(R.id.tv_none_num);
            tv_none_num.setOnClickListener(v -> mOnSecectCallback.getCityFromClick(""));
            GridView gvCityName = (GridView) view
                    .findViewById(R.id.gv_city_name_container);
            gvCityName.setOnItemClickListener((parent, view1, position, id) -> {

                mOnSecectCallback.getCityFromClick(((TextView) view1).getText().toString().trim());

            });
            CityNameAdapter cityNameAdapter = new CityNameAdapter(mContext);
            cityNameAdapter
                    .setOnTextItemOnClickListener(city ->
                            mOnSecectCallback.getCityFromClick(((TextView) city).getText().toString()
                            .trim()));
            gvCityName.setAdapter(cityNameAdapter);
            mCityPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mCityPop.setBackgroundDrawable(new BitmapDrawable());
            mCityPop.setFocusable(true);
        }
    }

    public PopupWindow getmCityPop(){
        return mCityPop;
    }

    public interface OnSelectCity{
         void getCityFromClick(String city);
    }

}
