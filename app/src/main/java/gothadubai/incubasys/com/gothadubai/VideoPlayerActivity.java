package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.vimeo.networking.model.Video;

import org.json.JSONObject;

import CommonUtils.CommonVaraibles;
import CommonUtils.SharedFunctions;
import Controllers.VideosController;
import Handlers.NetworkHandler;
import Interfaces.NetworkResponse;
import Models.VideosModel;

/**
 * Created by Xain on 20/10/2016.
 */

public class VideoPlayerActivity extends Activity implements NetworkResponse{
    private VideoView videoView;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_palyer_layout);
        position = getIntent().getIntExtra("position", 0);
        if(VideosController.Instance().videoList.get(position).getUrlMP4().equals("")) {
            NetworkHandler handler = new NetworkHandler(VideosController.Instance().videoList.get(position).getConfigUrl(), CommonVaraibles.API_TYPE.GET, VideoPlayerActivity.this, VideoPlayerActivity.this, CommonVaraibles.COMMON_TAG_VIDEO_CONFIG);
            handler.execute();
        }else{
            playVideo();
        }




//        WebView webview = (WebView) findViewById(R.id.video_webview);
//
//
//        int widht = 480;//SharedFunctions.screenWidth(VideoPlayerActivity.this);
//        int height = 800;//SharedFunctions.screenHeight(VideoPlayerActivity.this);
//
//        int position = getIntent().getIntExtra("position", 0);
//        VideosModel model =  VideosController.Instance().videoList.get(position);
//        Log.v("iframe", "iframe = " + model.getVideo().embed.html);
//        String []iframe = model.getVideo().embed.html.split("\"");
//        String url = "";
//        for(int i=0; i<iframe.length-1; i++){
//            Log.v("iframe", "iframe = " + iframe[i]);
//            if(url.equals("")){
//                url = iframe[i] + "\"";
//            }else if(iframe[i].contains("https:")){
//                url = url + iframe[i] + "&autoplay=1\"";
//            }
//            else{
//                if(iframe[i].contains("width")){
//                    url = url + iframe[i] + "\"";
//                    url = url + widht+ "\"";
//                    i++;
//                }else if(iframe[i].contains("height")){
//                    url = url + iframe[i] + "\"";
//                    url = url + height + "\"";
//                    i++;
//                }else {
//                    url = url + iframe[i] + "\"";
//                }
//            }
//        }
//
//        url = url + iframe[iframe.length-1];
//
//
//
//        String html = "<html><head></head><body>" + url + "</body></html>";
//
//
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setAppCacheEnabled(true);
//        webview.getSettings().setDomStorageEnabled(true);
//        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
//
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        webview.loadData(html, "text/html; charset=UTF-8", null);
    }

    @Override
    public void apiResult(String json, int pTag) {
        if(pTag == CommonVaraibles.COMMON_TAG_VIDEO_CONFIG){
            Log.v("JSON", "JSON = " + json);
            try {
                JSONObject jsonObj = new JSONObject(json);
                jsonObj = jsonObj.getJSONObject(CommonVaraibles.CONSTANT_PARAM_REQUEST);
                jsonObj = jsonObj.getJSONObject(CommonVaraibles.CONSTANT_PARAM_FILES);
                jsonObj = jsonObj.getJSONObject(CommonVaraibles.CONSTANT_PARAM_HLS);
                VideosController.Instance().videoList.get(position).setUrlMP4(jsonObj.getString(CommonVaraibles.CONSTANT_PARAM_URL));
                playVideo();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void apiError(String msg, int pTag) {
        if(pTag == CommonVaraibles.COMMON_TAG_VIDEO_CONFIG){
            Toast.makeText(VideoPlayerActivity.this, "Vimeo: " + msg, Toast.LENGTH_SHORT).show();
        }
    }


    private void playVideo(){
        videoView = (VideoView)findViewById(R.id.splashId);
        MediaController controller = new MediaController(VideoPlayerActivity.this);
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(VideosController.Instance().videoList.get(position).getUrlMP4()));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                ((ProgressBar)findViewById(R.id.progrBar)).setVisibility(View.GONE);
                videoView.start();

            }
        });


    }
}
