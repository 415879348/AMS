package com.esharp.ams.contract;

import com.esharp.sdk.base.IBasePresenter;
import com.esharp.sdk.base.IBaseView;
import com.esharp.sdk.base.IHostView;
import com.esharp.sdk.bean.FileVo;
import com.esharp.sdk.bean.response.DeviceBean;
import com.esharp.sdk.bean.response.DeviceInfoForm;
import com.esharp.sdk.bean.response.DictionaryBean;
import com.esharp.sdk.bean.response.WorkOrderVo;

import java.util.List;

public interface CreateAssetContract {

    interface View extends IBaseView {
        void addAppDeviceSuc(boolean it);

        void assetTypeSuc(List<DictionaryBean> it);

        void assetBrandSuc(List<DictionaryBean> it);

        void assetModelSuc(List<DictionaryBean> it);

        void generateCodeSuc(String it);

        void uploadPhotoSus1(String it);

        void uploadPhotoSus2(String it);

        void uploadPhotoSus3(String it);

        void deviceAllSuc(List<DeviceBean> it);
    }

    interface Presenter extends IBasePresenter {
        void addAppDevice(DeviceInfoForm it);

        void generateCode();

        void assetType();

        void assetBrand();

        void assetModel(String brandID);

        void uploadPhoto(int request, FileVo photo);

        void deviceAll();
    }
}
