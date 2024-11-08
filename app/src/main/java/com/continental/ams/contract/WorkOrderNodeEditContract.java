package com.continental.ams.contract;

import android.util.Pair;

import com.continental.sdk.base.IBasePresenter;
import com.continental.sdk.base.IBaseView;
import com.continental.sdk.bean.request.FieldVo;
import com.continental.sdk.bean.request.Files;
import com.continental.sdk.bean.request.WorkOrderNodeVo;
import com.continental.sdk.bean.response.HandlerVo;

import java.util.List;

import retrofit2.http.Body;

public interface WorkOrderNodeEditContract {

    interface View extends IBaseView {
        void nodeSearchSuc(WorkOrderNodeVo vo);
        void nodeUpdateSuc(Boolean it);
        void uploadPhotoSus(Pair<List<String>, List<String>> pair);
    }

    interface Presenter extends IBasePresenter {
        void nodeSearch(Long nodeId);
        void nodeUpdate(HandlerVo vo);
        void uploadPhoto(List<String> photos, Files it);
    }
}
