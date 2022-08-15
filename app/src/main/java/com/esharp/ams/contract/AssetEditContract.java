package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;

import java.util.List;

public interface AssetEditContract {

    interface View extends IBaseView {
        void getDataSuc(DeviceBean it);
        void updateDeviceSuc(boolean it);
        void assetTypeSuc(List<DictionaryBean> it);
        void assetBrandSuc(List<DictionaryBean> it);
        void assetModelSuc(List<DictionaryBean> it);
    }

    interface Presenter extends IBasePresenter {
        void getData(String deviceId);

        void updateDevice(DeviceInfoForm it);

        void assetType();

        void assetBrand();

        void assetModel(String brandID);
    }
}
