package gothadubai.incubasys.com.gothadubai;


import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.*;

import CommonUtils.CommonVaraibles;
import CommonUtils.SharedFunctions;
import Handlers.NetworkHandler;
import Interfaces.NetworkResponse;
import Views.CameraPreview;


public class CameraActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private String TAG = "CameraActivity", textViewDate = "";
    private ImageView cameraSwitchBtn, flashBtn, showImage, shareImage;
    public static int currentCameraId = 1;
    private FrameLayout preview;
    RelativeLayout rl, capture;
    LinearLayout llView;
    TextView tv, save, cancel, gothaText;
    private Bitmap _bmp;
    Animation slide_to_hide, fadeIn, fadeOut, push_up_in;
    boolean isflashOn = false, saveToTakePicture = false;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.camera_activity_layout);
        rl = (RelativeLayout)findViewById(R.id.v);
        preview = (FrameLayout) findViewById(R.id.camera_preview);
        capture = (RelativeLayout) findViewById(R.id.btn_capture);
        cameraSwitchBtn = (ImageView)findViewById(R.id.camera_switch_btn);
        flashBtn = (ImageView)findViewById(R.id.flash_btn);
        save = (TextView)findViewById(R.id.save);
        cancel = (TextView)findViewById(R.id.cancel);
        showImage = (ImageView)findViewById(R.id.showImage);
        llView = (LinearLayout)findViewById(R.id.ll_view);
        shareImage = (ImageView)findViewById(R.id.share_btn);
        gothaText = (TextView)findViewById(R.id.gothaText);
        Log.v("Width", "Width = "+ SharedFunctions.screenWidth(this) + "Height = "+ SharedFunctions.screenHeight(this));
        if(SharedFunctions.screenWidth(this)>1080){
            preview.setVisibility(View.GONE);
            preview = (FrameLayout) findViewById(R.id.camera_preview2);
            preview.setVisibility(View.VISIBLE);
        }

        mCamera = getCameraInstance();
        mPreview = new CameraPreview(this, mCamera);
//        int _width = mPreview.getWidth();
//        int _height = mPreview.getHeight();
//        preview.setLayoutParams( new RelativeLayout.LayoutParams(_width, _height));
        preview.addView(mPreview);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(_bmp);
//                llView.setVisibility(View.GONE);
                llView.startAnimation(fadeOut);
