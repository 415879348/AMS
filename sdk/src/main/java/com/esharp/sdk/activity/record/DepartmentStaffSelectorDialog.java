package com.esharp.sdk.activity.record;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.bean.response.DepartmentVo;
import com.esharp.sdk.bean.response.RecordFilterVo;
import com.esharp.sdk.dialog.BaseAlertDialog;
import com.esharp.sdk.dialog.ListPopWindow;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.SchedulerUtils;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.widget.DateTimeSelector;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.SPEditView;
import com.esharp.sdk.widget.SPSelectorView;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 功能描述：选择部门职员进行筛选
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class DepartmentStaffSelectorDialog extends BaseAlertDialog {
    private LinearLayout container;

    private SPSelectorView ssv_department, ssv_staff;
    private MyTextView mv_cancel, mv_commit;
    private SPEditView ev_date;
    private CheckBox cb_temperature;
    private Listener mListener;
    private ListPopWindow<DepartmentVo> departmentPopWindow;
    private ListPopWindow<DepartmentVo.UserBaseBean> staffPopWindow;

    public DepartmentStaffSelectorDialog(@NonNull Context context, Listener listener) {
        super(context, R.layout.spsdk_dialog_department_staff_selection);

//        container = findViewById(R.id.container);

//        FrameLayout.LayoutParams linearParams =(FrameLayout.LayoutParams) container.getLayoutParams();

//        linearParams.height = ScreenUtils.getScreenHeight() / 3;

//        container.setLayoutParams(linearParams);

        mListener = listener;

        ssv_department = findViewById(R.id.ssv_department);

        ssv_staff = findViewById(R.id.ssv_staff);

        ev_date = findViewById(R.id.ev_date);
        ev_date.setContent(DateTimeUtils.millis2Date(DateTimeUtils.getForwardTimeMillis(System.currentTimeMillis(), -1)));

        cb_temperature = findViewById(R.id.cb_temperature);

        mv_cancel = findViewById(R.id.mv_cancel);

        mv_commit = findViewById(R.id.mv_commit);

        mv_cancel.setOnClickListener(v -> dismiss());

        mv_commit.setOnClickListener(v -> {
            DepartmentVo bean = (DepartmentVo) ssv_department.getTag();
            if (bean == null) {
                return;
            }
            DepartmentVo.UserBaseBean userBaseBean = (DepartmentVo.UserBaseBean) ssv_staff.getTag();
            if (userBaseBean == null) {
                return;
            }
            if (StringUtils.isTrimEmpty(ev_date.getContent())) {
                return;
            }

            RecordFilterVo vo = new RecordFilterVo();
            vo.setStartTime(DateTimeUtils.parseToLong(ev_date.getContent()));
            vo.setEndTime(System.currentTimeMillis());
            vo.setDeptIds(bean.getDepartmentId()+"");
            vo.setUserIds(userBaseBean.getUserId()+"");
            vo.setOverTemperature(cb_temperature.isChecked()? 1 : 0);

            mListener.onCommit(vo);

            dismiss();
        });

        ev_date.setRightClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                ev_date.setContent(datetime);
            });
        });

        HttpService.get()
                .departmentList()
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(departmentVos -> {

                    LogUtils.json(departmentVos);
                    if (departmentVos.size() == 0) {
                        return;
                    }

                    DepartmentVo department = departmentVos.get(0);
                    ssv_department.setTag(department);
                    ssv_department.setContent(department.getDepartmentName());
                    ssv_department.setOnClickListener(v -> {
                        DepartmentVo bean = (DepartmentVo) ssv_department.getTag();
                        departmentPopWindow.show(ssv_department, bean.getDepartmentId()+"");
                    });

                    List<DepartmentVo.UserBaseBean> userBaseList = department.getUserBaseList();
                    if (userBaseList.size() > 0) {
                        ssv_staff.setTag(userBaseList.get(0));
                        ssv_staff.setContent(userBaseList.get(0).getUserName());
                        ssv_staff.setOnClickListener(v -> {
                            if (ssv_staff.getTag() != null) {
                                DepartmentVo.UserBaseBean bean = (DepartmentVo.UserBaseBean) ssv_staff.getTag();
                                staffPopWindow.show(ssv_staff, bean.getUserId()+"");
                            }
                        });
                    }

                    departmentPopWindow = new ListPopWindow<>(getContext(), departmentVos, departmentVo -> {
                        LogUtils.json(departmentVo);
                        ssv_department.setTag(departmentVo);
                        ssv_department.setContent(departmentVo.getDepartmentName());
                        List<DepartmentVo.UserBaseBean> userBaseList2 = departmentVo.getUserBaseList();
                        if (userBaseList2.size() > 0) {
                            ssv_staff.setTag(userBaseList2.get(0));
                            ssv_staff.setContent(userBaseList2.get(0).getUserName());
                            refreshAllItems(departmentVo.getUserBaseList());
                        } else {
                            ssv_staff.setTag(null);
                            ssv_staff.setContent("");
                        }
                    });

                    staffPopWindow = new ListPopWindow<>(getContext(), department.getUserBaseList(), userBaseBean -> {
                        LogUtils.json(userBaseBean);
                        ssv_staff.setTag(userBaseBean);
                        ssv_staff.setContent(userBaseBean.getUserName());
                    });

                }, LogUtils::json);
    }


    private void refreshAllItems(List<DepartmentVo.UserBaseBean> data) {
        staffPopWindow.refreshAllItems(data);
    }

    interface Listener { // 要同一个包下面才能引用
        void onCommit(RecordFilterVo item);
    }

}
