package com.rn_navigation_redux;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.sensorsdata.analytics.RNSensorsAnalyticsPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SAConfigOptions;
import com.sensorsdata.analytics.android.sdk.SensorsAnalyticsAutoTrackEventType;


public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new RNSensorsAnalyticsPackage(),
            new RNGestureHandlerPackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
  initSA();
  }

  private void initSA(){
    // 数据接收的 URL
    final String SA_SERVER_URL = "https://newsdktest.datasink.sensorsdata.cn/sa?project=chenru&token=5a394d2405c147ca";

    //设置 SAConfigOptions，传入数据接收地址 SA_SERVER_URL
    SAConfigOptions saConfigOptions = new SAConfigOptions(SA_SERVER_URL);

    //通过 SAConfigOptions 设置神策 SDK，每个条件都非必须，开发者可根据自己实际情况设置，更多设置可参考 SAConfigOptions 类中方法注释
    saConfigOptions.setAutoTrackEventType(
      SensorsAnalyticsAutoTrackEventType.APP_CLICK | // 开启全埋点点击事件
            SensorsAnalyticsAutoTrackEventType.APP_START |      //开启全埋点启动事件
            SensorsAnalyticsAutoTrackEventType.APP_END |        //开启全埋点退出事件
            SensorsAnalyticsAutoTrackEventType.APP_VIEW_SCREEN)     //开启全埋点浏览事件
            .enableLog(true)        //开启神策调试日志，默认关闭(调试时，可开启日志)。
            .enableTrackAppCrash();     //开启 crash 采集
    //需要在主线程初始化神策 SDK
    SensorsDataAPI.startWithConfigOptions(this, saConfigOptions);
    SensorsDataAPI.sharedInstance().enableReactNativeAutoTrack();
    SensorsDataAPI.sharedInstance().enableVisualizedAutoTrack();
    SensorsDataAPI.sharedInstance().enableHeatMap();
    SensorsDataAPI.sharedInstance().enableVisualizedAutoTrackConfirmDialog(true);
    SensorsDataAPI.sharedInstance().setSessionIntervalTime(30000);
    // SensorsDataAPI.sharedInstance().enableHeatMap();
  }
}
