package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Xain on 27/09/2016.
 */
public class CreditsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_activity_layout);

        ((ImageView)findViewById(R.id.down_icon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView)findViewById(R.id.textview)).setText("Photos Credits : \n\nLe Gotha Club \n\nPalm Beach\nPlace Franklin Roosevelt\nPointe Croisette\n06400 Dubai - UAE\n\nPhone : +33(0)4 93 45 11 11\nMail :  info@gothaclubdubai.com\n\nwww.gothaclubdubai.com\n\nVideo Credits : \n\nKonio Productions\n1 Place Masséna\n06000 Nice - FRANCE\nPhone. : +33 (0)4 83 50 16 97\nMail : contact@konioprod.com\n\nwww.konioprod.com\n\nApp Creator : \n\nIncubasys\n4106, Churchill Executive Tower, Business Bay \n06000  DUBAI - UAE\n\nPhone : +971 42 77 62 20\nMail : info@incubasys.com\n\n www.incubasys.com\n\n\n\n\n  ");
        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");
        ((TextView)findViewById(R.id.textview)).setTypeface(face);
        ((TextView)findViewById(R.id.title)).setTypeface(face);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }
}
