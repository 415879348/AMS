package com.continental.ams.contract;

import android.util.Pair;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.request.FileVo;
import com.continental.sdk.bean.request.FieldVo;
import com.continental.sdk.bean.request.Files;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.bean.response.DeviceInfoForm;
import com.continental.sdk.bean.response.DictionaryBean;

import java.util.List;
import java.util.Map;

public interface AssetEditContract {

    interface View extends IBaseView {
        void getDataSuc(DeviceBean it);

        void updateDeviceSuc(boolean it);

        void deviceAllSuc(List<DeviceBean> it);

        void assetTypeSuc(List<DictionaryBean> it);

        void assetBrandSuc(List<DictionaryBean> it);

        void assetModelSuc(List<DictionaryBean> it);

        void uploadPhotoSus(Pair<List<String>, List<String>> pair);

        void deviceFieldSuc(List<FieldVo> it);
    }

    interface Presenter extends IBasePresenter {
        void getData(String deviceId);

        void updateDevice(DeviceInfoForm it);

        void assetType();

        void assetBrand();

        void assetModel(String brandID);

        void uploadPhoto(List<String> photos, Files it);

        void deviceAll();

        void deviceFieldValue(Map<String, String> params);
    }
}
