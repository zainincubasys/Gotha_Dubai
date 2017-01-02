package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Controllers.NotificationController;
import Models.NotificationModel;
import Models.VideosModel;
import gothadubai.incubasys.com.gothadubai.R;
import gothadubai.incubasys.com.gothadubai.VideoPlayerActivity;


public class NotificationAdapter  extends BaseAdapter {
    List<NotificationModel> notifiList;
    Context mContext;

    public NotificationAdapter(Context pContext){
        mContext = pContext;
        notifiList = NotificationController.Instance().notifList;
    }

    @Override
    public int getCount() {
        return notifiList.size();
    }

    @Override
    public Object getItem(int position) {
        return notifiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notification_item_layout, parent, false);

        }else{

        }
        ((TextView)convertView.findViewById(R.id.time_txt)).setText(notifiList.get(position).getDateCreated());
        ((TextView)convertView.findViewById(R.id.main_txt)).setText(notifiList.get(position).getMsg());
        Typeface face = Typeface.createFromAsset((mContext).getAssets(), "gothic-regular.ttf");
        ((TextView)convertView.findViewById(R.id.time_txt)).setTypeface(face);
        ((TextView)convertView.findViewById(R.id.main_txt)).setTypeface(face);
        ((LinearLayout)convertView.findViewById(R.id.parentPanel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}

