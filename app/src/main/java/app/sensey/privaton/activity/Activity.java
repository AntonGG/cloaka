package app.sensey.privaton.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

import app.sensey.privaton.GirlActivity;
import app.sensey.privaton.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity extends AppCompatActivity {
    @BindView(R.id.girl_image)
    ImageView girlImage;
    @BindView(R.id.girl_url)
    TextView girlUrl;
    @BindView(R.id.girl_count)
    TextView girlCount;

    GirlActivity girlActivity;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("girlActivity", girlActivity);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        ButterKnife.bind(this);

        if (savedInstanceState != null) girlActivity = savedInstanceState.getParcelable("girlActivity");
        else if (getIntent() != null) girlActivity = getIntent().getParcelableExtra("girlActivity");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        girlUrl.setPaintFlags(girlUrl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        if (girlActivity != null) {
            if (girlActivity.getNickname() != null) setTitle(girlActivity.getNickname());

            if (girlActivity.getImage() > 0) {

                Glide.with(this)
                        .load(girlActivity.getImage())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                        .into(girlImage);
            } else girlImage.setVisibility(View.GONE);

            int km = new Random().nextInt(50);
            girlCount.setText("Расстояние: "+ km + " км");

            if (girlActivity.getNickname() != null && girlActivity.getNickname().length() > 0)
                girlUrl.setText("instagram.com/" + girlActivity.getNickname());
            else girlUrl.setVisibility(View.GONE);

        } else {
            onBackPressed();
        }
    }

    @OnClick(R.id.girl_info)
    public void onInfoClick() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(girlActivity.getUrl()));
        startActivity(browserIntent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
