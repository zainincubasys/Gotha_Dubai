package gothadubai.incubasys.com.gothadubai;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import Adapters.PhotoViewPagerAdapter;
import Controllers.AlbumsControllers;
import Models.AlbumModel;


public class PhotoPreviewActivity extends Activity {

    ProgressBar progressBar;
    ViewPager pager;
    TextView title, done;
    ImageView leftArrow, rightArrow;
    RelativeLayout rlTopBar, rlBottomBar;
    int listNumb, currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_preview_layout);
        pager = (ViewPager)findViewById(R.id.viewPager);
        rlTopBar = (RelativeLayout)findViewById(R.id.topBar);
        rlBottomBar = (RelativeLayout)findViewById(R.id.bottomLy);
        title = ((TextView)findViewById(R.id.title));
        done = ((TextView)findViewById(R.id.done));
        leftArrow = (ImageView)findViewById(R.id.left_arrow);
        rightArrow = (ImageView)findViewById(R.id.right_arrow);

        listNumb = getIntent().getIntExtra("listNumb", 0);
        currentImage = getIntent().getIntExtra("position", 0);




        final PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(this, AlbumsControllers.Instance().getAlbumModelList().get(listNumb).getPictureModelList(), rlTopBar, rlBottomBar);
        pager.setAdapter(adapter);
        pager.setCurrentItem(currentImage%AlbumsControllers.Instance().getAlbumModelList().get(listNumb).getPictureModelList().size());
        title.setText((currentImage%AlbumsControllers.Instance().getAlbumModelList().get(listNumb).getPictureModelList().size()+1) + " of " + AlbumsControllers.Instance().getAlbumModelList().get(listNumb).getPictureModelList().size());

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getCurrentItem()-1 >=0){
                    pager.setCurrentItem(pager.getCurrentItem()-1);
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getCurrentItem()+1 < AlbumsControllers.Instance().getAlbumModelList().get(listNumb).getPictureModelList().size()){
                    pager.setCurrentItem(pager.getCurrentItem()+1);
                }
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.v("Pager", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                title.setText(( position+1) + " of " + AlbumsControllers.Instance().getAlbumModelList().get(listNumb).getPictureModelList().size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (rlTopBar.getVisibility() == View.VISIBLE) {
                    rlTopBar.setVisibility(View.GONE);
                    rlBottomBar.setVisibility(View.GONE);


                    Animation slide_to_hide = AnimationUtils.loadAnimation(PhotoPreviewActivity.this,
                            R.anim.slide_to_hide);
                    slide_to_hide.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            rlBottomBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    slide_to_hide.setDuration(500);

//                    rlBottomBar.setVisibility(View.GONE);

                    Animation slide_up_hide = AnimationUtils.loadAnimation(PhotoPreviewActivity.this,
                            R.anim.slide_up_hide);
                    slide_up_hide.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            rlTopBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    slide_up_hide.setDuration(500);
                    rlBottomBar.startAnimation(slide_to_hide);
                    rlTopBar.startAnimation(slide_up_hide);

                }
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        progressBar = (ProgressBar)findViewById(R.id.prg);
//
//        ImageView img = (ImageView)findViewById(R.id.full_image_preview);
//        Picasso.with(this)
//                .load("http://www.w3schools.com/css/img_lights.jpg")
//                .into(img, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        progressBar.setVisibility(View.GONE);
//                    }
//                    @Override
//                    public void onError() {
//                    }
//                });
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }
}
