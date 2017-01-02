package gothadubai.incubasys.com.gothadubai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

import CommonUtils.CommonVaraibles;

/**
 * Created by Xain on 07/11/2016.
 */

public class GothaFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
//        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.v("GothaFIrebase", "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    public void sendRegistrationToServer(final String uniqueId) {

        StringRequest req = new StringRequest(Request.Method.POST, CommonVaraibles.BASE_URL + CommonVaraibles.NOTIFICATION_REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(CommonVaraibles.CONSTANT_PARAM_DEVICE_ID, uniqueId);
                params.put(CommonVaraibles.CONSTANT_PARAM_DEVICE_TYPE, "0");
//               uniqueId params.put("email", email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
}
