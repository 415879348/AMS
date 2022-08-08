package com.esharp.sdk.activity.approverList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseAdapter;
import com.esharp.sdk.bean.response.StaffVo;
import com.esharp.sdk.http.GlideUtils;
import com.esharp.sdk.widget.MyTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 *
 * @author (作者) someone
 * 创建时间： 2021/8/7
 */
public class ReviewerAdapter extends BaseAdapter<StaffVo, ReviewerAdapter.Listener> {

    public ReviewerAdapter(Listener onItemOperate) {
        super(onItemOperate);
    }

//    @Override
//    public void refreshItem(StaffVo item) {
//        for (int i = 0; i < getItemCount(); i++) {
//            if (mList.get(i).getUserId() == item.getUserId()) {
//                notifyItemChanged(i);
//                return;
//            }
//        }
//    }

    public List<StaffVo> getSelectedData() {
        List<StaffVo> tempList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).isCheck()) {
                tempList.add(mList.get(i));
            }
        }
        return tempList;
    }


    @NonNull
    @Override
    public ViewHolder<StaffVo, Listener> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_approver, parent, false));
    }

    class BaseViewHolder extends ViewHolder<StaffVo, Listener> {
        private final MyTextView mv_approver_name, mv_approver_position;
        private final ImageView iv_head;
        private final CheckBox cb_checkbox;

        BaseViewHolder(View itemView) {
            super(itemView);
            cb_checkbox = itemView.findViewById(R.id.cb_checkbox);
            mv_approver_name = itemView.findViewById(R.id.mv_approver_name);
            mv_approver_position = itemView.findViewById(R.id.mv_approver_position);
            iv_head = itemView.findViewById(R.id.iv_head);
        }

        @Override
        protected void bindData(StaffVo item, Listener onItemOperate) {
            cb_checkbox.setChecked(item.isCheck());
            mv_approver_name.setText(item.getUsername());
            mv_approver_position.setText(item.getDepartmentName());
            GlideUtils.showImage(iv_head, item.getImageUrl());

            cb_checkbox.setEnabled(false);
            cb_checkbox.setClickable(false);

            itemView.setOnClickListener(v -> {

                if (getSelectedData().size() >= 10 && !cb_checkbox.isChecked()) {
                    onItemOperate.onMoreThan();
                    return;
                }

                cb_checkbox.setChecked(! cb_checkbox.isChecked());

                item.setCheck(cb_checkbox.isChecked());

                int count = getSelectedData().size();

                onItemOperate.selected(count);
            });
        }
    }

    void selectAll() {
        unSelectAll();

        for (int i = 0; i < mList.size(); i++) {
            if (i == 10) {
                break;
            }
            mList.get(i).setCheck(true);
        }
    }

    void unSelectAll() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).setCheck(false);
        }
    }

    interface Listener {
        void onMoreThan();
        void selected(int count);
    }
}
