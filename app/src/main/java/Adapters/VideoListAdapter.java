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
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import CommonUtils.CommonVaraibles;
import Models.VideosModel;
import gothadubai.incubasys.com.gothadubai.PhotoGalleryActivity;
import gothadubai.incubasys.com.gothadubai.R;
import gothadubai.incubasys.com.gothadubai.VideoPlayerActivity;
import gothadubai.incubasys.com.gothadubai.VideosListviewActivity;

/**
 * Created by Xain on 20/10/2016.
 */

public class VideoListAdapter extends BaseAdapter {
    List<VideosModel> videoList;
    Context mContext;

    public VideoListAdapter(Context pContext, List<VideosModel> pList){
        videoList = pList;
        mContext = pContext;

    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoList.get(position);
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
            convertView = inflater.inflate(R.layout.video_item, parent, false);

        }else{
        }
        ImageView share_img = (ImageView)convertView.findViewById(R.id.share_img);
        ((TextView)convertView.findViewById(R.id.video_name)).setText(videoList.get(position).getName());
        Typeface face = Typeface.createFromAsset((mContext).getAssets(), "gothic-regular.ttf");
        ((TextView)convertView.findViewById(R.id.video_name)).setTypeface(face);
        ImageView img = (ImageView)convertView.findViewById(R.id.video_image);
        Picasso.with(mContext)
                .load(videoList.get(position).getVideo().pictures.sizes.get(2).link)
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {
                    }
                });

        img.setTag(position);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                intent.putExtra("position", Integer.parseInt(v.getTag().toString()));
                ((Activity)mContext).startActivity(intent);
            }
        });
        share_img.setTag(position);
        share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!v.isClickable()) {
                    return;
                }
                int pPosition = Integer.parseInt(v.getTag().toString());
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, videoList.get(pPosition).getName());
                share.putExtra(Intent.EXTRA_TEXT, videoList.get(pPosition).getVideo().link);

                ((Activity)mContext).startActivity(Intent.createChooser(share, "Sharew Video"));
                v.setClickable(false);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setClickable(true);
                    }
                }, CommonVaraibles.DELAY_IN_MS);

            }
        });

        return convertView;
    }

}
