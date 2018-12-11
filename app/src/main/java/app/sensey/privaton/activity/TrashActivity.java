package app.sensey.privaton.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.sensey.privaton.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

public class TrashActivity extends AppCompatActivity implements AdvancedWebView.Listener {

    @BindView(R.id.webview)
    AdvancedWebView webview;
    @BindView(R.id.auth_progress)
    ProgressBar progressBar;

    String trash;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("trash", trash);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);
        ButterKnife.bind(this);

        if (savedInstanceState != null) trash = savedInstanceState.getString("trash");
        else if (getIntent() != null) trash = getIntent().getStringExtra("trash");

        webview.setListener(this, this);
        webview.loadUrl(trash);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
            webview.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        text.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        webview.setVisibility(View.GONE);

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }


    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        webview.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        webview.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        webview.onDestroy();
        // ...
        super.onDestroy();
    }
}
