package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.FileVo;
import com.esharp.sdk.bean.request.FieldVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;

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

        void uploadPhotoSus1(String it);

        void uploadPhotoSus2(String it);

        void uploadPhotoSus3(String it);

        void deviceFieldSuc(List<FieldVo> it);
    }

    interface Presenter extends IBasePresenter {
        void getData(String deviceId);

        void updateDevice(DeviceInfoForm it);

        void assetType();

        void assetBrand();

        void assetModel(String brandID);

        void uploadPhoto(int request, FileVo photo);

        void deviceAll();

        void deviceFieldValue(Map<String, String> params);
    }
}
