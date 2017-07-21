package com.rongyant.architecturecomponentmaster.base;

/**
 * Created by XRY on 2017/7/21.
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(String[] deniedPermissions);
}
