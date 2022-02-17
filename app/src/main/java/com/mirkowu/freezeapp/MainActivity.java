package com.mirkowu.freezeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mirkowu.lib_util.LogUtil;
import com.mirkowu.lib_util.utilcode.util.ShellUtils;
import com.mirkowu.lib_util.utilcode.util.Utils;

public class MainActivity extends AppCompatActivity {
    public static final String PKG = "com.yiyaowang.wms";
    TextView tvState;
    DeviceMethod deviceMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvState = findViewById(R.id.tv_state);

        LogUtil.init(BuildConfig.DEBUG);
        getState(PKG);

        deviceMethod = DeviceMethod.getInstance(this);
        deviceMethod.onActivate();


    }

    private void getState(String pkgName) {
        ShellUtils.execCmdAsync("pm list packages | grep wms", false, new Utils.Consumer<ShellUtils.CommandResult>() {
            @Override
            public void accept(ShellUtils.CommandResult commandResult) {
                LogUtil.e(commandResult.toString());
            }
        });

        int state = getPackageManager().getApplicationEnabledSetting(pkgName);
        tvState.setText("当前状态：" + state);
    }

    public void freezeApp(View view) {
//        ShellUtils.execCmdAsync("pm hide " + PKG, false, new Utils.Consumer<ShellUtils.CommandResult>() {
//            @Override
//            public void accept(ShellUtils.CommandResult commandResult) {
//                LogUtil.e(commandResult.toString());
//            }
//        });
//        getPackageManager().setApplicationEnabledSetting(PKG, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        deviceMethod.hideApp(PKG);
        getState(PKG);
    }

    public void unfreezeApp(View view) {
//        ShellUtils.execCmdAsync("pm unhide " + PKG, false, new Utils.Consumer<ShellUtils.CommandResult>() {
//            @Override
//            public void accept(ShellUtils.CommandResult commandResult) {
//                LogUtil.e(commandResult.toString());
//            }
//        });
//        getPackageManager().setApplicationEnabledSetting(PKG, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        deviceMethod.unHideApp(PKG);
        getState(PKG);
    }

    public void activeManager(View view) {
        ShellUtils.execCmdAsync("dpm set-device-owner com.mirkowu.freezeapp/.AdminReceiver", false, new Utils.Consumer<ShellUtils.CommandResult>() {
            @Override
            public void accept(ShellUtils.CommandResult commandResult) {
                LogUtil.e(commandResult.toString());
            }
        });
    }

    public void removeManager(View view) {
        deviceMethod.onRemoveActivate();
    }
}