package com.esharp.ams.dialog;

import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.esharp.ams.R;
import com.esharp.ams.ui.WorkOrderDetailActivity;
import com.esharp.sdk.bean.response.HandlerVo;
import com.esharp.sdk.bean.response.UserVo;
import com.esharp.sdk.dialog.BaseAlertDialog;
import com.esharp.sdk.dialog.ListPopWindow;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;
import com.esharp.sdk.utils.ResUtils;
import com.esharp.sdk.widget.MyTextView;
import com.esharp.sdk.widget.OnClickCallback;
import com.esharp.sdk.widget.SPNoteView;
import com.esharp.sdk.widget.SPSelectorView;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 功能描述：添加申请
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class WorkOrderHandleDialog extends BaseAlertDialog {
    private ImageView iv_cancel;
    private SPNoteView nv_remark;
    private SPSelectorView sv_selector;
    private MyTextView mv_end;
    private MyTextView mv_assign;
    private ListPopWindow<UserVo> selectorPopWindow;
    private List<UserVo> userList;

    public WorkOrderHandleDialog(@NonNull WorkOrderDetailActivity mView) {
        super(mView, R.layout.dialog_work_order_handle);
        iv_cancel = findViewById(R.id.iv_cancel);
        nv_remark = findViewById(R.id.nv_remark);
        sv_selector = findViewById(R.id.sv_selector);
        mv_end = findViewById(R.id.mv_end);
        mv_assign = findViewById(R.id.mv_assign);
        iv_cancel.setOnClickListener(v -> {
            cancel();
        });


        HttpService.get()
                .userAll()
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(it -> {
                    userList = it;
                    if (userList.size() > 0) {
                        sv_selector.setTag(userList.get(0));

                        selectorPopWindow = new ListPopWindow<>(nv_remark, getContext(), userList, userVo -> {
                            LogUtils.json(userVo);
                            sv_selector.setTag(userVo);
                            sv_selector.setContent(userVo.getUsername());
                        });

                        sv_selector.setContent(userList.get(0).getUsername());
                    }
                });
    }

    public BaseAlertDialog setOnClickListener(OnClickCallback<HandlerVo> it, OnClickCallback<HandlerVo> it2) {
        mv_end.setOnClickListener(v-> {
            HandlerVo vo = new HandlerVo();
            vo.setContent(nv_remark.getContent());
            vo.setIsOver(0);
            it.onClick(vo);
        });
        mv_assign.setOnClickListener(v-> {

            if (sv_selector.getVisibility() == View.GONE) {
                sv_selector.setVisibility(View.VISIBLE);
                mv_assign.setText(ResUtils.getString(R.string.confirm));

                sv_selector.setOnClickListener(view -> {
                    UserVo bean = (UserVo) sv_selector.getTag();
                    selectorPopWindow.show(sv_selector, bean.getUsername());
                });

            } else {
                UserVo bean = (UserVo) sv_selector.getTag();

                HandlerVo vo = new HandlerVo();
                vo.setContent(nv_remark.getContent());
                vo.setIsOver(1);
                vo.setProcessId(bean.getId());
                it2.onClick(vo);
            }

        });
        return this;
    }

}
