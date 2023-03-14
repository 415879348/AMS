package com.esharp.sdk.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.esharp.sdk.R;
import com.esharp.sdk.utils.ResUtils;

/**
 *@author Albener
 *2020-01-19
 */
public class CustomDialogBuilder implements DialogBuilder {

    private Context context;

    private String title = "";

    private CharSequence message = "";

    private boolean isCancelable = false;

    private boolean isCancelRed = false;

    private boolean isConfirmRed = false;

    private String confirm_btnText = "";

    private String cancel_btnText = "";

    private int type = WindowManager.LayoutParams.TYPE_APPLICATION;

    private DialogInterface.OnClickListener confirm_btnClickListener = null;

    private DialogInterface.OnClickListener cancel_btnClickListener = null;

    public CustomDialogBuilder(Context context) {
        this.context = context;
    }

    public CustomDialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomDialogBuilder setMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            this.message = message.replace("\\n\\n", "\n");
        }
        return this;
    }

    public CustomDialogBuilder setMessage(int messageId) {
        this.message = ResUtils.getString(messageId);
        return this;
    }

    public CustomDialogBuilder setMessage(SpannableString sp) {
        this.message = sp;
        return this;
    }

    public CustomDialogBuilder setType(int type) {
        this.type = type;
        return this;
    }

    public CustomDialogBuilder setPositiveButton(int confirm_btnText) {
        return setPositiveButton(confirm_btnText, null);
    }

    public CustomDialogBuilder setPositiveButton(DialogInterface.OnClickListener listener) {
        return setPositiveButton(R.string.ensureemailok, listener);
    }

    public CustomDialogBuilder  setPositiveButton(int confirm_btnText, DialogInterface.OnClickListener listener) {
        return setPositiveButton(confirm_btnText, listener, false);
    }

    public CustomDialogBuilder setPositiveButton(int confirm_btnText, DialogInterface.OnClickListener listener, Boolean isRed) {
        this.confirm_btnText = (String) context.getText(confirm_btnText);
        this.confirm_btnClickListener = listener;
        this.isConfirmRed = isRed;
        return this;
    }

    public CustomDialogBuilder setNegativeButton(DialogInterface.OnClickListener listener) {
        return setNegativeButton(R.string.spsdk_cancel, listener);
    }

    public CustomDialogBuilder  setNegativeButton(int cancel_btnText, DialogInterface.OnClickListener listener) {
        return setNegativeButton(cancel_btnText, listener, false);
    }

    public CustomDialogBuilder  setNegativeButton(int cancel_btnText, DialogInterface.OnClickListener listener, Boolean isRed) {
        this.cancel_btnText = (String) context.getText(cancel_btnText);
        this.cancel_btnClickListener = listener;
        this.isCancelRed = isRed;
        return this;
    }

    public CustomDialogBuilder isCancelable(Boolean b) {
        this.isCancelable = b;
        return this;
    }

    @Override
    public CustomDialog create() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CustomDialog dialog = new CustomDialog(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.spsdk_dialog_custom, null);
        dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        TextView confirm = layout.findViewById(R.id.confirm_btn);
        TextView cancel = layout.findViewById(R.id.cancel_btn);

        confirm.setTextColor(ResUtils.getColor(R.color.spsdk_main_color));
        cancel.setTextColor(ResUtils.getColor(R.color.spsdk_main_color));

        if (!TextUtils.isEmpty(confirm_btnText)) {
            confirm.setText(confirm_btnText);
            if (confirm_btnClickListener != null) {
                confirm.setOnClickListener(v -> confirm_btnClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE));
            } else {
                confirm.setOnClickListener(v -> dialog.dismiss());
            }

            if (isConfirmRed) {
                confirm.setTextColor(ResUtils.getColor(R.color.spsdk_color_red));
            }
        } else {
            confirm.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(cancel_btnText)) {
            cancel.setText(cancel_btnText);
            if (cancel_btnClickListener != null) {
                cancel.setOnClickListener(v -> cancel_btnClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE));
            } else {
                cancel.setOnClickListener(v -> dialog.dismiss());
            }

            if (isCancelRed) {
                cancel.setTextColor(ResUtils.getColor(R.color.spsdk_color_red));
            }
        } else {
            cancel.setVisibility(View.GONE);
        }

        TextView titleTv = layout.findViewById(R.id.titleTv);
        if (TextUtils.isEmpty(title)) {
            titleTv.setVisibility(View.GONE);
        } else {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(title);
        }

        TextView tv_msg = layout.findViewById(R.id.message);
        if (TextUtils.isEmpty(message)) {
            tv_msg.setVisibility(View.GONE);
        } else {
            tv_msg.setVisibility(View.VISIBLE);
            tv_msg.setMovementMethod(LinkMovementMethod.getInstance());
            tv_msg.setText(message);
        }

        dialog.setContentView(layout);
        dialog.setCancelable(isCancelable);

        if (isCancelable) {
            layout.findViewById(R.id.background).setOnClickListener(v -> dialog.dismiss());
        }
        if (dialog.getWindow() != null) {
            dialog.getWindow().setType(type);
        }
        return dialog;
    }
}