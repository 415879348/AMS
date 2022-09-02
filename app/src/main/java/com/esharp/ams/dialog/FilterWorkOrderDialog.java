package com.esharp.ams.dialog;

import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.ui.MainActivity;
import com.esharp.sdk.bean.response.StepVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.bean.response.WorkOrderTypeVo;
import com.esharp.sdk.dialog.BaseAlertDialog;
import com.esharp.sdk.dialog.ListPopWindow;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;
import com.esharp.sdk.utils.DateTimeUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.DateTimeSelector;
import com.esharp.sdk.widget.OnClickCallback;
import com.esharp.sdk.widget.SPCardEditView;
import com.esharp.sdk.widget.SPSelectorCardView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * 功能描述：工单筛选
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class FilterWorkOrderDialog extends BaseAlertDialog {

    private final SPSelectorCardView scv_type, scv_handler, scv_progress, scv_date_from, scv_date_to;

    private final ImageView iv_search;

    private final SPCardEditView cev_job_number_name;

    private ListPopWindow<WorkOrderTypeVo> typePop;

    private ListPopWindow<StepVo> stepPop;

    private ListPopWindow<UserVo> handlerPop;

    private List<WorkOrderTypeVo> typeList = new ArrayList<>();

    private List<StepVo> stepList = new ArrayList<>();

    public FilterWorkOrderDialog(@NonNull MainActivity mView) {
        super(mView, R.layout.dialog_filter_work_order);

//      -1：撤销(暂无)  0：结束  1：申请  2：审批(暂无)  3：处理
        StepVo p1 = new StepVo();
        p1.setId("-1");
        p1.setProgressName("撤销");

        StepVo p2 = new StepVo();
        p2.setId("0");
        p2.setProgressName("结束");

        StepVo p3 = new StepVo();
        p3.setId("1");
        p3.setProgressName("申请");

        StepVo p4 = new StepVo();
        p4.setId("2");
        p4.setProgressName("审批");

        StepVo p5 = new StepVo();
        p5.setId("3");
        p5.setProgressName("处理");

        stepList.add(p1);
        stepList.add(p2);
        stepList.add(p3);
        stepList.add(p4);
        stepList.add(p5);

        WorkOrderTypeVo vo1 = new WorkOrderTypeVo();
        vo1.setTypeId("1");
        vo1.setTypeName(ResUtils.getString(R.string.job_fault));

        WorkOrderTypeVo vo2 = new WorkOrderTypeVo();
        vo2.setTypeId("2");
        vo2.setTypeName(ResUtils.getString(R.string.job_maintain));

        typeList.add(vo1);
        typeList.add(vo2);

        cev_job_number_name = findViewById(R.id.cev_job_number_name);
        scv_handler = findViewById(R.id.scv_handler);
        scv_type = findViewById(R.id.scv_type);
        scv_progress = findViewById(R.id.scv_progress);

        scv_date_from = findViewById(R.id.scv_date_from);
        scv_date_to = findViewById(R.id.scv_date_to);

        iv_search = findViewById(R.id.iv_search);

        findViewById(R.id.iv_close).setOnClickListener(v -> {
            cancel();
        });

        scv_type.setOnItemClick(v -> {
            WorkOrderTypeVo vo = (WorkOrderTypeVo) v.getTag();
            if (vo == null) {
                typePop.show(scv_type, "");
            } else {
                typePop.show(scv_type, vo.getTypeId());
            }
        });

        scv_handler.setOnItemClick(v -> {
            UserVo vo = (UserVo) v.getTag();
            if (vo == null) {
                handlerPop.show(scv_handler, "");
            } else {
                handlerPop.show(scv_handler, vo.getId());
            }
        });

        scv_progress.setOnItemClick(v -> {
            UserVo vo = (UserVo) v.getTag();
            if (vo == null) {
                stepPop.show(scv_progress, "");
            } else {
                handlerPop.show(scv_progress, vo.getId());
            }
        });

        scv_date_from.setOnItemClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                scv_date_from.setContent(datetime);
            }, DateTimeUtils.parseToLong(scv_date_to.getContent()), null);
        });

        scv_date_to.setOnItemClick(v -> {
            new DateTimeSelector(v.getContext(), datetime -> {
                LogUtils.json(datetime);
                scv_date_to.setContent(datetime);
            }, null, DateTimeUtils.parseToLong(scv_date_from.getContent()));
        });

        HttpService.get().userAll()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(it -> {
                    handlerPop = new ListPopWindow<>(scv_handler, mView, it, vo -> {
                        LogUtils.json(vo);
                        scv_handler.setTag(vo);
                        scv_handler.setContent(vo.getUsername());
                    });

                    typePop = new ListPopWindow<>(scv_type, mView, typeList, vo -> {
                        LogUtils.json(vo);
                        scv_type.setTag(vo);
                        scv_type.setContent(vo.getTypeName());
                    });

                    stepPop = new ListPopWindow<>(scv_progress, mView, stepList, vo -> {
                        LogUtils.json(vo);
                        scv_progress.setTag(vo);
                        scv_progress.setContent(vo.getProgressName());
                    });
                });
    }

    public BaseAlertDialog setOnClickListener(OnClickCallback<Map<String, String>> it) {
        iv_search.setOnClickListener(v -> {

            String titleOrNumber = "";
            String handlerId = "";
            String type = "";
            String step = "";
            String startTime = "";
            String endTime = "";

            titleOrNumber = cev_job_number_name.getContent();

            UserVo userVo = (UserVo) scv_handler.getTag();
            if (userVo != null) {
                handlerId = userVo.getId();
            }

            WorkOrderTypeVo workOrderTypeVo = (WorkOrderTypeVo) scv_type.getTag();
            if (workOrderTypeVo != null) {
                type = workOrderTypeVo.getTypeId();
            }

            StepVo stepVo = (StepVo) scv_progress.getTag();
            if (stepVo != null) {
                step = stepVo.getId();
            }

            String fromDate = scv_date_from.getContent();
            String toDate = scv_date_to.getContent();
            if (! TextUtils.isEmpty(fromDate)) {
                startTime = DateTimeUtils.parseToLong(fromDate)+"";
            }

            if (! TextUtils.isEmpty(toDate)) {
                endTime = DateTimeUtils.parseToLong(toDate)+"";
            }

            Map<String, String> map = new HashMap<>();

            map.put("titleOrNumber", titleOrNumber);
            map.put("handlerId", handlerId);
            map.put("type", type);
            map.put("step", step);
            map.put("startTime", startTime);
            map.put("endTime", endTime);

            LogUtils.json(map);
            it.onClick(map);
            cancel();
        });
        return this;
    }

}