//                capture.setVisibility(View.VISIBLE);

                showImage.setVisibility(View.GONE);
                showImage.setVisibility(View.GONE);

                capture.startAnimation(push_up_in);
                resetCamera();
                AlertDialog.Builder builder = new AlertDialog.Builder(CameraActivity.this);
                builder.setMessage("Picture saved successfully!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCamera();
//                llView.setVisibility(View.GONE);
                llView.startAnimation(fadeOut);
//                capture.setVisibility(View.VISIBLE);
                capture.startAnimation(push_up_in);
                showImage.setVisibility(View.GONE);
            }
        });

        capture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
               if(saveToTakePicture) {
                   textViewDate = tv.getText().toString();
                   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                   String currentDate = sdf.format(new Date());
                   SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
                   String currentTime = _sdf.format(new Date());
                   tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                   mCamera.takePicture(null, null, mPicture);
                   saveToTakePicture = false;
               }

            }
        });

        flashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    try {
                        if (isFlashSupported()) {
                            Camera.Parameters params = mCamera.getParameters();
                            if (params.getFlashMode().equals(Camera.Parameters.FLASH_MODE_ON)) {
                                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                flashBtn.setImageResource(R.drawable.flash_off);
                                isflashOn = false;
                            } else {
                                params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                                isflashOn = true;
                                flashBtn.setImageResource(R.drawable.flash_on);
                            }
                            mCamera.setParameters(params);
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    flashBtn.setImageResource(R.drawable.flash_off);
                }
            }
        });

        cameraSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Camera.getNumberOfCameras() == 1){

                }else{
                    if (mCamera != null) {
                        mCamera.stopPreview();
                        mCamera.release();
                        mCamera = null;
                    }
                    if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                        flashBtn.setImageResource(R.drawable.flash_off);
                    } else {
                        currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                    }

                    try {
                        mCamera = Camera.open(currentCameraId);
                        mCamera.setDisplayOrientation(90);
                        mCamera.setPreviewDisplay(mPreview.getHolder());
                        mCamera.startPreview();
                        saveToTakePicture = true;
                    }
                    catch (Exception e) { e.printStackTrace(); }
                }
            }
        });

        ((ImageView)findViewById(R.id.close_icon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
//                        onDestroy();
                    }
                }, 1000);

            }
        });

        tv = (TextView) findViewById(R.id.gothaText);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "KGHopeForACure.ttf");
        tv.setTypeface(face);

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!v.isClickable()) {
                    return;
                }
                shareImageViaIntent();
                v.setClickable(false);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setClickable(true);
                    }
                }, CommonVaraibles.DELAY_IN_MS);


            }
        });

        createAnimation();
        Typeface _face = Typeface.createFromAsset(getAssets(),
                "gothic-regular.ttf");
        save.setTypeface(_face);
        cancel.setTypeface(_face);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());

        gothaText.setText("Gotha Club \n" + currentDate);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private boolean isFlashSupported() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else {
            return false;
        }
    }
    String tempImagePath = "";
    private synchronized void shareImageViaIntent(){
        tempImagePath = savetempImage();
        if(!tempImagePath.equals("")) {
            File file = new File(tempImagePath);
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            Uri screenshotUri = Uri.fromFile(file);
//            try {
//                InputStream stream = getContentResolver().openInputStream(screenshotUri);
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            sharingIntent.setType("image/jpeg");
            sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "www.gothaclubdubai.com");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
            startActivityForResult(Intent.createChooser(sharingIntent, "Share image using"), 1001);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001){
            try{
                File file = new File(tempImagePath);
                file.delete();
                tempImagePath = "";
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public String savetempImage(){
        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (pictureFile == null){
            return "";
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        _bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(byteArray);
            fos.close();



        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }

        return pictureFile.getAbsolutePath();
    }

    public void addImageToGallery(String filePath, Context context) {

        MediaStore.Images.Media.insertImage(getContentResolver(), _bmp,
                filePath + ".jpg", null);

//        ContentValues values = new ContentValues();
//
//        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        values.put(MediaStore.MediaColumns.DATA, filePath);
//
//        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }



    public Camera getCameraInstance(){
        Camera c = null;
//        try{
//            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
//            c.stopPreview();
//            c.setPreviewCallback(null);
//            c.release();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        try {
            c=null;
            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
//
//            Camera.Parameters parameters = mCamera.getParameters();
//            parameters.setPreviewFormat(ImageFormat.NV16);
//            c.setParameters(parameters);
//            c.setDisplayOrientation(90);
//            c.startPreview();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            Log.v("Error", "Exception = " + e.toString());
        }
        return c;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
//            String tt = tv.getText().toString();
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String currentDate = sdf.format(new Date());
//            SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//            String currentTime = _sdf.format(new Date());
//            tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
            int picHeight = 660;
            if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
                picHeight = 800;
            }
            llView.setVisibility(View.VISIBLE);
            llView.startAnimation(fadeIn);
            capture.startAnimation(slide_to_hide);

            Bitmap frame= BitmapFactory.decodeResource((CameraActivity.this).getResources(), R.drawable.camera_bg);
            Bitmap bmp;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);

            bmp = rotateBitmap(bmp, 90);
//            int  orgWidth = bmp.getWidth();

//            if(bmp.getWidth() == 2448){
//                bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * 1080) / bmp.getHeight(), 1080, false);
//            }
            Log.v("Picture Size", "Picture Size = " + bmp.getWidth() + " Height = " + bmp.getHeight());
            if(SharedFunctions.screenWidth(CameraActivity.this)==1080 && SharedFunctions.screenHeight(CameraActivity.this) == 1776){
                bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * 1080) / bmp.getHeight(), 1080, false);
                int heightFactor = (bmp.getHeight() / 6);

                if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth() - 100, heightFactor * 5 );
                    bmp = rotateBitmap(bmp, 180);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth() - 100, heightFactor * 4 + 175);
                    bmp = rotateBitmap(bmp, 180);
                } else {
                    bmp = rotateBitmap(bmp, 180);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4  + 175 );
                }
                frame = Bitmap.createScaledBitmap(frame, bmp.getWidth(), bmp.getHeight() , false);

                bmp = combineImages(frame, bmp);
                Canvas c = new Canvas(bmp);
                if (SharedFunctions.screenHeight(CameraActivity.this) >= 1770) {
//                    String tt = tv.getText().toString();
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    String currentDate = sdf.format(new Date());
//                    SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                    String currentTime = _sdf.format(new Date());
//                    tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);

                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    tv.setDrawingCacheEnabled(true);
                    c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 125, null);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                    tv.setText(textViewDate);
                } else {
//                    String tt = tv.getText().toString();
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    String currentDate = sdf.format(new Date());
//                    SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                    String currentTime = _sdf.format(new Date());

//                    tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                    tv.setDrawingCacheEnabled(true);
                    c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 130, null);
                    tv.setText(textViewDate);
                }
            }
            else if(bmp.getHeight()>1920) {
                bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * 1080) / bmp.getHeight(), 1080, false);
                int heightFactor = (bmp.getHeight() / 6);
                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 5);
                if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    bmp = rotateBitmap(bmp, 180);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4);
                    bmp = rotateBitmap(bmp, 180);
                } else {
                    bmp = rotateBitmap(bmp, 180);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4);
                }
                frame = Bitmap.createScaledBitmap(frame, bmp.getWidth(), bmp.getHeight(), false);

                bmp = combineImages(frame, bmp);
                Canvas c = new Canvas(bmp);
                if (SharedFunctions.screenHeight(CameraActivity.this) >= 1900) {

//                    String tt = tv.getText().toString();
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    String currentDate = sdf.format(new Date());
//                    SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                    String currentTime = _sdf.format(new Date());

//                    tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6);
                    tv.setDrawingCacheEnabled(true);
                    c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 125, null);
                    tv.setText(textViewDate);
                } else {
//                    String tt = tv.getText().toString();
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                    String currentDate = sdf.format(new Date());
//                    SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                    String currentTime = _sdf.format(new Date());

//                    tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                    tv.setDrawingCacheEnabled(true);
                    c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 130, null);
                    tv.setText(textViewDate);
                }
            }
            else if(bmp.getHeight()==1920){
                if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * 1080) / bmp.getHeight(), 1080, false);
                    int heightFactor = (bmp.getHeight() / 6);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 5 - 10);
                    if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        bmp = rotateBitmap(bmp, 180);
                        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4);
                        bmp = rotateBitmap(bmp, 180);
                    } else {
                        bmp = rotateBitmap(bmp, 180);
                        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4);
                    }
                    frame = Bitmap.createScaledBitmap(frame, bmp.getWidth(), bmp.getHeight() - 50, false);
                    bmp = combineImages(frame, bmp);
                    Canvas c = new Canvas(bmp);
                    if (SharedFunctions.screenHeight(CameraActivity.this) >= 1900) {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());

