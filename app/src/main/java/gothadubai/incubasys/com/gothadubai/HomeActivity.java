package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;

import CommonUtils.CommonVaraibles;
import Controllers.EventsController;
import Controllers.VideosController;


public class HomeActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static boolean IS_HOME_OPENED = false;
    private VideoView videoView;
    public float firstItemWidthDate;
    public float paddingDate;
    public float itemWidthDate;
    public int allPixelsDate;
    public int finalWidthDate;
    private DateAdapter dateAdapter;
    private RelativeLayout mainLayout;
    private ArrayList<LabelerDate> labelerDates = new ArrayList<>();
    private RecyclerView recyclerViewDate;
    private ImageView cameraIcon, aboutUs, logo;
    Animation zoomIn, zoomOut, pushDownIn, pushUpIn, zoomInIcon, zoomOutIcon;
    View _view;
    private FrameLayout frameLayout;
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        cameraIcon = (ImageView)findViewById(R.id.camera_icon);
        videoView = (VideoView)findViewById(R.id.bg_video);
        aboutUs = (ImageView)findViewById(R.id.about_us);
        logo = (ImageView)findViewById(R.id.logo_main);
        mainLayout = (RelativeLayout)findViewById(R.id.main_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");

        String path = "android.resource://" + getPackageName() + "/" + R.raw.main;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();

            }
        });
        getRecyclerviewDate();
        createAnimation();


        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!v.isClickable()) {
                    return;
                }
                Intent intent = new Intent(HomeActivity.this, CameraActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                v.setClickable(false);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setClickable(true);
                    }
                }, CommonVaraibles.DELAY_IN_MS);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentUrl(Uri.parse("http://gothaclubdubai.com"))
