package com.continental.sdk.bean.response;

import java.io.Serializable;

public class UrlsBean implements Serializable {
        /**
         * createTime :
         * extension :
         * id : 0
         * joinId : 0
         * type : 0
         * url :
         */
        private Long createTime;
        private String extension;
        private Long id;
        private Integer joinId;
        private Integer type;
        private String url;

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getJoinId() {
            return joinId;
        }

        public void setJoinId(Integer joinId) {
            this.joinId = joinId;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }