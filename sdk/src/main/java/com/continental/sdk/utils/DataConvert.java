package com.continental.sdk.utils;

import com.continental.sdk.bean.response.ApprovalUserBean;
import com.continental.sdk.bean.response.CopyUserListBean;
import com.continental.sdk.bean.response.StaffVo;

import java.util.ArrayList;
import java.util.List;

public class DataConvert {

    public static List<ApprovalUserBean> staffVoToApprovalUserBean(List<StaffVo> list) {

        List<ApprovalUserBean> it = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            StaffVo vo = list.get(i);
            ApprovalUserBean bean = new ApprovalUserBean();
            bean.setApprovalUserId(vo.getUserId());
            bean.setApprovalUserName(vo.getUsername());
            bean.setDepartmentName(vo.getDepartmentName());
            bean.setHeadPortrait(vo.getImageUrl());
            it.add(bean);
        }
        return it;
    }

    public static ArrayList<StaffVo> approvalUserBeanToStaffVo(List<ApprovalUserBean> list) {

        ArrayList<StaffVo> it = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            ApprovalUserBean bean = list.get(i);
            StaffVo vo = new StaffVo();
            vo.setUserId(bean.getApprovalUserId());
            vo.setUsername(bean.getApprovalUserName());
            vo.setDepartmentName(bean.getDepartmentName());
            vo.setImageUrl(bean.getHeadPortrait());
            it.add(vo);
        }
        return it;
    }

    public static List<CopyUserListBean> staffVoToCopyUserBean(List<StaffVo> list) {

        List<CopyUserListBean> it = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            StaffVo vo = list.get(i);
            CopyUserListBean bean = new CopyUserListBean();
            bean.setCopyUserId(vo.getUserId());
            bean.setCopyUserName(vo.getUsername());
            bean.setHeadPortrait(vo.getImageUrl());
            it.add(bean);
        }
        return it;
    }

    public static List<StaffVo> copyUserBeanToStaffVo(List<CopyUserListBean> list) {

        List<StaffVo> it = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            CopyUserListBean bean = list.get(i);
            StaffVo vo = new StaffVo();
            vo.setUserId(bean.getCopyUserId());
            vo.setUsername(bean.getCopyUserName());
            vo.setImageUrl(bean.getHeadPortrait());
            it.add(vo);
        }
        return it;
    }
}
