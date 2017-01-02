package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Controllers.PartnersController;
import Models.PartnerModel;
import gothadubai.incubasys.com.gothadubai.PartnerActivity;
import gothadubai.incubasys.com.gothadubai.R;

/**
 * Created by Xain on 28/10/2016.
 */

public class PartnersDataAdapter extends BaseAdapter {
    private Context context;
    List<PartnerModel> dataList;

    public PartnersDataAdapter(Context pContext){
        dataList = PartnersController.Instance().partnersList;
        context = pContext;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.partner_item, parent, false);
        }else{
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.logo_img);
        Picasso.with(context)
                .load(dataList.get(position).getBanner())
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
