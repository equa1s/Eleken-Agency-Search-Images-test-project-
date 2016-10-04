package com.imagesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author equa1s.
 */
public class MainActivity extends BaseActionBarActivity
        implements View.OnClickListener,
                    SeekBar.OnSeekBarChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String SEARCH_QUERY = "search_query";
    public static final String ROWS_COUNT = "rows_count";

    @BindView(R.id.button_go)      public Button    mGo;
    @BindView(R.id.rows_count)     public SeekBar   mRows;
    @BindView(R.id.search_params)  public EditText  mParam;
    @BindView(R.id.row_count)      public TextView  rows;

    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initializeListeners();
    }

    @Override
    public boolean enableBackButton() {
        return false;
    }

    private void initializeListeners() {
        mRows.setOnSeekBarChangeListener(this);
        mGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_go: handleGoButton();
                break;

            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.d(TAG, "Progress changed: " + progress);
        this.progress = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String rowsCount = String.valueOf(progress);
        rows.setText(rowsCount);
    }

    private void handleGoButton() {

        int rowsCount = progress;
        String searchQuery = mParam.getText().toString();

        if (!searchQuery.isEmpty() && rowsCount != 0) {

            Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
                intent.putExtra(SEARCH_QUERY, searchQuery);
                intent.putExtra(ROWS_COUNT, rowsCount);

            Log.d(TAG, "Rows: " + rowsCount + ", Search query: " + searchQuery);

            startActivity(intent);

        } else {

            if (searchQuery.isEmpty()) {
                Toast.makeText(this, "Please enter search params... ", Toast.LENGTH_SHORT).show();
            }

            if (rowsCount == 0) {
                Toast.makeText(this, "Please enter rows... ", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
