package com.ecaray.pda_xj;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.constant.RouterUri;
import com.ecaray.basicres.service.UpLoadLocService;
import com.ecaray.basicres.util.DialogUtils;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.router.facade.annotation.RouteNode;


/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */
//@RouteNode(path = RouterUri.MAIN_PATH, desc = "首页")
public class MainBaseActivity extends BaseActivity {

    private RelativeLayout mHomeSchedule;
    private RelativeLayout mHomeIllegal;
    private RelativeLayout mHomeEquip;
    private RelativeLayout mHomeBerth;
    private RelativeLayout mHomeSys;

    private void assignViews() {
        mHomeSchedule = findViewById(R.id.home_schedule);
        mHomeIllegal = findViewById(R.id.home_illegal);
        mHomeEquip = findViewById(R.id.home_equip);
        mHomeBerth = findViewById(R.id.home_berth);
        mHomeSys = findViewById(R.id.home_sys);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        startService(new Intent(this, UpLoadLocService.class));
    }

    @Override
    public void initView() {
        assignViews();
    }

    @Override
    public void setOnInteractListener() {
        //巡检调度
        mHomeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //违章上报
        mHomeIllegal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //设备维保
        mHomeEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //应急收费
        mHomeBerth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        //系统设置
        mHomeSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onBackPressed() {
        DialogUtils.showExitSystem(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑上传位置服务
        stopService();
    }

    private void stopService() {
        //停止上传位置
        stopService(new Intent(this, UpLoadLocService.class));
    }
}
