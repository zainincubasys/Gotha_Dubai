package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.error.VimeoError;

import org.json.JSONArray;
import org.json.JSONObject;

import CommonUtils.CommonVaraibles;
import CommonUtils.SharedFunctions;
import Controllers.AlbumsControllers;
import Controllers.EventsController;
import Controllers.NotificationController;
import Controllers.PartnersController;
import Controllers.VideosController;
import Handlers.NetworkHandler;
import Interfaces.NetworkResponse;

/**
 * Created by Xain on 20/09/2016.
 */
public class SplashActivity extends Activity implements NetworkResponse {

    private VideoView videoView;
    private final int SPLASH_DISPLAY_LENGTH = 5500;

    private boolean isVideoCompleted = false, isGalleryTagCompleted = false,
            isVimeoVideoAPICompleted = false, isInternetConnected = true,
            isPartnersAPICompleted = false, isEventAPICompleted = false,
            isNotifAPICompleted = false;


    public static final String STAFF_PICKS_VIDEO_URI = "/users/user52127078/videos";
//    public static final String STAFF_PICKS_VIDEO_URI = "/channels/927/videos"; // 927 == staffpicks

    private VimeoClient mApiClient = VimeoClient.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.splash_activity_layout);
        super.onCreate(savedInstanceState);
        videoView = (VideoView)findViewById(R.id.splashId);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.splash_loading;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isVideoCompleted = true;
                if(isInternetConnected){
                    startNextActivity();
                }else{
                    showDialog();
                }
            }
        });
        networkCalls();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setMessage("No Internet Connected")
                .setTitle("Error");
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                networkCalls();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void networkCalls(){


        if(SharedFunctions.getConnectivityStatus(SplashActivity.this) != SharedFunctions.TYPE_NOT_CONNECTED) {
            isInternetConnected = true;
            NetworkHandler galleryHandler = new NetworkHandler(CommonVaraibles.BASE_URL + CommonVaraibles.METHOD_GALLERY, CommonVaraibles.API_TYPE.GET, this, this, CommonVaraibles.COMMON_TAG_GALLERY);
            galleryHandler.execute();
            handleCodeGrantIfNecessary();

            NetworkHandler partnerHandler = new NetworkHandler(CommonVaraibles.BASE_URL + CommonVaraibles.METHOD_PARTNERS, CommonVaraibles.API_TYPE.GET, this, this, CommonVaraibles.COMMON_TAG_PARTNER);
            partnerHandler.execute();

            NetworkHandler eventHandler = new NetworkHandler(CommonVaraibles.BASE_URL + CommonVaraibles.METHOD_EVENTS, CommonVaraibles.API_TYPE.GET, this, this, CommonVaraibles.COMMON_TAG_EVENTS);
            eventHandler.execute();

            NetworkHandler notificationHandler = new NetworkHandler(CommonVaraibles.BASE_URL + CommonVaraibles.METHOD_NOTIFICATIONS, CommonVaraibles.API_TYPE.GET, this, this, CommonVaraibles.COMMON_TAG_NOTIFICATIONS);
            notificationHandler.execute();

            fetchStaffPicks();

        }else{
            isInternetConnected = false;
            if(isVideoCompleted){
                showDialog();
            }

        }

    }

    private void handleCodeGrantIfNecessary() {
        if (getIntent() != null) {
            String action = getIntent().getAction();
            Uri uri = getIntent().getData();
            if (Intent.ACTION_VIEW.equals(action) && uri != null) {
                // This is coming from a deep link
                authenticateWithCodeGrant(uri);
            }
        }
    }

    private void authenticateWithCodeGrant(Uri uri) {

        if (uri.getQuery() == null || uri.getQuery().isEmpty()) {
            toast("Bad deep link - no query parameters");
            return;
        }
        mApiClient.authenticateWithCodeGrant(uri.toString(), new AuthCallback() {
            @Override
            public void success() {
                toast("Code Grant Success");
            }

            @Override
            public void failure(VimeoError error) {
                toast("Code Grant Failure");

            }
        });
    }

    private void toast(String string) {
        Toast.makeText(SplashActivity.this, string, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void apiResult(String json, int pTag) {
        try {
            if(pTag == CommonVaraibles.COMMON_TAG_GALLERY) {
                JSONObject jObj = new JSONObject(json);
                JSONArray jArray = jObj.getJSONArray(CommonVaraibles.CONSTANT_PARAM_GALLERIES);
                AlbumsControllers.Instance().parseAlbums(jArray);
                isGalleryTagCompleted = true;
                startNextActivity();
            }
            else if(pTag == CommonVaraibles.COMMON_TAG_PARTNER){
                JSONObject jObj = new JSONObject(json);
                JSONArray jArray = jObj.getJSONArray(CommonVaraibles.CONSTANT_PARAM_PARTNERS);
                PartnersController.Instance().parsePartners(jArray);
                isPartnersAPICompleted = true;
                startNextActivity();
            }
            else if(pTag == CommonVaraibles.COMMON_TAG_EVENTS){
                JSONObject jObj = new JSONObject(json);
                JSONArray jArray = jObj.getJSONArray(CommonVaraibles.CONSTANT_PARAM_EVENTS);
                EventsController.Instance().parseEvents(jArray);
                isEventAPICompleted = true;
                startNextActivity();

            }
            else if(pTag == CommonVaraibles.COMMON_TAG_NOTIFICATIONS){
                JSONObject jObj = new JSONObject(json);
                NotificationController.Instance().parseNotifications(jObj);
                isNotifAPICompleted = true;
                startNextActivity();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void apiError (String msg, int pTag){
        if(pTag == CommonVaraibles.COMMON_TAG_GALLERY) {
            Toast.makeText(SplashActivity.this, "Gallery: " + msg, Toast.LENGTH_SHORT).show();
        }
        else if(pTag == CommonVaraibles.COMMON_TAG_PARTNER){
            Toast.makeText(SplashActivity.this, "Partner: " + msg, Toast.LENGTH_SHORT).show();
        }
        else if(pTag == CommonVaraibles.COMMON_TAG_EVENTS){
            Toast.makeText(SplashActivity.this, "Events: " + msg, Toast.LENGTH_SHORT).show();

        }
    }


    public void startNextActivity(){
        if(isVideoCompleted ){//&& isGalleryTagCompleted && isPartnersAPICompleted && isEventAPICompleted && /*isNotifAPICompleted &&*/ isVimeoVideoAPICompleted) {
            Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }
    }

//    https://player.vimeo.com/video/75658473/config
    private void fetchStaffPicks() {

        mApiClient.fetchNetworkContent(STAFF_PICKS_VIDEO_URI, new ModelCallback<VideoList>(VideoList.class) {
            @Override
            public void success(VideoList videoList) {
                String iframe = "";
                if (videoList != null && videoList.data != null) {
                    VideosController.Instance().videoList.clear();
                    boolean addNewLine = false;
                    for (Video video : videoList.data) {
                        VideosController.Instance().addVideo(video);
                    }
                }
                isVimeoVideoAPICompleted = true;
                startNextActivity();

            }

            @Override
            public void failure(VimeoError error) {

                toast("Video Failure");
            }
        });
    }

    private void authenticateWithClientCredentials() {
        mApiClient.authorizeWithClientCredentialsGrant(new AuthCallback() {
            @Override
            public void success() {
                toast("Client Credentials Authorization Success");
            }

            @Override
            public void failure(VimeoError error) {
                toast("Client Credentials Authorization Failure");
            }
        });
    }



}
