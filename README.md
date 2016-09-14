# cordova-plugin-system-alert-window-permission
### How to use

```
/* return 0 when dont have SYSTEM_ALERT_WINDOW permission, 1 when have SYSTEM_ALERT_WINDOW permission */
cordova.plugins.systemAlertWindowPermission.hasPermission(successFunction, errorFunction);

/* request SYSTEM_ALERT_WINDOW permission */
cordova.plugins.systemAlertWindowPermission.requestPermission(successFunction, errorFunction);
```
