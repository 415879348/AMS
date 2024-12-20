package com.continental.sdk.http;

import com.continental.sdk.bean.request.FileVo;
import com.continental.sdk.bean.request.AmendApplyVo;
import com.continental.sdk.bean.request.CreateWorkOrderVo;
import com.continental.sdk.bean.request.FieldVo;
import com.continental.sdk.bean.request.Files;
import com.continental.sdk.bean.request.IDListVo;
import com.continental.sdk.bean.request.JPRegisterVo;
import com.continental.sdk.bean.request.LeaveVo;
import com.continental.sdk.bean.request.LoginVo;
import com.continental.sdk.bean.request.OverTimeApplyVo;
import com.continental.sdk.bean.request.RequestApplyVo;
import com.continental.sdk.bean.request.WorkOrderNodeVo;
import com.continental.sdk.bean.response.AlertDetailVo;
import com.continental.sdk.bean.response.AlertVo;
import com.continental.sdk.bean.response.ApplyRecordVo;
import com.continental.sdk.bean.response.ApplyVo;
import com.continental.sdk.bean.response.AssetAlertBean;
import com.continental.sdk.bean.response.AssetAlertVo;
import com.continental.sdk.bean.response.AttendanceDetailsVo;
import com.continental.sdk.bean.response.DepartmentVo;
import com.continental.sdk.bean.response.DeviceBean;
import com.continental.sdk.bean.response.DeviceInfoForm;
import com.continental.sdk.bean.response.DeviceVo;
import com.continental.sdk.bean.response.DictionaryBean;
import com.continental.sdk.bean.response.DictionaryVo;
import com.continental.sdk.bean.response.HandlerVo;
import com.continental.sdk.bean.response.HttpResult;
import com.continental.sdk.bean.response.LanguageVo;
import com.continental.sdk.bean.response.LeaveTypeVo;
import com.continental.sdk.bean.response.NodeVo;
import com.continental.sdk.bean.response.RecordDetailVo;
import com.continental.sdk.bean.response.RepairDetailVo;
import com.continental.sdk.bean.response.StaffVo;
import com.continental.sdk.bean.response.StatisticsVo;
import com.continental.sdk.bean.response.UserVo;
import com.continental.sdk.bean.response.WorkOrderBean;
import com.continental.sdk.bean.response.WorkOrderVo;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 功能描述：api 接口
 *
 * @author (作者) someone
 * 创建时间： 2021/7/11
 */
public interface IApiService {

    @POST("auth/login")
    Single<HttpResult<String>> login(@Body LoginVo it);

    @POST("auth/logout")
    Single<HttpResult<Boolean>> logout();

    /**
     * 人員字典
     * @return
     */
    @GET("user/all")
    Single<HttpResult<List<UserVo>>> userAll();

    /**
     * 個人信息詳情
     * @return
     */
    @GET("auth")
    Single<HttpResult<UserVo>> auth();

    /**
     * 選擇字典
     * @param dictType 0:品牌 1:品牌型號 2:資產類型 3:所在位置
     * @return
     */
    @GET("dict")
    Single<HttpResult<List<DictionaryBean>>> appDict(@Query("dictType") String dictType);

    /**
     * 字典查詢所有列表
     * 公司主鍵
     * 0:品牌 1:品牌型號 2:資產類型 3:所在位置
     * @return
     */
    @GET("dict/all")
    Single<HttpResult<List<DictionaryBean>>> dictAll(@QueryMap Map<String, String> params);

    /**
     * 字典樹形查詢 品牌對於下級型號
     * @param dictType 0:品牌 1:品牌型號 2:資產類型 3:所在位置
     * @return
     */
    @GET("dict/parent")
    Single<HttpResult<DictionaryVo>> dictParent(@Query("dictType") int dictType);

    /**
     * 資產字典
     * @return
     */
    @GET("device/all")
    Single<HttpResult<List<DeviceBean>>> appDeviceAll();

    /**
     * 资产分页查询
     * @return
     */
    @GET("device")
    Single<HttpResult<DeviceVo>> device(@Query("current") int current, @Query("size") int size);

