package app.sensey.privaton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import app.sensey.privaton.GirlActivity;
import app.sensey.privaton.GirlAdapter;
import app.sensey.privaton.GirlGenerator;
import app.sensey.privaton.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GridActivity extends AppCompatActivity implements GirlAdapter.ClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    GirlAdapter adapter;
    ArrayList<GirlActivity> items = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);

        items = GirlGenerator.getGirls();
        adapter = new GirlAdapter(items);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(GirlActivity girlActivity) {
        Intent intent = new Intent(this, Activity.class);
        intent.putExtra("girlActivity", girlActivity);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            default: return super.onOptionsItemSelected(item);
            case R.id.action_help:
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grid,menu);
        return true;
    }
}
