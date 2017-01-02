package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Adapters.EventViewPagerAdapter;
import CommonUtils.CommonVaraibles;
import Controllers.EventsController;
import Models.AlbumModel;


public class EventActivity extends Activity {



    private ViewPager pager;
    private ImageView shareBtn, tableBooking, closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_activity_layout);

        pager = (ViewPager)findViewById(R.id.viewPager);
        shareBtn = (ImageView)findViewById(R.id.share_btn);
        tableBooking = (ImageView)findViewById(R.id.table_booking);
        closeBtn = ((ImageView)findViewById(R.id.down_icon));



        if(EventsController.Instance().eventsList.size()>0) {

            final EventViewPagerAdapter adapter = new EventViewPagerAdapter(this);
            pager.setAdapter(adapter);

            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (!v.isClickable()) {
                        return;
                    }
                    shareItem();


                    v.setClickable(false);
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            v.setClickable(true);
                        }
                    }, CommonVaraibles.DELAY_IN_MS);

                }
            });

            tableBooking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (EventsController.Instance().eventsList.get(pager.getCurrentItem()).isAvailable()) {
                        Intent tableBookingIntent = new Intent(EventActivity.this, TableBookingActivity.class);
                        tableBookingIntent.putExtra(CommonVaraibles.CONSTANT_PARAM_INTENT_CURRENT_BOOKING_EVENT, pager.getCurrentItem());
                        startActivity(tableBookingIntent);
                        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    } else {
                    }
                }
            });

            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.v("State", "onPageScrolled");
                }

                @Override
                public void onPageSelected(int position) {
                    Log.v("State", "onPageSelected");
                    if (EventsController.Instance().eventsList.get(position).isAvailable()) {
                        tableBooking.setImageResource(R.drawable.icon_table_booking_on);
                    } else {
                        tableBooking.setImageResource(R.drawable.icon_table_booking_off);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    Log.v("State", "onPageScrollStateChanged");
                }
            });

            pager.setCurrentItem(EventsController.Instance().eventsList.size() - 1);
        }else{
            pager.setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.bottomLy)).setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.no_image_layout)).setVisibility(View.VISIBLE);
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");
        ((TextView)findViewById(R.id.title)).setTypeface(face);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void shareItem() {
        Picasso.with(getApplicationContext()).load(EventsController.Instance().eventsList.get(pager.getCurrentItem()).getBanner()).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("*/*");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, EventsController.Instance().eventsList.get(pager.getCurrentItem()).getName() + " " + EventsController.Instance().eventsList.get(pager.getCurrentItem()).formatedDateTime() + " \n" + "www.gothaclubdubai.com");
//                shareIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
//                startActivity(shareIntent);



//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("image/*");
//                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
//                startActivity(Intent.createChooser(i, "Share Image"));

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                Intent chooserIntent = Intent.createChooser(shareIntent, "Choose");
                String fb_URL = EventsController.Instance().eventsList.get(pager.getCurrentItem()).getFb_event_url();
                String textToShare = CommonVaraibles.CONSTANT_STR_EVENT_SHARING_TEXT + EventsController.Instance().eventsList.get(pager.getCurrentItem()).getName() + CommonVaraibles.CONSTANT_STR_EVENT_SHARING_TEXT_ON + EventsController.Instance().eventsList.get(pager.getCurrentItem()).formatedDateTime() + CommonVaraibles.CONSTANT_STR_EVENT_SHARING_TEXT_END  + "\n" + "www.gothaclubdubai.com";
                if(fb_URL != null || !fb_URL.equals("") || !fb_URL.equals("null") || !fb_URL.equalsIgnoreCase("undefined")  ){
                    textToShare = CommonVaraibles.CONSTANT_STR_EVENT_SHARING_TEXT + EventsController.Instance().eventsList.get(pager.getCurrentItem()).getName() + CommonVaraibles.CONSTANT_STR_EVENT_SHARING_TEXT_ON + EventsController.Instance().eventsList.get(pager.getCurrentItem()).formatedDateTime() + CommonVaraibles.CONSTANT_STR_EVENT_SHARING_TEXT_END  + "\n" + fb_URL;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                    Bundle facebookBundle = new Bundle();
                    facebookBundle.putString(Intent.EXTRA_TEXT, textToShare);
                    Bundle replacement = new Bundle();
                    replacement.putBundle("com.facebook.katana", facebookBundle);
                    chooserIntent.putExtra(Intent.EXTRA_REPLACEMENT_EXTRAS, replacement);
                } else {
                    shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                }
                chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(chooserIntent);

            }
            @Override public void onBitmapFailed(Drawable errorDrawable) { }
            @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }
}
