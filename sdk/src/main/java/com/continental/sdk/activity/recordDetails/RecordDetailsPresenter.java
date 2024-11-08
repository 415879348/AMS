package com.continental.sdk.activity.recordDetails;

import com.continental.sdk.base.BasePresenter;

public class RecordDetailsPresenter extends BasePresenter<RecordDetailsContract.IHost> implements RecordDetailsContract.Presenter {

    public RecordDetailsPresenter(RecordDetailsContract.IHost mView) {
        super(mView);
    }
}
