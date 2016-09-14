/*global cordova, module*/

module.exports = {
    hasPermission: function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SystemAlertWindowPermission", "hasPermission", []);
    },
    requestPermission: function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SystemAlertWindowPermission", "requestPermission", []);
    }
};
