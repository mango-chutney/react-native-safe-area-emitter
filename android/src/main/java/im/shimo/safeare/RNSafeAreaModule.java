

package im.shimo.safeare;


import android.content.res.Resources;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.DisplayMetricsHolder;

import java.util.HashMap;
import java.util.Map;

public class RNSafeAreaModule extends ReactContextBaseJavaModule {
    private static int mNavigationBarHeight = 0;
    private static int mStatusBarHeight = 25;

    public RNSafeAreaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Resources resources = reactContext.getResources();
        int bottomId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (bottomId > 0) {
          int height = resources.getDimensionPixelSize(bottomId);
          mNavigationBarHeight = (int) ( height / DisplayMetricsHolder.getScreenDisplayMetrics().density);
        }
        int topId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (topId > 0) {
            mStatusBarHeight = resources.getDimensionPixelSize(topId);
          int height = resources.getDimensionPixelSize(topId);
          mStatusBarHeight = (int) ( height / DisplayMetricsHolder.getScreenDisplayMetrics().density);
        }
    }

    @Override
    public String getName() {
        return "RNSafeArea";
    }

    @ReactMethod
    public void getRootSafeArea(Promise promise) {
        promise.resolve(getRootSafeArea());
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("rootSafeArea", getRootSafeArea());
        return constants;
    }

    private WritableMap getRootSafeArea() {
        WritableMap result = Arguments.createMap();
        result.putInt("top", mStatusBarHeight);
        result.putInt("bottom", 0);
        result.putInt("left", 0);
        result.putInt("right", 0);
        return result;
    }
}

