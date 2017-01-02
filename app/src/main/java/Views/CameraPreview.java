package Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import gothadubai.incubasys.com.gothadubai.CameraActivity;


public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CameraPreview";

    private Context mContext;
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private List<Camera.Size> mSupportedPreviewSizes;
    private Camera.Size mPreviewSize;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mContext = context;
        mCamera = camera;
        if(mCamera!=null) {
            mSupportedPreviewSizes = mCamera.getParameters().getSupportedPreviewSizes();
            for (Camera.Size str : mSupportedPreviewSizes) {
                Log.e(TAG, str.width + "/" + str.height);
            }

            mHolder = getHolder();
            mHolder.addCallback(this);
            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {

//        mCamera = Camera.open();
//        try {
//            mCamera.setPreviewDisplay(holder);
//        } catch (IOException exception) {
//            mCamera.release();
//            mCamera = null;
//        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            if (mCamera != null) {
//                if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                    mCamera.stopPreview();
//                    mCamera.release();
//                }
                mCamera = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.e(TAG, "surfaceChanged => w=" + w + ", h=" + h);
        if (mHolder.getSurface() == null){
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e){

        }
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            if(CameraActivity.currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);

                parameters.setPreviewFormat(ImageFormat.JPEG);
                mCamera.setParameters(parameters);

                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
                mCamera.setPreviewDisplay(mHolder);
            }else {
                parameters.setPreviewFormat(ImageFormat.JPEG);
                mCamera.setParameters(parameters);

                parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.width);
                mCamera.startPreview();
                //mPreviewSize.height);

            }


        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = (resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec)) ;

        if (mSupportedPreviewSizes != null) {
            mPreviewSize = /*determineBestPictureSize(mCamera.getParameters());//*/ getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
//            mPreviewSize = determineBestSize(mSupportedPreviewSizes);
        }


        float ratio;
        if(mPreviewSize.height >= mPreviewSize.width)
            ratio = (float) mPreviewSize.height / (float) mPreviewSize.width;
        else
            ratio = (float) mPreviewSize.width / (float) mPreviewSize.height;

        setMeasuredDimension(width, (int) (width * ratio));
    }

    public Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {

        double ASPECT_TOLERANCE =  0.5;
        if(CameraActivity.currentCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
            ASPECT_TOLERANCE = 0.5;
        }
        double targetRatio = (double) h / w;

        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;

            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }

        return optimalSize;
    }

//    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
//        final double ASPECT_TOLERANCE = 0.1;
//        double targetRatio = (double) (h) / w;
//
//        if (sizes == null)
//            return null;
//
//        Camera.Size optimalSize = null;
//        double minDiff = Double.MAX_VALUE;
//
//        int targetHeight = h;
//
//        for (Camera.Size size : sizes) {
//            double ratio = (double) (size.height) / size.width;
//            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
//                continue;
//
//            if (Math.abs((size.height) - targetHeight) < minDiff) {
//                optimalSize = size;
//                minDiff = Math.abs((size.height) - targetHeight);
//            }
//        }
//
//        if (optimalSize == null) {
//            minDiff = Double.MAX_VALUE;
//            for (Camera.Size size : sizes) {
//                if (Math.abs((size.height) - targetHeight) < minDiff) {
//                    optimalSize = size;
//                    minDiff = Math.abs((size.height) - targetHeight);
//                }
//            }
//        }
//
//        return optimalSize;
//    }



    public Camera.Size determineBestPictureSize(Camera.Parameters parameters) {
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();

        return determineBestSize(sizes);
    }

    protected Camera.Size determineBestSize(List<Camera.Size> sizes) {
        Camera.Size bestSize = null;
        long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long availableMemory = Runtime.getRuntime().maxMemory() - used;
        for (Camera.Size currentSize : sizes) {
            int newArea = currentSize.width * currentSize.height;
            long neededMemory = newArea * 4 * 4; // newArea * 4 Bytes/pixel * 4 needed copies of the bitmap (for safety :) )
            boolean isDesiredRatio = (currentSize.width / 4) == (currentSize.height / 4);
            boolean isBetterSize = (bestSize == null || currentSize.width > bestSize.width);
            boolean isSafe = neededMemory < availableMemory;
            if (isDesiredRatio && isBetterSize && isSafe) {
                bestSize = currentSize;
            }
        }
        if (bestSize == null) {
            return sizes.get(0);
        }
        return bestSize;
    }
}