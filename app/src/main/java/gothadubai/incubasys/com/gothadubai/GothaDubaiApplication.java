package gothadubai.incubasys.com.gothadubai;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.vimeo.networking.Configuration;
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.VimeoClient;

import java.util.HashMap;
import java.util.Map;

import CommonUtils.AccountPreferenceManager;
import CommonUtils.CommonVaraibles;
import gothadubai.incubasys.com.gothadubai.vimeonetworking.AndroidGsonDeserializer;
import gothadubai.incubasys.com.gothadubai.vimeonetworking.NetworkingLogger;
import gothadubai.incubasys.com.gothadubai.vimeonetworking.TestAccountStore;


public class GothaDubaiApplication extends Application {

    private static final String SCOPE = "private public create edit delete interact";

    private static final boolean IS_DEBUG_BUILD = false;
    // Switch to true to see how access token auth works.
    private static final boolean ACCESS_TOKEN_PROVIDED = false;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        AccountPreferenceManager.initializeInstance(mContext);

        FacebookSdk.sdkInitialize(mContext);
        AppEventsLogger.activateApp(mContext);

        Configuration.Builder configBuilder;
        if (ACCESS_TOKEN_PROVIDED) {
            configBuilder = getAccessTokenBuilder();
        } else {
            configBuilder = getClientIdAndClientSecretBuilder();
        }
        if (IS_DEBUG_BUILD) {
            configBuilder.enableCertPinning(false);
            configBuilder.setLogLevel(Vimeo.LogLevel.VERBOSE);
        }
        VimeoClient.initialize(configBuilder.build());
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.v("Token", "Token = " + token + "");

    }

    public Configuration.Builder getAccessTokenBuilder() {
        String accessToken = getString(R.string.access_token);
        return new Configuration.Builder(accessToken);
    }

    public Configuration.Builder getClientIdAndClientSecretBuilder() {
        String clientId = /*"ffcd59a3eee0c3cd183cff5a06047628b7311274";*/ getString(R.string.client_identifier);
        String clientSecret = /*"7ISl/dn51FbMxxW0j4ozXizs7CAaKRn1I8Xzd/ZK+dvCHKPyXfSy14p/UwmBCL8SXaHyS5IVCnX+9ULbwSx3BsSxf/1bQwPRtZ2kRf1S6SCcFiLkkm03QwdvrIkA8JV1";*/getString(R.string.client_secret);
        String codeGrantRedirectUri = getString(R.string.deeplink_redirect_scheme) + "://" +
                getString(R.string.deeplink_redirect_host);
        TestAccountStore testAccountStore = new TestAccountStore(this.getApplicationContext());
        Configuration.Builder configBuilder =
                new Configuration.Builder(clientId, clientSecret, SCOPE, testAccountStore,
                        new AndroidGsonDeserializer());
        configBuilder.setCacheDirectory(this.getCacheDir())
                .setUserAgentString(getUserAgentString(this)).setDebugLogger(new NetworkingLogger())
                .setCodeGrantRedirectUri(codeGrantRedirectUri);

        return configBuilder;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static String getUserAgentString(Context context) {
        String packageName = context.getPackageName();

        String version = "unknown";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("Unable to get packageInfo: " + e.getMessage());
        }

        String deviceManufacturer = Build.MANUFACTURER;
        String deviceModel = Build.MODEL;
        String deviceBrand = Build.BRAND;

        String versionString = Build.VERSION.RELEASE;
        String versionSDKString = String.valueOf(Build.VERSION.SDK_INT);

        return packageName + " (" + deviceManufacturer + ", " + deviceModel + ", " + deviceBrand +
                ", " + "Android " + versionString + "/" + versionSDKString + " Version " + version +
                ")";
    }

}
