package com.continental.sdk.bean.response;

import java.io.Serializable;
import java.util.List;

public class DictionaryVo implements Serializable {


    /**
     * current : 0
     * hitCount : true
     * pages : 0
     * records : [{"active":0,"children":[{"active":0,"companyId":"","dictName":"","dictType":0,"id":0,"parentId":0,"sort":0}],"companyId":"","dictName":"","dictType":0,"id":0,"parentId":0,"sort":0}]
     * searchCount : true
     * size : 0
     * total : 0
     */

    private Integer current;
    private Boolean hitCount;
    private Integer pages;
    private Boolean searchCount;
    private Integer size;
    private Integer total;
    private List<RecordsBean> records;

    public static class RecordsBean implements Serializable {
        /**
         * active : 0
         * children : [{"active":0,"companyId":"","dictName":"","dictType":0,"id":0,"parentId":0,"sort":0}]
         * companyId :
         * dictName :
         * dictType : 0
         * id : 0
         * parentId : 0
         * sort : 0
         */

        private Integer active;
        private String companyId;
        private String dictName;
        private Integer dictType;
        private Integer id;
        private Integer parentId;
        private Integer sort;
        private List<DictionaryBean> children;
    }
}
