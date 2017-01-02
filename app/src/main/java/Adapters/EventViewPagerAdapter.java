package Adapters;

import android.content.Context;
import android.graphics.Typeface;
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

import Controllers.EventsController;
import Models.EventModel;
import Models.PictureModel;
import gothadubai.incubasys.com.gothadubai.R;


public class EventViewPagerAdapter extends PagerAdapter {

    Context context;
    List<EventModel> eventModelList;


    public EventViewPagerAdapter(Context c){
        context = c;
        eventModelList = EventsController.Instance().eventsList;
    }

    @Override
    public int getCount() {
        return eventModelList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        EventModel pModel = eventModelList.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.event_item_layout, container, false);
        ImageView img = (ImageView)layout.findViewById(R.id.event_image);
        TextView eventName = (TextView)layout.findViewById(R.id.event_name);

        Typeface face = Typeface.createFromAsset((context).getAssets(), "gothic-regular.ttf");
        eventName.setTypeface(face);
        eventName.setText(pModel.getName() + "\n" + pModel.formatedDateTime() );
        Picasso.with(context)
                .load(eventModelList.get(position).getBanner())
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
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
}
