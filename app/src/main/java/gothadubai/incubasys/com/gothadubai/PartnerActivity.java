package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.twotoasters.jazzylistview.JazzyListView;

import java.util.ArrayList;
import java.util.List;

import Adapters.PartnersDataAdapter;
import CommonUtils.PaperCut;
import Controllers.PartnersController;
import Models.PartnerModel;

/**
 * Created by Xain on 22/09/2016.
 */
public class PartnerActivity extends Activity {
    private PartnersDataAdapter adapter;
    View topDummyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_activity_layout);


        JazzyListView list = (JazzyListView)findViewById(R.id.list);
        topDummyView = (View)findViewById(R.id.topView);

        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");
        ((TextView) findViewById(R.id.title)).setTypeface(face);

        if(PartnersController.Instance().partnersList.size()>0) {
            list.setTransitionEffect(new PaperCut());
            list.setVerticalScrollBarEnabled(false);

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


            adapter = new PartnersDataAdapter(PartnerActivity.this);
            list.setAdapter(adapter);




            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final PartnerModel pModel = PartnersController.Instance().partnersList.get(position);
                    if(pModel.getPageUrl() != null && !pModel.getPageUrl().equals("")) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(PartnerActivity.this);
                        builder1.setTitle("Open");
                        builder1.setMessage("Open in browser");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                        i.setData(Uri.parse(pModel.getPageUrl()));
                                        startActivity(i);
                                        dialog.cancel();
                                    }
                                });

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                }
            });
        }else{
            list.setVisibility(View.GONE);
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }


}
