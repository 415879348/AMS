package com.continental.sdk.utils;

import com.continental.sdk.Constant;
import com.continental.sdk.Tag;

public class TagUtils {

    public static Tag getTagFromApplyType(int applyType) {
        Tag tab = Tag.AMEND;
        switch (applyType) {
            case Constant.APPLY_TYPE_AMEND: {
                tab = Tag.AMEND;
            }
                break;
            case Constant.APPLY_TYPE_LEAVE: {
                tab = Tag.LEAVE;
            }
                break;
            case Constant.APPLY_TYPE_OVERTIME: {
                tab = Tag.OVERTIME;
            }
                break;
        }
        return tab;
    }

}