    /**
     * 资产分页查询
     * @return
     */
    @GET("device")
    Single<HttpResult<DeviceVo>> device(@QueryMap Map<String, String> params);

    /**
     * 資產刪除
     * @param ids 批量刪除主鍵，中間用逗號隔開，例1,2,3
     * @return
     */
    @HTTP(method = "DELETE", path = "device/", hasBody = true)
    Single<HttpResult<Boolean>> deleteAppDevice(@Body IDListVo ids);

    /**
     * 資產添加用 字段查詢列表
     * @param deviceTypeId 補充字段針對資產類型 0:其他 1:雪櫃 2:大華設備 3:IOT網關
     * @return
     */
    @GET("device/field")
    Single<HttpResult<List<FieldVo>>> deviceField(@Query("deviceTypeId") String deviceTypeId);

    /**
     * 資產修改用 字段查詢列表
     * @return
     */
    @GET("device/field/value")
    Single<HttpResult<List<FieldVo>>> deviceFieldValue(@QueryMap Map<String, String> params);

    /**
     * 資產修改用 補充字段值查詢列表
     * @param deviceTypeId 資產主鍵
     * @param deviceTypeId 補充字段針對資產類型 0:其他 1:雪櫃 2:大華設備 3:IOT網關
     * @return
     */
    @GET("device/field/value")
    Single<HttpResult<List<FieldVo>>> deviceFieldValue(@Query("deviceId") int deviceId, @Query("deviceTypeId") int deviceTypeId);

    /**
     * 資產添加
     * @param
     * @return
     */
    @POST("device")
    Single<HttpResult<Boolean>> addAppDevice(@Body DeviceInfoForm it);

    /**
     * 資產修改
     * @param
     * @return
     */
    @PUT("device")
    Single<HttpResult<Boolean>> updateAppDevice(@Body DeviceInfoForm it);

    /**
     * 資產詳情
     * @param
     * @return
     */
    @GET("device/{id}")
    Single<HttpResult<DeviceBean>> queryAppDevice(@Path("id") String id);

    /**
     * 添加工单
     * @param
     * @return
     */
    @POST("work/order")
    Single<HttpResult<Boolean>> createWorkOrder(@Body CreateWorkOrderVo it);

    /**
     * 處理工单
     * @param
     * @return
     */
    @POST("work/order/process")
    Single<HttpResult<Boolean>> workOrderProcess(@Body HandlerVo it) ;

    /**
     * 查詢工單
     * 待处理工单传递参数processStep=0
     * 已处理工单传递参数step=0
     * @return
     */
    @GET("work/order")
    Single<HttpResult<WorkOrderVo>> workOrderProcess(@QueryMap Map<String, String> params);

    /**
     * 查詢待處理工單數
     * @param
     * @return
     */
    @GET("work/order/count/process")
    Single<HttpResult<Integer>> workOrderCountProcess();

   /**
     * 查詢完成工單數
     * @param
     * @return
     */
    @GET("work/order/count/over")
    Single<HttpResult<Integer>> workOrderCountOver(@QueryMap Map<String, String> params);

    /**
     * 節點查詢 詳情頁面顯示執行信息列表
     * 查工单的处理人
     * @param orderId 工單主鍵
     * @return
     */
    @GET("work/order/node/{orderId}")
    Single<HttpResult<List<NodeVo>>> workOrderNode(@Path("orderId") String orderId);

    /**
     * 主鍵查詢 所有工單列表(包括首頁和工單頁面)詳情頁面用到
     * @param id 工單主鍵
     * @return
     */
    @GET("work/order/{id}")
    Single<HttpResult<WorkOrderBean>> workOrderID(@Path("id") String id);

    /**
     * 资产编号生成
     * @return
     */
    @GET("device/generate/code")
    Single<HttpResult<String>> generateCode();

    /**
     * 添加文件
     * @param
     * @return
     */
    @POST("document")
    Single<HttpResult<String>> document(@Body FileVo it);

    /**
     * 添加文件
     * @param
     * @return
     */
    @POST("document/more")
    Single<HttpResult<List<String>>> documentMore(@Body Files it);