//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 125, null);
                        tv.setText(textViewDate);
                    } else {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());

//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 130, null);
                        tv.setText(textViewDate);
                    }
                }else{
                    bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * 1080) / bmp.getHeight(), 1080, false);
                    int heightFactor = (bmp.getHeight() / 6);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 5 );
                    if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        bmp = rotateBitmap(bmp, 180);
                        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4);
                        bmp = rotateBitmap(bmp, 180);
                    } else {
                        bmp = rotateBitmap(bmp, 180);
                        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), heightFactor * 4);
                    }
                    frame = Bitmap.createScaledBitmap(frame, bmp.getWidth(), bmp.getHeight() - 50, false);
                    bmp = combineImages(frame, bmp);
                    Canvas c = new Canvas(bmp);
                    if (SharedFunctions.screenHeight(CameraActivity.this) >= 1900) {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());

//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 125, null);
                        tv.setText(textViewDate);
                    } else {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());

//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 130, null);
                        tv.setText(textViewDate);
                    }
                }
            }
            else {
                if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    bmp = Bitmap.createScaledBitmap(bmp, 600, 800, false);
                    bmp = Bitmap.createScaledBitmap(bmp, 600, 800, false);

//                    bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * picHeight) / bmp.getHeight(), picHeight, false);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), picHeight);
                    if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        bmp = rotateBitmap(bmp, 180);
                    }
                    frame = Bitmap.createScaledBitmap(frame, 600, bmp.getHeight() + 50, false);
                    bmp = combineImages(frame, bmp);
                    Canvas c = new Canvas(bmp);
                    if (SharedFunctions.screenHeight(CameraActivity.this) >= 1900) {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());
