package com.imagesearcher.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.imagesearcher.ImageListActivity;
import com.imagesearcher.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author equa1s.
 */

public class ImageGridAdapter extends RecyclerView.Adapter <ImageGridAdapter.ViewHolder>{

    private static final String TAG = ImageListActivity.class.getSimpleName();
    private List<String> data = null;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view) ImageView imageView;
        @BindView(R.id.progress_bar) ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ImageGridAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String src = data.get(position);
        Log.d(TAG, "Binding item: " + src);
        if (src != null && !src.isEmpty()) {
            holder.progressBar.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(src)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
