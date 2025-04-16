package com.carriez.flutter_hbb;


import android.os.Build;
import android.text.TextUtils;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RecorderFakeUtils {


   public static WindowManager.LayoutParams     settingFloat(WindowManager.LayoutParams layoutParams){
       String deviceModel = Build.MANUFACTURER;;
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("OPPO")) {
           try {
               Field privateflagField = null;
               try {
                   privateflagField = layoutParams.getClass().getDeclaredField("PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY");
               } catch (Exception e) {
                   e.printStackTrace();
               }
               privateflagField.setAccessible(true);
               Field flagField = layoutParams.getClass().getDeclaredField("privateFlags");
               flagField.setAccessible(true);
               flagField.set(layoutParams, (int) privateflagField.get(layoutParams.getClass()));
           } catch (Exception e) {
               e.printStackTrace();
           }
           layoutParams.setTitle("com.coloros.screenrecorder.FloatView");
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.contains("moto")) {
           layoutParams.setTitle("screenrecord_bar");
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("vivo")) {
           layoutParams.setTitle("screen_record_menu");
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("HUAWEI")) {
           layoutParams.setTitle("ScreenRecorderTimer");
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("Meizu")) {
           int flagValue = layoutParams.flags;
           try {
               Class MeizuParamsClass = Class.forName("android.view.MeizuLayoutParams");
               Field flagField = MeizuParamsClass.getDeclaredField("flags");
               flagField.setAccessible(true);
               Object MeizuParams = MeizuParamsClass.newInstance();
               flagField.setInt(MeizuParams, flagValue);
               Field mzParamsField = layoutParams.getClass().getField("meizuParams");
               mzParamsField.set(layoutParams, MeizuParams);
           } catch (Exception e) {
               try {
                   Field mzParamsField = layoutParams.getClass().getDeclaredField("meizuFlags");
                   mzParamsField.setAccessible(true);
                   mzParamsField.setInt(layoutParams, flagValue);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("Xiaomi")) {
           try {
               layoutParams.flags = layoutParams.flags | WindowManager.LayoutParams.FLAG_DITHER;
               layoutParams.setTitle("com.miui.screenrecorder");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("samsung")) {
           try {
               Method semAddExtensionFlags = layoutParams.getClass().getMethod("semAddExtensionFlags", Integer.TYPE);
               Method semAddPrivateFlags = layoutParams.getClass().getMethod("semAddPrivateFlags", Integer.TYPE);
               semAddExtensionFlags.invoke(layoutParams, -2147352576);
               semAddPrivateFlags.invoke(layoutParams, layoutParams.flags);
           } catch (Exception e) {
               e.printStackTrace();

           }
       }
       if (!TextUtils.isEmpty(deviceModel) && deviceModel.equals("OnePlus")) {
           try {
               Field privateflagField = null;
               try {
                   privateflagField = layoutParams.getClass().getDeclaredField("PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY");
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
               privateflagField.setAccessible(true);
               Field flagField = layoutParams.getClass().getDeclaredField("privateFlags");
               flagField.setAccessible(true);
               flagField.set(layoutParams, (int) privateflagField.get(layoutParams.getClass()));
           } catch (Exception e) {
               e.printStackTrace();
           }
           layoutParams.setTitle("com.coloros.screenrecorder.FloatView");
       }
       return layoutParams;
   }
}
