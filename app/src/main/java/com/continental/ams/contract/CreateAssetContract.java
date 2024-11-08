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

public interface CreateAssetContract {

    interface View extends IBaseView {
        void addAppDeviceSuc(boolean it);

        void assetTypeSuc(List<DictionaryBean> it);

        void assetBrandSuc(List<DictionaryBean> it);

        void assetModelSuc(List<DictionaryBean> it);

        void generateCodeSuc(String it);

        void uploadPhotoSus(Pair<List<String>, List<String>> pair);

        void deviceAllSuc(List<DeviceBean> it);

        void deviceFieldSuc(List<FieldVo> it);
    }

    interface Presenter extends IBasePresenter {
        void addAppDevice(DeviceInfoForm it);

        void generateCode();

        void assetType();

        void assetBrand();

        void assetModel(String brandID);

        void uploadPhoto(List<String> photos, Files it);

        void deviceAll();

        void deviceField(String deviceTypeId);
    }
}
