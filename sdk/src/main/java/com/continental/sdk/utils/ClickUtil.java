package com.continental.sdk.utils;

public class ClickUtil {

   private static long mTouchTime = 0;

   private static final long WAIT_TIME = 800L;

   public static boolean isFastDoubleClick() {
       if (System.currentTimeMillis() - mTouchTime < WAIT_TIME) {
            mTouchTime = 0;
            return true;
       } else {
            mTouchTime = System.currentTimeMillis();
            return false;
       }
   }

}