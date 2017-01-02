package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import Adapters.NotificationAdapter;
import Controllers.NotificationController;


public class NotificationActivity extends Activity{

    private ImageView closeBtn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifiaction_layot);
        closeBtn = ((ImageView)findViewById(R.id.down_icon));
        list = (ListView)findViewById(R.id.list);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list.setAdapter(new NotificationAdapter(NotificationActivity.this));

        Typeface face = Typeface.createFromAsset(getAssets(),
                "gothic-regular.ttf");
        ((TextView)findViewById(R.id.title)).setTypeface(face);

        if(NotificationController.Instance().notifList.size()>0){

        }else{
            list.setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.no_image_layout)).setVisibility(View.VISIBLE);
        }


    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }
}