    /**
     * 资产警报记录
     * @param
     * @return
     */
    @GET("device/alert/log")
    Single<HttpResult<AssetAlertVo>> deviceAlertLog(@QueryMap Map<String, String> params);

    /**
     * 主鍵查詢 警报詳情頁面用到
     * @param id 工單主鍵
     * @return
     */
    @GET("device/alert/log")
    Single<HttpResult<WorkOrderBean>> deviceAlertLogID(@Path("id") String id);

    /**
     * 资产警报记录总数
     * @param status 0：未处理 1：已处理
     * @return
     */
    @GET("device/alert/log/count")
    Single<HttpResult<Integer>> deviceAlertLogCount(@Query("status") Integer status);

    /**
     * 處理工单
     * @param
     * @return
     */
    @POST("device/alert/log/process/{id}")
    Single<HttpResult<Boolean>> deviceAlertLogProcess(@Path("id") String id);

    /**
     * 接收极光推送RegisterID
     * @param
     * @return
     */
    @POST("jp/registerId")
    Single<HttpResult<Object>> jpRegisterId(@Body JPRegisterVo it);

    @GET("http://182.92.123.13:8067/ams/jp/test")
    Single<HttpResult<AssetAlertBean>> jpTest();

    @GET("language")
    Single<HttpResult<List<LanguageVo>>> language();

    /////////////////////////////











//    //账户信息修改
//    @PUT("user/info")
//    Single<HttpResult<Boolean>> modifyUserInfo(@Body DeviceVo.RecordsBean it);

    //警報列表
    @GET("alert/paging")
    Single<HttpResult<AlertVo>> alert(@Query("page") int page, @Query("limit") int limit);

    //按ID查询警報详情
    @GET("alert/detail")
    Single<HttpResult<AlertDetailVo>> getAlertDetailById(@Query("id") String id);

    //申请列表
    @GET("apply/list")
    Single<HttpResult<ApplyVo>> applyList(@Query("page") int page, @Query("limit") int limit,  @Query("type") int type);

    @POST("leave")
    Single<HttpResult<Boolean>> leave(@Body LeaveVo it);

    @GET("leave/type/list")
    Single<HttpResult<List<LeaveTypeVo>>> leaveTypeList();

    //請假待办详情
    @GET("leave/ordinary/flow/details")
    Single<HttpResult<RecordDetailVo>> leaveOrdinaryFlowDetails(@Query("applyId") long applyId);

    //請假已办详情
    @GET("leave/ordinary/flow/details/all")
    Single<HttpResult<RecordDetailVo>> leaveOrdinaryFlowDetailsAll(@Query("applyId") long applyId);

    //补签待办详情
    @GET("repair/ordinary/flow/details")
    Single<HttpResult<RecordDetailVo>> repairOrdinaryFlowDetails(@Query("applyId") long applyId);

    //补签已办详情
    @GET("repair/ordinary/flow/details/all")
    Single<HttpResult<RecordDetailVo>> repairOrdinaryFlowDetailsAll(@Query("applyId") long applyId);

    //加班待办详情
    @GET("ot/ordinary/flow/details")
    Single<HttpResult<RecordDetailVo>> otOrdinaryFlowDetails(@Query("applyId") long applyId);

    //加班已办详情
    @GET("ot/ordinary/flow/details/all")
    Single<HttpResult<RecordDetailVo>> otOrdinaryFlowDetailsAll(@Query("applyId") long applyId);

    @GET("ot/paging")
    Single<HttpResult<ApplyVo>> otPaging(@Query("startTime") long startTime,
                                                   @Query("endTime") long endTime,
                                                   @Query("page") int page,
                                                   @Query("limit") int limit,
                                                   @Query("userIds") String userIds);

    @GET("repair/paging")
    Single<HttpResult<ApplyVo>> repairPaging(@Query("startTime") long startTime,
                                                       @Query("endTime") long endTime,
                                                       @Query("page") int page,
                                                       @Query("limit") int limit,
                                                       @Query("userIds") String userIds);

