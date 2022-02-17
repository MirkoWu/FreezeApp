package com.mirkowu.freezeapp;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mirkowu.lib_util.LogUtil;

public class AdminReceiver extends DeviceAdminReceiver {
    public static final String TAG = AdminReceiver.class.getSimpleName();

    @Override
    public void onEnabled(@NonNull Context context, @NonNull Intent intent) {
        super.onEnabled(context, intent);
        LogUtil.i(TAG, "onEnabled");
    }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        LogUtil.i(TAG, "onReceive");
    }

    @Nullable
    @Override
    public CharSequence onDisableRequested(@NonNull Context context, @NonNull Intent intent) {
        CharSequence result = super.onDisableRequested(context, intent);
        LogUtil.i(TAG, "onDisableRequested " + result);
        //这里如果返回的字符串不为空，那么当用户去设置里取消时，则会提示带此文字的确定框
        String strResult = "取消时便不能使用免root停用应用";
        return strResult;
    }

    @Override
    public void onDisabled(@NonNull Context context, @NonNull Intent intent) {
        super.onDisabled(context, intent);
        LogUtil.i(TAG, "onDisabled");
    }
}
