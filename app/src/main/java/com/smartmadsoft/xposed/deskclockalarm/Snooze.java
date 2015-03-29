package com.smartmadsoft.xposed.deskclockalarm;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Snooze implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (!loadPackageParam.packageName.equals("com.android.deskclock") )
            return;

        XposedHelpers.findAndHookMethod("com.android.deskclock.DeskClock", loadPackageParam.classLoader, "initViews", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedHelpers.setObjectField(param.thisObject, "mSelectedTab", 0);
            }
        });
    }
}