//                        .setImageUrl(Uri.parse(EventsController.Instance().eventsList.get(0).getBanner()))
//                        .setContentTitle("Gotha")
//                        .build();
//                ShareDialog shareDialog = new ShareDialog(HomeActivity.this);
//                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                zoomIn.setDuration(150);
                logo.startAnimation(zoomIn);



            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomIn.setDuration(150);
                logo.startAnimation(zoomIn);
            }
        });

        frameLayout.setVisibility(View.INVISIBLE);


    }



    public void getRecyclerviewDate() {
        recyclerViewDate = (RecyclerView) findViewById(R.id.rv_tasks_date);
        if (recyclerViewDate != null) {
            recyclerViewDate.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDateValue();
                }
            }, 300);
            recyclerViewDate.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerViewDate.smoothScrollToPosition(4);
                    setDateValue();
                    pushUpIn.setDuration(300);
                    if(!IS_HOME_OPENED) {
                        frameLayout.startAnimation(pushUpIn);
                        frameLayout.setVisibility(View.VISIBLE);
                    }
                }
            }, 500);
        }
        ViewTreeObserver vtoDate = recyclerViewDate.getViewTreeObserver();
        vtoDate.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {


            @Override
            public boolean onPreDraw() {
                recyclerViewDate.getViewTreeObserver().removeOnPreDrawListener(this);
                finalWidthDate = recyclerViewDate.getMeasuredWidth();
                itemWidthDate = getResources().getDimension(R.dimen.item_dob_width);
                paddingDate = (int)((finalWidthDate - itemWidthDate) / 2);
                firstItemWidthDate = paddingDate;
                allPixelsDate = 0;

                final LinearLayoutManager dateLayoutManager = new LinearLayoutManager(getApplicationContext());
                dateLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewDate.setLayoutManager(dateLayoutManager);
                recyclerViewDate.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
                recyclerViewDate.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        synchronized (this) {
//                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                                calculatePositionAndScrollDate(recyclerView);
//                            }
                        }

                    }



                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        allPixelsDate += dx;
                        synchronized (this) {
//                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                calculatePositionAndScrollDate(recyclerView);
//                            }
                        }
                    }
                });

                if (labelerDates == null) {
                    labelerDates = new ArrayList<>();
                }
                genLabelerDate();
                dateAdapter = new DateAdapter(labelerDates, (int) firstItemWidthDate);
                recyclerViewDate.setAdapter(dateAdapter);
                dateAdapter.setSelecteditem(dateAdapter.getItemCount() - 1);
                return true;
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(IS_HOME_OPENED){
            zoomOut.setDuration(0);
            logo.startAnimation(zoomOut);
        }else{

            logo.setAnimation(pushDownIn);
        }
    }

    private void genLabelerDate() {
        labelerDates.add(new LabelerDate("ABC", DateAdapter.VIEW_TYPE_PADDING, 0));


        labelerDates.add(new LabelerDate("VIDEOS", DateAdapter.VIEW_TYPE_ITEM, R.drawable.cat_videos));
        labelerDates.add(new LabelerDate("PHOTOS", DateAdapter.VIEW_TYPE_ITEM, R.drawable.cat_photos));
        labelerDates.add(new LabelerDate("EVENTS", DateAdapter.VIEW_TYPE_ITEM, R.drawable.cat_soirees));
//        labelerDates.add(new LabelerDate("DINNER", DateAdapter.VIEW_TYPE_ITEM, R.drawable.cat_resto));
        labelerDates.add(new LabelerDate("PARTNERS", DateAdapter.VIEW_TYPE_ITEM, R.drawable.cat_partners));
        labelerDates.add(new LabelerDate("NOTIFICATIONS", DateAdapter.VIEW_TYPE_ITEM, R.drawable.cat_notif));

        labelerDates.add(new LabelerDate("XYZ", DateAdapter.VIEW_TYPE_PADDING, 0));

    }


    private void calculatePositionAndScrollDate(RecyclerView recyclerView) {
        int expectedPositionDate = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate);

        if (expectedPositionDate == -1) {
            expectedPositionDate = 0;
        } else if (expectedPositionDate >= recyclerView.getAdapter().getItemCount() - 2) {
            expectedPositionDate--;
        }
        scrollListToPositionDate(recyclerView, expectedPositionDate);

    }


    private void scrollListToPositionDate(RecyclerView recyclerView, int expectedPositionDate) {
        float targetScrollPosDate = expectedPositionDate * itemWidthDate + firstItemWidthDate - paddingDate;
        float missingPxDate = targetScrollPosDate - allPixelsDate;
        if (missingPxDate != 0) {
//            recyclerView.smoothScrollBy((int) missingPxDate, 0);
        }
        setDateValue();
    }


    private void setDateValue() {
        int expectedPositionDateColor = Math.round((allPixelsDate + paddingDate - firstItemWidthDate) / itemWidthDate);
        int setColorDate = expectedPositionDateColor + 1;
        dateAdapter.setSelecteditem(setColorDate);
    }


    public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
        private ArrayList<LabelerDate> dateDataList;


        private static final int VIEW_TYPE_PADDING = 1;
        private static final int VIEW_TYPE_ITEM = 2;
        private int paddingWidthDate = 0;

        private int selectedItem = -1;

        public DateAdapter(ArrayList<LabelerDate> dateData, int paddingWidthDate) {
            this.dateDataList = dateData;
            this.paddingWidthDate = paddingWidthDate;

        }

        @Override
        public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,
                        parent, false);
                return new DateViewHolder(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,
                        parent, false);

                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                layoutParams.width = paddingWidthDate;

                view.setLayoutParams(layoutParams);
                return new DateViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(DateViewHolder holder, final int position) {
            LabelerDate labelerDate = dateDataList.get(position);
            if (getItemViewType(position) == VIEW_TYPE_ITEM) {
                holder.tvDate.setText(labelerDate.getNumber());
                holder.icon.setImageResource(labelerDate.imgID);
                holder.iconLg.setImageResource(labelerDate.imgID);
                holder.tvDate.setVisibility(View.VISIBLE);

                Log.d(TAG, "default " + position + ", selected " + selectedItem);
                if (position == selectedItem) {
                    Log.d(TAG, "center" + position);
                    holder.tvDate.setTextColor(Color.WHITE);
                    holder.tvDate.setVisibility(View.VISIBLE);
                    holder.iconLg.setVisibility(View.VISIBLE);
                    holder.icon.setVisibility(View.GONE);
//                    holder.tvDate.setBackgroundColor(Color.WHITE);

                } else {
                    holder.tvDate.setVisibility(View.INVISIBLE);
                    holder.iconLg.setVisibility(View.GONE);
                    holder.icon.setVisibility(View.VISIBLE);
                }

                holder.icon.setTag(position);
                holder.iconLg.setTag(position);
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(v);
//                        recyclerViewDate.smoothSc=
                    }
                });

                holder.iconLg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openActivity(v);
                    }
                });

            } else {
                holder.tvDate.setVisibility(View.INVISIBLE);
                holder.icon.setVisibility(View.GONE);
                holder.iconLg.setVisibility(View.INVISIBLE);

            }

        }

        public void setSelecteditem(int selecteditem) {
            this.selectedItem = selecteditem;
//            notifyItemChanged(selecteditem);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return dateDataList.size();
        }

        @Override
        public int getItemViewType(int position) {
            LabelerDate labelerDate = dateDataList.get(position);
            if (labelerDate.getType() == VIEW_TYPE_PADDING) {
                return VIEW_TYPE_PADDING;
            } else {
                return VIEW_TYPE_ITEM;
            }

        }


        public class DateViewHolder extends RecyclerView.ViewHolder {
            public TextView tvDate;
            public ImageView icon;
            public ImageView iconLg;

            public DateViewHolder(View itemView) {
                super(itemView);
                tvDate = (TextView) itemView.findViewById(R.id.txt_date);
                tvDate.setTypeface(face);
                icon = (ImageView)itemView.findViewById(R.id.icon_img);
                iconLg = (ImageView)itemView.findViewById(R.id.icon_large_img);
            }
        }
    }

    private class LabelerDate {
        private int type;
        private String number;
        public int imgID;

        public LabelerDate(String pName, int pType, int pID){
            number = pName;
            type = pType;
            imgID = pID;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    private void openActivity(View v){
        if(Integer.parseInt(v.getTag().toString()) == 5) {
            Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
        else if(Integer.parseInt(v.getTag().toString()) == 1) {
            Intent intent = new Intent(HomeActivity.this, VideosListviewActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
        else if(Integer.parseInt(v.getTag().toString()) == 2) {
            Intent intent = new Intent(HomeActivity.this, PhotoGalleryActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
        else if(Integer.parseInt(v.getTag().toString()) == 3) {
            Intent intent = new Intent(HomeActivity.this, EventActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
        else if(Integer.parseInt(v.getTag().toString()) == 4) {
            Intent intent = new Intent(HomeActivity.this, PartnerActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
    }

    private void createAnimation(){


        pushUpIn = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.push_up_in);
        pushUpIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                frameLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        pushDownIn = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.push_down_in);
        pushDownIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        zoomOut = AnimationUtils.loadAnimation(HomeActivity.this,
                R.anim.zoom_out);
        zoomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomIn = AnimationUtils.loadAnimation(HomeActivity.this,
                R.anim.zoom_in);
        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent (HomeActivity.this, AboutUsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        moveTaskToBack(true);
        super.onBackPressed();


    }
}
