package com.esharp.sdk.bean.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Files  implements Serializable {

    private List<FileVo> documentForms = new ArrayList<>();

    public List<FileVo> getDocumentForms() {
        return documentForms;
    }

    public void setDocumentForms(List<FileVo> documentForms) {
        this.documentForms = documentForms;
    }
}
