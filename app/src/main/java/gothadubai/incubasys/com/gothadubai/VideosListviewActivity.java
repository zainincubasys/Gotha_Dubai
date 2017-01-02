package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.CardsEffect;
import com.twotoasters.jazzylistview.effects.CurlEffect;
import com.twotoasters.jazzylistview.effects.FadeEffect;
import com.twotoasters.jazzylistview.effects.FanEffect;
import com.twotoasters.jazzylistview.effects.FlipEffect;
import com.twotoasters.jazzylistview.effects.FlyEffect;
import com.twotoasters.jazzylistview.effects.GrowEffect;
import com.twotoasters.jazzylistview.effects.HelixEffect;
import com.twotoasters.jazzylistview.effects.ReverseFlyEffect;
import com.twotoasters.jazzylistview.effects.SlideInEffect;
import com.twotoasters.jazzylistview.effects.StandardEffect;
import com.twotoasters.jazzylistview.effects.TiltEffect;
import com.twotoasters.jazzylistview.effects.TwirlEffect;
import com.twotoasters.jazzylistview.effects.WaveEffect;
import com.twotoasters.jazzylistview.effects.ZipperEffect;
import com.vimeo.networking.model.VideoList;

import java.util.ArrayList;
import java.util.List;

import Adapters.VideoListAdapter;
import CommonUtils.PaperCut;
import Controllers.VideosController;
import Models.VideosModel;

public class VideosListviewActivity extends Activity {

    View topDummyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos_activity_layout);
        JazzyListView list = (JazzyListView) findViewById(R.id.list);
        topDummyView = (View)findViewById(R.id.topView);

        if(VideosController.Instance().videoList.size()>0 ) {

            list.setTransitionEffect(new PaperCut());
            list.setVerticalScrollBarEnabled(false);

            VideoListAdapter adapter = new VideoListAdapter(VideosListviewActivity.this, VideosController.Instance().videoList);
            list.setAdapter(adapter);

            list.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if(firstVisibleItem == 0){
                        topDummyView.setVisibility(View.VISIBLE);
                    }else{
                        topDummyView.setVisibility(View.GONE);
                    }
                }
            });




        }else{
            list.setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.no_image_layout)).setVisibility(View.VISIBLE);
        }
        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");
        ((TextView) findViewById(R.id.title)).setTypeface(face);
        ((ImageView) findViewById(R.id.down_icon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

}
