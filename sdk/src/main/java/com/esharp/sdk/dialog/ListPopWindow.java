package com.esharp.sdk.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.esharp.sdk.R;
import com.esharp.sdk.base.BaseAdapter;
import com.esharp.sdk.base.ICallback;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 功能描述：
 *
 * @author (作者) someone
 * 创建时间： 2021/9/7
 */
public class ListPopWindow<T extends ListPopWindow.Item> {
    private final PopupWindow mPopupWindow;
    private final Adapter<T> adapter;

    @SuppressLint("ClickableViewAccessibility")
    public ListPopWindow(Context context, List<T> data, ICallback<T> callback) {
        RecyclerView view = (RecyclerView) View.inflate(context, R.layout.spsdk_pop_list, null);
        view.setBackgroundResource(R.drawable.spsdk_shape_grey_popbg);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchInterceptor((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                mPopupWindow.dismiss();
                return true;
            }
            return false;
        });
        adapter = new Adapter<T>(areaCode -> {
            mPopupWindow.dismiss();
            callback.exec(areaCode);
        });
        adapter.refreshAllItems(data);
        view.setAdapter(adapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    public ListPopWindow(View anchor, Context context, List<T> data, ICallback<T> callback) {
        RecyclerView view = (RecyclerView) View.inflate(context, R.layout.spsdk_pop_list, null);
        view.setBackgroundResource(R.drawable.spsdk_shape_grey_popbg);
        mPopupWindow = new PopupWindow(view, anchor.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchInterceptor((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                mPopupWindow.dismiss();
                return true;
            }
            return false;
        });
        adapter = new Adapter<T>(areaCode -> {
            mPopupWindow.dismiss();
            callback.exec(areaCode);
        });
        adapter.refreshAllItems(data);
        view.setAdapter(adapter);
    }

    public void refreshAllItems(List<T> data) {
        adapter.refreshAllItems(data);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void show(View anchor, String checkedCode) {
        mPopupWindow.showAsDropDown(anchor);
        for (T item : adapter.getData()) {
            item.setChecked(checkedCode);
        }
        adapter.notifyDataSetChanged();
    }

    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    static class Adapter<T extends Item> extends BaseAdapter<T, ICallback<T>> {

        public Adapter(ICallback<T> onItemOperate) {
            super(onItemOperate);
        }

        @NonNull
        @Override
        public ViewHolder<T, ICallback<T>> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListPopWindow.ViewHolder<>(LayoutInflater.from(parent.getContext()).inflate(R.layout.spsdk_item_text_view, parent, false));
        }
    }

    static class ViewHolder<T extends Item> extends BaseAdapter.ViewHolder<T, ICallback<T>> {
        private final TextView mTextView;
        private final int padding;

        public ViewHolder(View itemView) {
            super(itemView);
            padding =  AdaptScreenUtils.pt2Px( 5);
            mTextView = itemView.findViewById(R.id.text_item);
        }

        @Override
        protected void bindData(T item, ICallback<T> onItemOperate) {
            mTextView.setText(item.text());
            itemView.setOnClickListener(v -> onItemOperate.exec(item));
            mTextView.setTextColor(ContextCompat.getColor(mContext, item.isChecked() ? R.color.spsdk_color_blue : R.color.spsdk_main_color));
            mTextView.setPadding(padding, padding, padding, padding);
            itemView.setBackgroundColor(ContextCompat.getColor(mContext, getAbsoluteAdapterPosition() % 2 == 0 ? R.color.spsdk_transparent_00 : R.color.spsdk_transparent_10));
        }
    }

    public abstract static class Item {
        protected boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public abstract void setChecked(String checkedCode);

        public abstract String text();
    }
}
