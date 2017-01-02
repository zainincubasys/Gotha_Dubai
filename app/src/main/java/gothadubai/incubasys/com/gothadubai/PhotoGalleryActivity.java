package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Adapters.CircularListAdapter;
import CommonUtils.CommonVaraibles;
import CommonUtils.SharedFunctions;
import Controllers.AlbumsControllers;
import Models.AlbumModel;
import Models.PictureModel;




public class PhotoGalleryActivity extends Activity {
    RelativeLayout rlayout;
    Animation []slide_in_right;
    int index = 0;
    Animation []push_up_in;
    private ListView []listViews;
    private TextView []textViews;
    private HorizontalScrollView _HSView;
    int scrollPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_gallery_activity_layout);
        _HSView = (HorizontalScrollView)findViewById(R.id.scView);
        LinearLayout llview = (LinearLayout)findViewById(R.id.llview);
        LinearLayout bottomLayout = (LinearLayout)findViewById(R.id.bottomLayout);
        rlayout = (RelativeLayout)findViewById(R.id.rlayout);

        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");
        ((TextView)findViewById(R.id.title)).setTypeface(face);

        if(AlbumsControllers.Instance().getAlbumModelList().size()>0) {
            AlbumsControllers.Instance().getAlbumModelList().size();

            listViews = new ListView[AlbumsControllers.Instance().getAlbumModelList().size()];
            textViews = new TextView[AlbumsControllers.Instance().getAlbumModelList().size()];
            CircularListAdapter[] adapters = new CircularListAdapter[AlbumsControllers.Instance().getAlbumModelList().size()];
            SpectrumAdapter[] spectrumAdapter = new SpectrumAdapter[AlbumsControllers.Instance().getAlbumModelList().size()];

            bottomLayout.setWeightSum(listViews.length);
            push_up_in = new Animation[listViews.length];
            slide_in_right = new Animation[listViews.length];

            for (int i = 0; i < listViews.length; i++) {
                listViews[i] = new ListView(this);
                int width = SharedFunctions.screenWidth(PhotoGalleryActivity.this) / 3;
                width = width / 4 + width;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
                params.setMargins(0, 0, 10, 0);
                listViews[i].setBackgroundColor(Color.DKGRAY);
                listViews[i].setLayoutParams(params);
                spectrumAdapter[i] = new SpectrumAdapter(this, -1, AlbumsControllers.Instance().getAlbumModelList().get(i).getPictureModelList());
                adapters[i] = new CircularListAdapter(spectrumAdapter[i]);
                listViews[i].setDividerHeight(10);
                listViews[i].setAdapter(adapters[i]);
                listViews[i].setFastScrollEnabled(false);
                listViews[i].smoothScrollToPosition(10);
                listViews[i].setSelector(getResources().getDrawable(R.drawable.list_item_selector));
                listViews[i].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int listNumb = Integer.parseInt(parent.getTag().toString());
                        Intent intent = new Intent(PhotoGalleryActivity.this, PhotoPreviewActivity.class);
                        intent.putExtra("listNumb", listNumb);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });
                listViews[i].setTag(i);

                listViews[i].setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        ListView lstView = (ListView)view;
                        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                            synchronized (this) {
                                lstView.smoothScrollToPosition(scrollPosition);
//                                lstView.scrollTo(scrollPosition, 0);
                            }

                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        scrollPosition = firstVisibleItem;
                    }
                });

                llview.addView(listViews[i]);

                textViews[i] = new TextView(this);
                textViews[i].setText(AlbumsControllers.Instance().getAlbumModelList().get(i).getName().toUpperCase() + "\n\n" + AlbumsControllers.Instance().getAlbumModelList().get(i).getDate());
                textViews[i].setTextColor(Color.WHITE);
                textViews[i].setGravity(Gravity.CENTER);
                textViews[i].setTextSize(20);
                textViews[i].setTypeface(face);

                int height = bottomLayout.getLayoutParams().height;
                params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
                if (i != textViews.length - 1) {
                    params.setMargins(0, 0, 10, 0);
                }
                textViews[i].setLayoutParams(params);
                bottomLayout.addView(textViews[i]);

                if (!CommonVaraibles.isPhotoOpened) {
                    textViews[i].setVisibility(View.INVISIBLE);
                    listViews[i].setVisibility(View.INVISIBLE);

                    push_up_in[i] = AnimationUtils.loadAnimation(PhotoGalleryActivity.this,
                            R.anim.push_up_in);
                    push_up_in[i].setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            listViews[index].setVisibility(View.VISIBLE);
                            listViews[index].setBackgroundColor(Color.BLACK);
                            listViews[index].setAnimation(null);
                            textViews[index].startAnimation(slide_in_right[index]);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    push_up_in[i].setDuration(250);

                    slide_in_right[i] = AnimationUtils.loadAnimation(PhotoGalleryActivity.this,
                            R.anim.slide_in_right);
                    slide_in_right[i].setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            textViews[index].setVisibility(View.VISIBLE);
                            textViews[index].setAnimation(null);
                            index++;
                            if (index != listViews.length) {
                                listViews[index].startAnimation(push_up_in[index]);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    slide_in_right[i].setDuration(200);
                }
            }



        }else{
            _HSView.setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.no_image_layout)).setVisibility(View.VISIBLE);
        }

        ((ImageView) findViewById(R.id.down_icon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!CommonVaraibles.isPhotoOpened && AlbumsControllers.Instance().getAlbumModelList().size()>0 ) {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    index = 0;
                    push_up_in[index].setDuration(500);
                    listViews[index].startAnimation(push_up_in[index]);
                    CommonVaraibles.isPhotoOpened = true;
                }
            }, 500);


        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    class SpectrumAdapter extends ArrayAdapter<PictureModel> {
        private List<PictureModel> spectrum;

        public SpectrumAdapter(Context context, int textViewResourceId,
                               List<PictureModel> spectrum) {
            super(context, textViewResourceId, spectrum);
            this.spectrum = spectrum;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null) {
                LayoutInflater inflater = (LayoutInflater) PhotoGalleryActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.image_item, parent, false);
            }else{

            }
            ImageView img = (ImageView)convertView.findViewById(R.id.image_thumb);
            Picasso.with(PhotoGalleryActivity.this)
                    .load(spectrum.get(position).getThumbUrl())
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {

                        }
                        @Override
                        public void onError() {
                        }
                    });
            return convertView;

        }

    }


    private void createAnimation(){

    }


}
