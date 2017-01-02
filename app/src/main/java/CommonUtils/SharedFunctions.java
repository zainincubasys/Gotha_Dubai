package CommonUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Xain on 21/09/2016.
 */
public class SharedFunctions {

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int screenWidth(Context c){
        Display display = ((Activity)c).getWindowManager().getDefaultDisplay();
        return display.getWidth();

    }

    public static int screenHeight(Context c) {
        Display display = ((Activity) c).getWindowManager().getDefaultDisplay();
        return display.getHeight();
    }

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = SharedFunctions.getConnectivityStatus(context);
        String status = null;
        if (conn == SharedFunctions.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == SharedFunctions.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == SharedFunctions.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }


}
