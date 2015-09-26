package himanshu.creative.com.ola_hack.activity;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import himanshu.creative.com.ola_hack.R;
import himanshu.creative.com.ola_hack.utils.Constants;

public class LoginActivity extends AppCompatActivity {
    Activity activity;

    @Bind(R.id.login_webview)
    WebView loginWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = this;
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        loadWebView(Constants.loginURL);
    }


    private void loadWebView(String url){

        String paymentUrl = url;
        loginWebView.setWebChromeClient(new WebChromeClient());

        loginWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                Log.i("Came Back(Recieved Error) ==>", description + "===" + failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                handler.proceed();
                Toast.makeText(activity, "SslError! " + error, Toast.LENGTH_SHORT).show();
                Log.i(" Came Back(RecievedSSL Error) ==>", error.toString());

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (url.equalsIgnoreCase("https://m.sandbox-p.olacabs.com/"))
                    Log.i(" Came Back(override) ==>", url);


                return super.shouldOverrideUrlLoading(view, url);
            }

        });
        loginWebView.getSettings().setBuiltInZoomControls(true);
        loginWebView.getSettings().setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        loginWebView.getSettings().setCacheMode(2);
        loginWebView.getSettings().setDomStorageEnabled(true);
        loginWebView.clearHistory();
        loginWebView.clearCache(true);
        loginWebView.getSettings().setJavaScriptEnabled(true);
        loginWebView.getSettings().setSupportZoom(true);
        loginWebView.getSettings().setUseWideViewPort(false);
        loginWebView.getSettings().setLoadWithOverviewMode(false);
        webview_ClientPost(loginWebView, paymentUrl);
    }

    public void webview_ClientPost(WebView webView, String url){
        StringBuilder sb = new StringBuilder();

        sb.append("<html><head></head>");
        sb.append("<body onload='form1.submit()'>");
        sb.append(String.format("<form id='form1' action='%s' method='%s'>", url, "post"));

        sb.append("</form></body></html>");
        webView.loadData(sb.toString(), "text/html", "UTF-8");

    }

}
