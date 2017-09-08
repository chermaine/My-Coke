package com.example.myapptest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.example.myapptest.BuildConfig;


public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        mWebView.setWebChromeClient(new WebChromeClient());
        //Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("https://m.youtube.com") || url.contains("https://www.youtube.com") ||
                        url.contains("https://www.facebook.com") || url.contains("https://en-gb.facebook.com") ||
                        url.contains("https://m.facebook.com") || url.contains("https://www.instagram.com") ||
                        url.contains("https://mobile.twitter.com") || url.contains("https://www.tumblr.com") ||
                        url.contains("www.dailymotion.com") || url.contains("https://www.iflix.com") ||
                        url.contains("https://www.netflix.com") || url.contains("https://vimeo.com") ||
                        url.contains("www.metacafe.com") || url.contains("www.veoh.com") || url.contains("https://myspace.com") ||
                        url.contains("www1.dramanice.to") || url.contains("www1.dramacool.to") ||
                        url.contains("https://www.dramafever.com") || url.contains("kshowonline.com") ||
                        url.contains("kshow123.net") || url.contains("kshowdaily.net")
                        ) {
                    Toast.makeText(getApplicationContext(), "You do not have permission to access this website", Toast.LENGTH_LONG).show();
                    return true;
                }
                if (url.contains("https://play.google.com")) {
                    Toast.makeText(getApplicationContext(), "You do not have permission to download any apps from Google Store", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }

            @Override
            public void onReceivedError (WebView view, int errorCode, String description, String failingUrl) {
                mWebView.loadUrl("file:///android_asset/www/error.html");
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressDialog != null){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onPageStarted (WebView view, String url, Bitmap favicon) {
                if (progressDialog.getProgress() < 100) {
                    progressDialog.show();
                }
            }

        });

        /* Button email_btn = (Button) findViewById(R.id.button2);
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWebView.loadUrl("https://portal.microsoftonline.com/");
            }
        });

        Button google_btn = (Button) findViewById(R.id.button3);
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("https://www.google.com/");
            }
        });

        Button home_btn = (Button) findViewById(R.id.button);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("https://www.coca-cola.com.my");
            }
        });
*/
        mWebView.loadUrl("https://www.coca-cola.com.my");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_email:
                if (progressDialog.getProgress() < 100) {
                    progressDialog.show();
                }
                mWebView.loadUrl("https://outlook.office365.com/");
                return true;
            case R.id.action_google:
                if (progressDialog.getProgress() < 100) {
                    progressDialog.show();
                }
                mWebView.loadUrl("http://www.google.com/");
                return true;
            case R.id.action_home:
                if (progressDialog.getProgress() < 100) {
                    progressDialog.show();
                }
                mWebView.loadUrl("https://www.coca-cola.com.my");
                return true;
            case R.id.action_quit:
                moveTaskToBack(true);
                finish();
                return true;
            case R.id.action_about:
                String versionName = BuildConfig.VERSION_NAME;
                Toast.makeText(getApplicationContext(), "My Coca-Cola version " + versionName, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