    @GET("leave/paging")
    Single<HttpResult<ApplyVo>> leavePaging(@Query("startTime") long startTime,
                                              @Query("endTime") long endTime,
                                              @Query("page") int page,
                                              @Query("limit") int limit,
                                              @Query("userIds") String userIds);

    @POST("repair")
    Single<HttpResult<Boolean>> repair(@Body AmendApplyVo it);

    @GET("repair/details")
    Single<HttpResult<RepairDetailVo>> repairDetails(@Query("applyId") String applyId);

    @GET("repair/paging")
    Single<HttpResult<RepairDetailVo>> repairPaging(@Query("applyId") String applyId);

    @POST("repair/ordinary/flow/create")
    Single<HttpResult<Boolean>> repairOrdinaryFlowCreate(@Body RequestApplyVo it);

    @PUT("repair/ordinary/flow/update")
    Single<HttpResult<Boolean>> repairOrdinaryFlowUpdate(@Body RequestApplyVo it);

    @POST("repair/ordinary/flow/revocation")
    Single<HttpResult<Boolean>> repairOrdinaryFlowRevocation(@Query("applyId") Long applyId);

    @POST("leave/ordinary/flow/create")
    Single<HttpResult<Boolean>> leaveOrdinaryFlowCreate(@Body RequestApplyVo it);

    @PUT("leave/ordinary/flow/update")
    Single<HttpResult<Boolean>> leaveOrdinaryFlowUpdate(@Body RequestApplyVo it);

    @POST("leave/ordinary/flow/revocation")
    Single<HttpResult<Boolean>> leaveOrdinaryFlowRevocation(@Query("applyId") Long applyId);

    @POST("ot/ordinary/flow/create")
    Single<HttpResult<Boolean>> otOrdinaryFlowCreate(@Body RequestApplyVo it);

    @PUT("ot/ordinary/flow/update")
    Single<HttpResult<Boolean>> otOrdinaryFlowUpdate(@Body RequestApplyVo it);

    @POST("ot/ordinary/flow/revocation")
    Single<HttpResult<Boolean>> oTOrdinaryFlowRevocation(@Query("applyId") Long applyId);

    @POST("ot")
    Single<HttpResult<Boolean>> ot(@Body OverTimeApplyVo it);

    @GET("user/list")
    Single<HttpResult<List<StaffVo>>> userList();

    @GET("home/record/statistics")
    Single<HttpResult<List<StatisticsVo>>> recordStatistics(@Query("startTime") long startTime, @Query("endTime") long endTime);

    @GET("home/oneself/record/statistics")
    Single<HttpResult<List<StatisticsVo>>> oneselfStatistics(@Query("startTime") long startTime, @Query("endTime") long endTime);

    @GET("home/record")
    Single<HttpResult<List<ApplyRecordVo>>> record();

    @GET("home/apply/record")
    Single<HttpResult<List<ApplyRecordVo>>> applyRecord();

    @GET("attendance/details")
    Single<HttpResult<AttendanceDetailsVo>> attendanceDetails(@Query("recordId") long recordId);

//    @GET("attendance/paging")
//    Single<HttpResult<ApplyVo>> attendancePaging(@Query("startTime") long startTime,
//                                                            @Query("endTime") long endTime,
//                                                            @Query("page") int page,
//                                                            @Query("limit") int limit,
//                                                            @Query("deptIds") String deptIds,
//                                                            @Query("userIds") String userIds,
//                                                            @Query("isOverTemperature") int isOverTemperature);

    @GET("attendance/paging")
    Single<HttpResult<ApplyVo>> attendancePaging(@QueryMap Map<String, String> params);

    @GET("ordinary/flow/node")
    Single<HttpResult<AttendanceDetailsVo>> ordinaryFlowNode(@Query("applyId") long recordId, @Query("applyType") long applyType);

    @GET("dept/list")
    Single<HttpResult<List<DepartmentVo>>> departmentList();

    @GET("work/order/node/search/{nodeId}")
    Single<HttpResult<WorkOrderNodeVo>> nodeSearch(@Path("nodeId") Long nodeId);

    @PUT("work/order/node/update")
    Single<HttpResult<Boolean>> nodeUpdate(@Body HandlerVo vo);
}