//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 125, null);
                        tv.setText(textViewDate);
                    } else {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());
//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 130, null);
                        tv.setText(textViewDate);
                    }
                }else{

                    bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * picHeight) / bmp.getHeight(), picHeight, false);
                    if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        bmp = rotateBitmap(bmp, 180);
                    }
                    bmp = Bitmap.createBitmap(bmp, 0, 0, (bmp.getWidth() * picHeight) / bmp.getHeight(), picHeight - 170);
                    bmp = Bitmap.createScaledBitmap(bmp, (bmp.getWidth() * picHeight) / bmp.getHeight(), picHeight - 100, false);
                    frame = Bitmap.createScaledBitmap(frame, bmp.getWidth(), picHeight, false);
                    bmp = combineImages(frame, bmp);
                    Canvas c = new Canvas(bmp);
                    if (SharedFunctions.screenHeight(CameraActivity.this) >= 1900) {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());
//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 6);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 125, null);
                        tv.setText(textViewDate);
                    } else {
//                        String tt = tv.getText().toString();
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        String currentDate = sdf.format(new Date());
//                        SimpleDateFormat _sdf = new SimpleDateFormat("HH:mm");
//                        String currentTime = _sdf.format(new Date());
//                        tv.setText("Gotha Club \n" + currentDate + "\n" + currentTime);
                        tv.setDrawingCacheEnabled(true);
                        c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 130, null);
                        tv.setText(textViewDate);
                    }

                }
            }
            _bmp = bmp;

            showImage.setVisibility(View.VISIBLE);
            showImage.setImageBitmap(_bmp);
            if(SharedFunctions.screenHeight(CameraActivity.this)>=1900) {
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            }
//            saveImage(bmp);

