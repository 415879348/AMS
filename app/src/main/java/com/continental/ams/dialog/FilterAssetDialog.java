package com.continental.ams.dialog;

import android.widget.ImageView;
import com.continental.ams.R;
import com.blankj.utilcode.util.LogUtils;

import com.continental.ams.ui.MainActivity;
import com.continental.sdk.bean.response.DictionaryBean;
import com.continental.sdk.dialog.BaseAlertDialog;
import com.continental.sdk.dialog.ListPopWindow;
import com.continental.sdk.http.HttpService;
import com.continental.sdk.rxjava.HttpResultOperator;
import com.continental.sdk.rxjava.ProgressOperator;
import com.continental.sdk.rxjava.SchedulerUtils;
import com.continental.sdk.widget.OnClickCallback;
import com.continental.sdk.widget.SPCardEditView;
import com.continental.sdk.widget.SPSelectorCardView;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * 功能描述：资产筛选
 *
 * @author (作者) someone
 * 创建时间： 2021/7/9
 */
public class FilterAssetDialog extends BaseAlertDialog {

    private final SPSelectorCardView scv_type;

    private final ImageView iv_search;

    private final SPCardEditView cev_number_name, cev_location;

    private ListPopWindow<DictionaryBean> assetTypePop;

    public FilterAssetDialog(@NonNull MainActivity mView) {
        super(mView, R.layout.dialog_filter_assets);
        cev_number_name = findViewById(R.id.cev_number_name);
        scv_type = findViewById(R.id.scv_type);
        cev_location = findViewById(R.id.cev_location);
        iv_search = findViewById(R.id.iv_search);

        findViewById(R.id.iv_close).setOnClickListener( v -> {
            cancel();
        });

        scv_type.setOnItemClick(v -> {
            DictionaryBean vo = (DictionaryBean) v.getTag();
            if (vo == null) {
                assetTypePop.show(scv_type, "");
            } else {
                assetTypePop.show(scv_type, vo.getId());
            }
        });

        Map<String, String> map = new HashMap<>();
        map.put("dictType", "2");
        HttpService.get().dictAll(map)
            .lift(new HttpResultOperator<>())
            .compose(SchedulerUtils.io_main_single())
            .lift(new ProgressOperator<>(mView, -1))
            .subscribe(it -> {
                assetTypePop = new ListPopWindow<>(scv_type, mView, it, vo -> {
                    LogUtils.json(vo);
                    scv_type.setTag(vo);
                    scv_type.setContent(vo.getDictName());
                });
            });
    }

    public BaseAlertDialog setOnClickListener(OnClickCallback<Map<String, String>> it) {
        iv_search.setOnClickListener(v-> {

            String titleOrNumber = "";
            String deviceTypeId = "";
            String location = "";

            titleOrNumber = cev_number_name.getContent();

            DictionaryBean typeVo = (DictionaryBean) scv_type.getTag();
            if (typeVo != null) {
                deviceTypeId = typeVo.getId();
            }
            location = cev_location.getContent();

            Map<String, String> map = new HashMap<>();

            map.put("titleOrNumber", titleOrNumber);
            map.put("deviceTypeId", deviceTypeId);
            map.put("location", location);

            LogUtils.json(map);
            it.onClick(map);
            cancel();
        });
        return this;
    }

}
