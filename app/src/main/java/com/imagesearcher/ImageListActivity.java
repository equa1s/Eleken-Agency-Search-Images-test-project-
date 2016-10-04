package com.imagesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.imagesearcher.controllers.GoogleSearchController;
import com.imagesearcher.controllers.callbacks.GoogleSearchCallback;
import com.imagesearcher.database.pojo.Items;
import com.imagesearcher.database.pojo.Pagemap;
import com.imagesearcher.database.pojo.SearchItem;
import com.imagesearcher.database.pojo.Thumbnail;
import com.imagesearcher.ui.ImageGridAdapter;
import com.imagesearcher.util.Constants;
import com.imagesearcher.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author equa1s.
 */
public class ImageListActivity extends BaseActionBarActivity implements GoogleSearchCallback {

    private static final String TAG = ImageListActivity.class.getSimpleName();

    @BindView(R.id.recycler_view) public RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar) public ProgressBar mProgressBar;

    private int rowsCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_list_activity);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {

            String searchParam = intent.getStringExtra(MainActivity.SEARCH_QUERY);
            rowsCount = intent.getIntExtra(MainActivity.ROWS_COUNT, 2);

            initializeRecyclerView();

            Log.d(TAG, searchParam + ", " + rowsCount);

            if (NetworkUtil.isNetworkConnected(this)) {
                sendRequest(searchParam);
            } else {
                Toast.makeText(this, R.string.exception_network_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean enableBackButton() {
        return true;
    }

    private void sendRequest(String searchParam) {
        GoogleSearchController mGoogleSearchController = new GoogleSearchController(this);

        Map<String, String> options = new HashMap<>();
        options.put("q", searchParam);
        options.put("key", Constants.GOOGLE_API_KEY);
        options.put("cx", Constants.ENGINE_ID);

        mGoogleSearchController.getGoogleSearchResults(options);
    }

    private void initializeRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, rowsCount, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:onBackPressed();
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onSuccess(Items items) {
        Log.wtf(TAG, "GET: " + items.toString());
        List<String> handled = handleResponse(items);
        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(this, handled);
        mRecyclerView.setAdapter(imageGridAdapter);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinish() {
        Log.wtf(TAG, "Finished. ");
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.wtf(TAG, throwable.getMessage());
    }

    // shitty enough, but still work
    private List<String> handleResponse(Items items) {

        List<String> result = new ArrayList<>();
        List<SearchItem> list = items.getItems();

        for (SearchItem searchItem : list) {

            if (searchItem != null) {

                Pagemap pagemap = searchItem.getPagemap();

                if (pagemap != null) {

                    List<Thumbnail> thumbnails = pagemap.getThumbnails();

                    if (thumbnails != null && !thumbnails.isEmpty()) {

                        for (Thumbnail thumbnail : thumbnails) {

                            String src = thumbnail.getSrc();
                            if (src != null) {

                                result.add(src);

                            }
                        }
                    }
                }

            }
        }
        return result;
    }

}
