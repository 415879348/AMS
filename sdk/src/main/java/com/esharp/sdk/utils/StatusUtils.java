package com.esharp.sdk.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.esharp.sdk.Constant;
import com.esharp.sdk.R;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/7/12
 */
public final class StatusUtils {

    public static View getApplyStatusView(Context context, int status) {

        View statusView = null;

        //0待处理，1已通过，2已拒绝，3撤销
        switch (status) {
            case Constant.APPLY_STATUS_WAITING_HANDLE :
                statusView = LayoutInflater.from(context).inflate(R.layout.spsdk_view_status_approving, null, false);
                break;
            case Constant.APPLY_STATUS_PASSED :
                statusView = LayoutInflater.from(context).inflate(R.layout.spsdk_view_status_passed, null, false);
                break;
            case Constant.APPLY_STATUS_REFUSED :
                statusView = LayoutInflater.from(context).inflate(R.layout.spsdk_view_status_rejected, null, false);
                break;
            case Constant.APPLY_STATUS_CANCEL :
                statusView = LayoutInflater.from(context).inflate(R.layout.spsdk_view_status_revoke, null, false);
        }

        return statusView;
    }

}
