package Handlers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import CommonUtils.CommonVaraibles;
import Interfaces.NetworkResponse;

/**
 * Created by Xain on 15/10/2016.
 */

public class NetworkHandler extends AsyncTask<String, String, String> {

    private Context context;
    private String url;
    NetworkResponse _delegate;
    private int tag;

    public NetworkHandler() {

    }

    public NetworkHandler(String pUrl, CommonVaraibles.API_TYPE pType, Context pContext, NetworkResponse pDelegate, int pTag) {
        context = pContext;
        url = pUrl;
        _delegate = pDelegate;
        tag = pTag;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        Log.v("Url", "URL : " + url);
        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);

            result = EntityUtils.toString(response.getEntity());


        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(tag == CommonVaraibles.COMMON_TAG_RESERVATION) {
            Log.v("JSON", url + " JSON = " + s);
            _delegate.apiResult(s, tag);
        }else {

            boolean isSuccess = false;
            Log.v("JSON", url + " JSON = " + s);
            JSONObject json = null;
            try {
                json = new JSONObject(s);
                if (tag == CommonVaraibles.COMMON_TAG_VIDEO_CONFIG) {

                    _delegate.apiResult(s, tag);
                    isSuccess = true;
                } else {
                    String status = json.getString(CommonVaraibles.CONSTANT_PARAM_STATUS);
                    if (status.equals(CommonVaraibles.CONSTANT_PARAM_SUCCESS)) {
                        _delegate.apiResult(s, tag);
                        isSuccess = true;
                    } else {
                        isSuccess = false;
                    }
                }
            } catch (Exception e) {
                isSuccess = false;

                e.printStackTrace();
            }
            String msg = "";


            if (isSuccess == false) {
                try {
                    json = new JSONObject(s);
                    msg = json.getString("message");
                    _delegate.apiError(msg, tag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage(msg)
//                        .setTitle("Error");
//                builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        ((Activity) context).finish();
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();

            }
        }
    }

}
