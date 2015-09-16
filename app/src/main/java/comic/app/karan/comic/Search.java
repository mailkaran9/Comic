package comic.app.karan.comic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Karan on 9/13/2015.
 */
public class Search extends Activity {

    ImageButton b;
    EditText et;
    WebView wv;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        b = (ImageButton) findViewById(R.id.imageButton);
        et = (EditText) findViewById(R.id.editText);

        wv = (WebView) findViewById(R.id.webView);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("http://www.google.com");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = et.getText().toString();
                wv.loadUrl("http://www.google.co.in/search?q="+s);

            }
        });
    }
}