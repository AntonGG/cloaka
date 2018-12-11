package app.sensey.privaton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GirlActivity> items;
    private static ClickListener clickListener;

    public GirlAdapter(ArrayList<GirlActivity> items) {
        this.items = items;

    }

    public GirlActivity getItem(int position) {
        return items.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, parent, false);
        return new GirlHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        GirlActivity girlActivity = items.get(position);
        GirlHolder holder = (GirlHolder) viewHolder;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(pxFromDp(holder.img.getContext(), 250),
                LinearLayout.LayoutParams.MATCH_PARENT);


        if (position % 2 != 0) {
            layoutParams.setMargins(pxFromDp(holder.img.getContext(), 1), 0, 0, pxFromDp(holder.img.getContext(), 2));
        } else {
            layoutParams.setMargins(0, 0, pxFromDp(holder.img.getContext(), 1), pxFromDp(holder.img.getContext(), 2));
        }
        holder.layout.setLayoutParams(layoutParams);
        Glide.with(holder.img.getContext())
                .load(girlActivity.getImage())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.img);

    }

    public static int pxFromDp(final Context context, int dp) {
        return (int) Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        GirlAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(GirlActivity girlActivity);
    }

    public class GirlHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_image)
        ImageView img;
        @BindView(R.id.layout)
        LinearLayout layout;

        public GirlHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getItem(getAdapterPosition()));
        }
    }
}
