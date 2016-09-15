/*global cordova, module*/

var systemAlertWindowPermissionName = "SystemAlertWindowPermission";

module.exports = {
    SYSTEM_ALERT_WINDOW: "android.permission.SYSTEM_ALERT_WINDOW",
    hasPermission: function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, systemAlertWindowPermissionName, "hasPermission", []);
    },
    requestPermission: function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, systemAlertWindowPermissionName, "requestPermission", []);
    }
};