//            Bitmap frame= BitmapFactory.decodeResource((CameraActivity.this).getResources(),
//                    R.drawable.camera_bg);
//            Bitmap bmp;
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inMutable = true;
//            bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
//
//            bmp = rotateBitmap(bmp, 90);
////            bmp = rotateBitmap(bmp, 180);
////            saveImage(bmp);
//            bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight()/6*5);
//            bmp = rotateBitmap(bmp, 180);
//            if(bmp.getHeight()> 1300) {
//                Log.v("Picture height", "Bmp height = " + bmp.getHeight() );
//                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight() / 6 * 4 + (bmp.getHeight() / 6) / 2);
//            }
//
//            if(frame.getHeight() > bmp.getHeight()) {
//                frame = Bitmap.createScaledBitmap(frame, frame.getWidth(), bmp.getHeight(), false);
//            }else{
//                bmp = Bitmap.createScaledBitmap(bmp, frame.getWidth(), frame.getHeight(), false);
//            }
//            bmp = combineImages(frame, bmp);
//            Canvas c = new Canvas(bmp);
//            tv.setDrawingCacheEnabled(true);
//            c.drawBitmap(tv.getDrawingCache(), 30, bmp.getHeight() - 100, null);
//            saveImage(bmp);
        }
    };

    public void saveImage(Bitmap bmp){
//        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//        if (pictureFile == null){
//            return;
//        }
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        try {
//            FileOutputStream fos = new FileOutputStream(pictureFile);
//            fos.write(byteArray);
//            fos.close();
//
//
//
//        } catch (FileNotFoundException e) {
//            Log.d(TAG, "File not found: " + e.getMessage());
//        } catch (IOException e) {
//            Log.d(TAG, "Error accessing file: " + e.getMessage());
//        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        addImageToGallery(timeStamp , this);


    }
    public Bitmap rotateBitmap(Bitmap source, float angle) {

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    float totalRotated = 0;

    public Bitmap rotate(Bitmap mBitmap, float degrees){
        if(mBitmap != null){
            totalRotated = (totalRotated + degrees) % 360;
            double radians = Math.toRadians(totalRotated);
            double sin = Math.abs(Math.sin(radians));
            double cos = Math.abs(Math.cos(radians));
            double newWidth = mBitmap.getWidth() * cos + mBitmap.getHeight() * sin;
            double newHeight = mBitmap.getWidth() * sin + mBitmap.getHeight() * cos;
            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.setRotate(totalRotated);
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,(int) newWidth,(int)newHeight, matrix, true);
        }
        return null;
    }

    public Bitmap combineImages(Bitmap frame, Bitmap orgPic) {
        Bitmap cs = null;
        int width = frame.getWidth(), height = frame.getHeight();
        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        comboImage.drawBitmap(orgPic, 0f, 0f, null);
        comboImage.drawBitmap(frame, 0f, 0f, null);
        return cs;
    }


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }
        return mediaFile;
    }

    public Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public void resetCamera(){
        if(Camera.getNumberOfCameras() == 1){

        }else{
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
            try {
                if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                    mCamera = Camera.open(currentCameraId);
                    mCamera.setDisplayOrientation(90);
                    mCamera.setPreviewDisplay(mPreview.getHolder());
                    mCamera.startPreview();
                    saveToTakePicture = true;
                    Camera.Parameters params = mCamera.getParameters();
                    if (isFlashSupported()) {
                        if (isflashOn) {
                            params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                            isflashOn = true;
                            flashBtn.setImageResource(R.drawable.flash_on);

                        } else {
                            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            flashBtn.setImageResource(R.drawable.flash_off);
                            isflashOn = false;
                        }
                        mCamera.setParameters(params);
                    }
                }else {
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                    mCamera = Camera.open(currentCameraId);
                    mCamera.setDisplayOrientation(90);
                    mCamera.setPreviewDisplay(mPreview.getHolder());
                    mCamera.startPreview();
                    saveToTakePicture = true;
                }

            }
            catch (Exception e) { e.printStackTrace(); }
        }
    }


    private void createAnimation(){

        slide_to_hide = AnimationUtils.loadAnimation(CameraActivity.this,
                R.anim.slide_to_hide);
        slide_to_hide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                capture.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide_to_hide.setDuration(200);

        push_up_in = AnimationUtils.loadAnimation(CameraActivity.this,
                R.anim.push_up_in);
        push_up_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                capture.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        push_up_in.setDuration(200);

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(200);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(100);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Camera.getNumberOfCameras() == 1) {

                    } else {
                        if (mCamera != null) {
                            mCamera.stopPreview();
                            mCamera.release();
                            mCamera = null;
                        }
                        try {

                            if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                                currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                                mCamera = Camera.open(currentCameraId);
                                mCamera.setDisplayOrientation(90);
                                mCamera.setPreviewDisplay(mPreview.getHolder());
                                mCamera.startPreview();
                                saveToTakePicture = true;

                            } else {
                                currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                                mCamera = Camera.open(currentCameraId);
                                mCamera.setDisplayOrientation(90);
                                mCamera.setPreviewDisplay(mPreview.getHolder());
                                mCamera.startPreview();
                                saveToTakePicture = true;

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            saveToTakePicture = false;
                        }
                    }
                }
            }, 1000);
//        }
    }

    @Override
    protected void onDestroy() {
        if (mCamera != null) {
            mCamera.release();
        }
        super.onDestroy();
    }
}
