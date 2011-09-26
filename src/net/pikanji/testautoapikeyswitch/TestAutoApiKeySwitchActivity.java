
package net.pikanji.testautoapikeyswitch;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class TestAutoApiKeySwitchActivity extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getResources().getString(R.string.app_name);

        // デバッグかリリースか判断し、正しいAPIキーを選択する
        int mapkeyResId;
        if (isDebuggable()) {
            mapkeyResId = R.string.mapkey_debug;
            title += ": Debug";
        } else {
            mapkeyResId = R.string.mapkey_release;
            title += ": Release";
        }
        String mapKey = getResources().getString(mapkeyResId);

        setTitle(title);

        // MapViewを生成する
        MapView mapView = new MapView(this, mapKey);
        mapView.setEnabled(true);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);

        setContentView(mapView);
    }

    public boolean isDebuggable() {
        PackageManager manager = getPackageManager();
        ApplicationInfo appInfo = null;
        try {
            appInfo = manager.getApplicationInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            return false;
        }

        int debuggableFlag = appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE;
        return (ApplicationInfo.FLAG_DEBUGGABLE == debuggableFlag);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
