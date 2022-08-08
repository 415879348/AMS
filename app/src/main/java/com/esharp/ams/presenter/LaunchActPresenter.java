package com.esharp.ams.presenter;

import com.esharp.ams.contract.LaunchActContract;
import com.esharp.sdk.base.BaseObserver;
import com.esharp.sdk.base.BasePresenter;
import com.esharp.sdk.bean.request.LoginVo;
import com.esharp.sdk.http.HttpFunction;
import com.esharp.sdk.http.HttpService;
import com.esharp.sdk.rxjava.HttpResultOperator;
import com.esharp.sdk.rxjava.ProgressOperator;
import com.esharp.sdk.rxjava.SchedulerUtils;

public class LaunchActPresenter extends BasePresenter<LaunchActContract.IHost> implements LaunchActContract.Presenter {

    public LaunchActPresenter(LaunchActContract.IHost mView) {
        super(mView);
    }

    @Override
    public void method() {
        HttpService.get().login(new LoginVo("super", "123456"))
                .map(new HttpFunction<>())
                .compose(SchedulerUtils.io_main_single())
                .subscribe(new BaseObserver<>(mView, mView::onLoginSuccess));
    }

    @Override
    public void method2() {
//        HttpService.get().userAll()
//                .lift(new HttpResultOperator<>())
//                .compose(SchedulerUtils.io_main_single())
//                .lift(new ProgressOperator<>(mView, -1))
//                .subscribe(new BaseObserver<>(mView, mView::getUserAll));

        HttpService.get().auth()
                .lift(new HttpResultOperator<>())
                .compose(SchedulerUtils.io_main_single())
                .lift(new ProgressOperator<>(mView, -1))
                .subscribe(new BaseObserver<>(mView, mView::getAuth));

//        HttpService.get().appDict("0")
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::getAppDict));

//        Map<String, String> map = new HashMap<>();
//        map.put("companyId", "a6baa2bdb9134615ac3735a5b96fb37f");
//        map.put("dictType", "2");
//        HttpService.get().dictAll(map)
//        .map(new HttpFunction<>())
//        .compose(SchedulerUtils.io_main_single())
//        .subscribe(new BaseObserver<>(mView, mView::getAppDict));
//
//        HttpService.get().dictParent(0)
//        .map(new HttpFunction<>())
//        .compose(SchedulerUtils.io_main_single())
//        .subscribe(new BaseObserver<>(mView, mView::dictParent));

//        HttpService.get().appDeviceAll()
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::getAppDeviceAll));

//        val json: String = JSONObject.toJSONString(purchases)
//                new JSONArray(new String["56"])

//        IDListVo vo = new IDListVo();
//        vo.getIds().add("57");
//        HttpService.get().deleteAppDevice(vo)
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::deleteAppDevice));

//        分页加载资产
//        HttpService.get().device("1", "20")
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::device));

//        添加资产
//        DeviceInfoForm it = new DeviceInfoForm();
//        it.setAddressId(87);
//        it.setBrandId(80);
//        it.setBrandModelId(81);
//        it.setDeviceName("A80");
//        it.setDeviceNumber("setDeviceNumber");
//        it.setDeviceTypeId(86);
//        it.setDeviceName("t34t3t3 update");
//        it.setRemark("remark");
//        HttpService.get().addAppDevice(it)
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::addAppDevice));

//        修改资产
//        DeviceInfoForm it = new DeviceInfoForm();
//        it.setId(155);
//        it.setAddressId(87);
//        it.setBrandId(80);
//        it.setBrandModelId(81);
//        it.setDeviceName("A80 58");
//        it.setDeviceNumber("setDeviceNumber");
//        it.setDeviceTypeId(86);
//        it.setRemark("update update update");
//        HttpService.get().updateAppDevice(it)
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::updateAppDevice));

        //            HttpService.get().workOrderID("70")
//            .map(new HttpFunction<>())
//            .compose(SchedulerUtils.io_main_single())
//            .subscribe(new BaseObserver<>(mView, mView::workOrderID));

//        HttpService.get().workOrderCountProcess()
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::workOrderCountProcess));

//        HttpService.get().workOrderCountOver()
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::workOrderCountOver));

//        HttpService.get().workOrderProcess("1", "20")
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::workOrderProcess));

//            HttpService.get().workOrderProcess("1", "20", "0")
//            .map(new HttpFunction<>())
//            .compose(SchedulerUtils.io_main_single())
//            .subscribe(new BaseObserver<>(mView, mView::workOrderProcess));

//        HttpService.get().queryAppDevice("155")
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::queryAppDevice));

//        HandlerVo it = new HandlerVo();
//        it.setId(70);
//        it.setContent("content");
//        it.setIsOver(0);
//        HttpService.get().workOrderProcess(it)
//        .map(new HttpFunction<>())
//        .compose(SchedulerUtils.io_main_single())
//        .subscribe(new BaseObserver<>(mView, mView::workOrderProcessSuc));

//        資產添加用 補充字段查詢列表
//        HttpService.get().deviceField(2)
//        .map(new HttpFunction<>())
//        .compose(SchedulerUtils.io_main_single())
//        .subscribe(new BaseObserver<>(mView, mView::deviceField));

//        資產修改用 補充字段查詢列表
//        HttpService.get().deviceFieldValue(155, 2)
//        .map(new HttpFunction<>())
//        .compose(SchedulerUtils.io_main_single())
//        .subscribe(new BaseObserver<>(mView, mView::deviceFieldValue));

//        HttpService.get().version()
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::version));

//        HttpService.get().logout()
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::logout));


//        HttpService.get().workOrderNode("70")
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::workOrderNode));


//        HttpService.get().document(new DocumentVo("base64", "extension", 0))
//                .map(new HttpFunction<>())
//                .compose(SchedulerUtils.io_main_single())
//                .subscribe(new BaseObserver<>(mView, mView::document));

    }
}
