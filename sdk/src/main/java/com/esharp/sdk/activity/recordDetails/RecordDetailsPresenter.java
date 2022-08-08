package com.esharp.sdk.activity.recordDetails;

import com.esharp.sdk.base.BasePresenter;

public class RecordDetailsPresenter extends BasePresenter<RecordDetailsContract.IHost> implements RecordDetailsContract.Presenter {

    public RecordDetailsPresenter(RecordDetailsContract.IHost mView) {
        super(mView);
    }
}
