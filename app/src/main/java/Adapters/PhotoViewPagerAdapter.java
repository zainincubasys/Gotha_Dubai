package Adapters;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import Models.PictureModel;
import gothadubai.incubasys.com.gothadubai.PhotoGalleryActivity;
import gothadubai.incubasys.com.gothadubai.R;
import gothadubai.incubasys.com.gothadubai.ZoomableImageView;

/**
 * Created by Xain on 23/09/2016.
 */
public class PhotoViewPagerAdapter extends PagerAdapter {

    Context context;
    List<PictureModel> pictureModelList;
    public static RelativeLayout rlTopBar, rlBottomBar;
    public static Animation slide_down, slide_up, slide_to_hide, slide_up_hide;


    public PhotoViewPagerAdapter(Context c, List<PictureModel> pList, RelativeLayout pRlTopBar, RelativeLayout pRlBottomBar){
        context = c;
        pictureModelList = pList;
        rlTopBar = pRlTopBar;
        rlBottomBar = pRlBottomBar;
        slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);
        slide_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rlBottomBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide_up.setDuration(500);

        slide_down = AnimationUtils.loadAnimation(context,
                R.anim.sliade_down);
        slide_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rlTopBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide_down.setDuration(500);

        slide_to_hide = AnimationUtils.loadAnimation(context,
                R.anim.slide_to_hide);
        slide_to_hide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rlBottomBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide_to_hide.setDuration(500);

        slide_up_hide = AnimationUtils.loadAnimation(context,
                R.anim.slide_up_hide);
        slide_up_hide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rlTopBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        slide_up_hide.setDuration(500);

    }

    @Override
    public int getCount() {
        return pictureModelList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_item, container, false);
        final ZoomableImageView img = (ZoomableImageView)layout.findViewById(R.id.IMAGEID);
        final ImageView full_image = (ImageView) layout.findViewById(R.id.full_image_preview);

        Picasso.with(context)
                .load(pictureModelList.get(position).getUrl()).fit().centerInside()
                .into(full_image, new Callback() {
                    @Override
                    public void onSuccess() {
//                        progressBar.setVisibility(View.GONE);
                        img.setImageBitmap(((BitmapDrawable)full_image.getDrawable()).getBitmap());
                    }
                    @Override
                    public void onError() {
                    }
                });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(rlBottomBar.getVisibility() != View.VISIBLE){
//                    rlTopBar.setVisibility(View.VISIBLE);
//                    rlBottomBar.setVisibility(View.VISIBLE);
//                    rlBottomBar.setAnimation(slide_up);
//                    rlTopBar.setAnimation(slide_down);
//
//                }else{
//
//                    rlTopBar.setVisibility(View.INVISIBLE);
//                    rlBottomBar.setVisibility(View.INVISIBLE);
//
//
//
//
//                    rlBottomBar.setAnimation(slide_to_hide);
//                    rlTopBar.setAnimation(slide_up_hide);
//                }
            }
        });

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "" + position;
    }

    public static void IMAGE_CLICKED(){
        if(rlBottomBar.getVisibility() != View.VISIBLE)
        {
            rlTopBar.setVisibility(View.VISIBLE);
            rlBottomBar.setVisibility(View.VISIBLE);
            rlBottomBar.startAnimation(slide_up);
            rlTopBar.startAnimation(slide_down);

        }else{

            rlTopBar.setVisibility(View.INVISIBLE);
            rlBottomBar.setVisibility(View.INVISIBLE);
            rlBottomBar.startAnimation(slide_to_hide);
            rlTopBar.startAnimation(slide_up_hide);
        }
    }

}
