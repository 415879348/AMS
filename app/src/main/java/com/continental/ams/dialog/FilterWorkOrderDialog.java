package com.continental.ams.dialog;

import android.text.TextUtils;
import android.widget.ImageView;
import com.continental.ams.R;
import com.blankj.utilcode.util.LogUtils;

import com.continental.ams.ui.MainActivity;
import com.continental.sdk.bean.response.DictionaryBean;
import com.continental.sdk.bean.response.StepVo;
import com.continental.sdk.bean.response.UserVo;
import com.continental.sdk.bean.response.WorkOrderTypeVo;
import com.continental.sdk.dialog.BaseAlertDialog;
import com.continental.sdk.dialog.ListPopWindow;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;
import com.continental.sdk.utils.DateTimeUtils;
import com.continental.sdk.utils.ResUtils;
import com.continental.sdk.widget.DateTimeSelector;
import com.continental.sdk.widget.OnClickCallback;
import com.continental.sdk.widget.SPCardEditView;
import com.continental.sdk.widget.SPSelectorCardView;

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

    private ListPopWindow<StepVo> stepPop;

    private ListPopWindow<UserVo> handlerPop;

    private List<WorkOrderTypeVo> typeList = new ArrayList<>();

    private List<StepVo> stepList = new ArrayList<>();

    private ListPopWindow<DictionaryBean> typePop;

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
            DictionaryBean vo = (DictionaryBean) v.getTag();
            if (vo == null) {
                typePop.show(scv_type, "");
            } else {
                typePop.show(scv_type, vo.getId());
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
                    stepPop = new ListPopWindow<>(scv_progress, mView, stepList, vo -> {
                        LogUtils.json(vo);
                        scv_progress.setTag(vo);
                        scv_progress.setContent(vo.getProgressName());
                    });
                });

        Map<String, String> map = new HashMap<>();
        map.put("dictType", "4");
        HttpService.get().dictAll(map)
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(it -> {
                    typePop = new ListPopWindow<>(scv_type, mView, it, vo -> {
                        LogUtils.json(vo);
                        scv_type.setTag(vo);
                        scv_type.setContent(vo.getDictName());
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
