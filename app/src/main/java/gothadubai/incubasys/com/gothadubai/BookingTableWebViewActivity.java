package gothadubai.incubasys.com.gothadubai;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Xain on 10/05/2017.
 */

public class BookingTableWebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_web_view_layout);
        WebView webview = (WebView)findViewById(R.id.web_view);

        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);

        String summary = "<html><body bgcolor='#000'><iframe height=\"515\" src=\"https://www.reserveout.com/en/rowidget?key=KlhztHfERLSXMGAH&vi=140001201&showPromo=true\" frameborder=\"0\" width=\"330\" scrolling=\"no\"></iframe></body></html>";
        webview.loadData(summary, "text/html", null);
    }
}
