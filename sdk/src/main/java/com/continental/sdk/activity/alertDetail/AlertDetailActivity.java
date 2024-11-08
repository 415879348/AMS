package com.continental.sdk.activity.alertDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.continental.sdk.R;
import com.continental.sdk.base.BaseMvpActivity;
import com.continental.sdk.bean.response.AlertDetailVo;
import com.continental.sdk.bean.response.AlertVo;
import com.continental.sdk.http.GlideUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.SPShowTextView;
import com.continental.sdk.widget.SPTitleView;

public class AlertDetailActivity extends BaseMvpActivity<AlertDetailContract.Presenter> implements AlertDetailContract.View {



    @Override
    protected Pair<Integer, AlertDetailContract.Presenter> layoutAndPresenter() {
        return Pair.create(R.layout.spsdk_activity_alert_detail, new AlertDetailPresenter(this));
    }

    public static void startActivity(Context context, AlertVo.RecordsBean it) {
        Intent intent = new Intent(context, AlertDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("RecordsBean", it);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private AlertVo.RecordsBean recordsBean = null;
    private SPShowTextView tv_staff = null;
    private SPShowTextView tv_department = null;
    private SPShowTextView tv_date = null;
    private SPShowTextView tv_temperature = null;
    private ImageView tv_icon = null;


    @Override
    protected void init() {
        ((SPTitleView)findViewById(R.id.title_view)).setTitle(ResUtils.getString(R.string.spsdk_alarm_details));

        tv_staff = findViewById(R.id.tv_staff);
        tv_department = findViewById(R.id.tv_department);
        tv_date = findViewById(R.id.tv_date);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_icon = findViewById(R.id.iv_icon);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        if (bundle.containsKey("RecordsBean")) {
            recordsBean = (AlertVo.RecordsBean) bundle.getSerializable("RecordsBean");
        }
        LogUtils.json(recordsBean);

        mPresenter.getAlertDetailById(recordsBean.getId());
    }

    @Override
    public void onGetAlertDetailSuccess(AlertDetailVo it) {

        LogUtils.json(it);
        tv_staff.setDetail(it.getUserName());
        tv_department.setDetail(it.getDepartmentName());
        tv_date.setDetail(it.getIdentificationTime());
        tv_temperature.setDetail(it.getTemperature());
        tv_temperature.setDetailColour(ResUtils.getColor(R.color.spsdk_color_red));
        GlideUtils.showImage(tv_icon, it.getFilePath());
    }
}
