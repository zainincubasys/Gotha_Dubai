package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by Xain on 27/09/2016.
 */
public class AboutUsActivity extends Activity implements View.OnClickListener
{

    private VideoView videoView;
    private ImageView iconFb, iconCall, iconMail, infoIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_layout);
        videoView = (VideoView)findViewById(R.id.bg_video);
        iconFb = (ImageView)findViewById(R.id.share_fb);
        iconCall = (ImageView)findViewById(R.id.share_call);
        iconMail = (ImageView)findViewById(R.id.share_mail);
        Typeface face = Typeface.createFromAsset(getAssets(), "gothic-regular.ttf");

        ((TextView)findViewById(R.id.title)).setTypeface(face);
        ((ImageView)findViewById(R.id.share_fb)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.share_call)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.share_mail)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.info_icon)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.down_icon)).setOnClickListener(this);
        String str = "Gotha Club has redefined nightlife in Cannes, French Riviera for the past 6 years, and now decided to settles in Dubai." + "\n" +
                "The Craziest, most select and glamorous nightclub, fashioned by APJ Management, features exclusive produced shows with a wide selection of Artists, hot on-trend clubbers, celebrities and VIP’s.\n" +
                "The unique designed, Lighting and Audio System made Gotha sets the stage for a memorable nightlife experience.";
                //"Launched in July 2011 on the French Rivieria, GOTHA CLUB is an unique and exclusice vvenue hosting sophisticated events and a completely original concept in entertainment. Gotha combines a classic night club with a live-entertainment venue. Its exclusively produced shows feature a wide selection of artists highlighting the best of contemporary music : from Dr Dre to Snoop from P.Diddy to David Guetta, to name only a few...\n" +
//                "\n" +
//                "For the third time in 2014, during the Dubai Film Festival, GOTHA CLUB will take its place in the heart of sumptuous and mythical Palm Beach, overlooking the bay of Dubai. GOTHA CLUB will welcome more than 3000 guests, specially selected to see the best international DJs and artists perform.\n" +
//                "\n" +
//                "More than a place, GOTHA CLUB is a spirit - the world's craziest, nost exclusive, most glamourous discotheque. Ephemeral and flexible, it will surprise you with its fabulous scenography and its outstanding programme !\n" +
//                "\n" +
//                "GOTHA CLUB 2014 will give you a spectalce to remember ! \n";
        //((TextView)findViewById(R.id.textview)).setText("Launched in July 2011 on the French Rivieria, GOTHA CLUB is an unique and exclusice vvenue hosting sophisticated events and a completely original concept in entertainment. Gotha combines a classic night club with a live-entertainment venue. Its exclusively produced shows feature a wide selection of artists highlighting the best of contemporary music : from Dr Dre to Snoop from P.Diddy to David Guetta, to name only a few...\n\nFor the third time in 2014, during the Cannes Film Festival, GOTHA CLUB will take its place in the heart of sumptuous and mythical Palm Beach, overlooking the bay of Cannes. GOTHA CLUB will welcome more than 3000 guests, specially selected to see the best international DJs and artists perform.\n\n\More than a place, GOTHA CLUB is a spirit - the world\'s craziest, nost exclusive, most glamourous discotheque. Ephemeral and flexible, it will surprise you with its fabulous scenography and its outstanding programme !\n\nGOTHA CLUB 2014 will give you a spectalce to remember !");

        ((TextView)findViewById(R.id.textview)).setText(str);
        ((TextView)findViewById(R.id.textview)).setTypeface(face);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.about;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();

            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.share_fb){
            openFb();

        }
        else if(v.getId() == R.id.share_call){
            sendCall();
        }
        else if(v.getId() == R.id.share_mail){
            sendMail();
        }else if(v.getId() == R.id.info_icon){
            Intent intent = new Intent(AboutUsActivity.this, CreditsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }else if (v.getId() == R.id.down_icon){
            finish();
        }
    }

    private void sendMail(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@gothaclubdubai.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "[Contact Application]");
        email.putExtra(Intent.EXTRA_TEXT, "");
        email.setType("message/rfc822");
        startActivity(email);//, "Choose an Email client :");
    }

    private void sendCall(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutUsActivity.this);
        builder1.setTitle("Call");
        builder1.setMessage("Call the +971 (0)4 51 39 008");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String num = "+97145139008";
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + num));
                        startActivity(intent);
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

    private void openFb(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(AboutUsActivity.this);
        builder1.setTitle("Facebook");
        builder1.setMessage("Leave the application and open Facebook?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getOpenFacebookIntent();
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
    public void getOpenFacebookIntent() {
        String facebookUrl = "https://www.facebook.com/gothaclubdubai/";//"https://www.facebook.com/events/744942972311075/";
        try {
            int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));

            } else {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://events/744942972311075")));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://gothaclubdubai")));

            }
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
        }

    }
}
