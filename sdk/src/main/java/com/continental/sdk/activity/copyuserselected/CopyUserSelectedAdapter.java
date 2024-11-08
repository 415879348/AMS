package com.continental.sdk.activity.copyuserselected;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.continental.sdk.R;
import com.continental.sdk.base.BaseAdapter;
import com.continental.sdk.bean.response.CopyUserListBean;
import com.continental.sdk.http.GlideUtils;

import androidx.annotation.NonNull;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class CopyUserSelectedAdapter extends BaseAdapter<CopyUserListBean, CopyUserSelectedAdapter.Listener> {

    public CopyUserSelectedAdapter(Listener onItemOperate) {
        super(onItemOperate);
    }

    @Override
    public void refreshItem(CopyUserListBean item) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mList.get(i).getCopyUserId().equals(item.getCopyUserId())) {
                notifyItemChanged(i);
                return;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public ViewHolder<CopyUserListBean, Listener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // recordType //100警報，101考勤 (目前只有考勤类型)
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_view_head, parent, false));
    }

    static class BaseViewHolder extends ViewHolder<CopyUserListBean, Listener> {

        private final ImageView iv_icon;
        private final TextView tv_title;

        BaseViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.title);
        }

        @Override
        protected void bindData(CopyUserListBean item, Listener onItemOperate) {
            GlideUtils.showImage(iv_icon, item.getHeadPortrait());
            tv_title.setText(item.getCopyUserName());
            itemView.setOnClickListener(v -> onItemOperate.onItemClick(item));
        }
    }

    interface Listener {
        void onItemClick(CopyUserListBean item);
    }
}
