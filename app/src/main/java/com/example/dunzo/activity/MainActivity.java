package com.example.dunzo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.example.dunzo.R;
import com.example.dunzo.adapter.PhotoGridAdapter;
import com.example.dunzo.common.FlexLayoutScrollListener;
import com.example.dunzo.common.enums.ErrorEnum;
import com.example.dunzo.model.PhotoListModel;
import com.example.dunzo.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOGGER_TAG = MainActivity.class.getSimpleName();
    private MainViewModel mMainViewModel;
    private PhotoGridAdapter mPhotoGridAdapter;
    private int mInitialPhotoRange = 0;
    private boolean mIgnoreFetching = false;
    private ProgressBar mProgressBar = null;
    private EditText mEtSearchString;
    private LinearLayoutManager layoutManager;

    private Runnable mResetFetchingFlag = new Runnable() {
        @Override
        public void run() {
            if (!isFinishing()) {
                mIgnoreFetching = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mProgressBar = findViewById(R.id.progress);
        Button searchButton = findViewById(R.id.btn_search);
        mEtSearchString = findViewById(R.id.et_search);
        final RecyclerView recyclerView = findViewById(R.id.rv_photo_grid);

        mEtSearchString.clearFocus();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFocus();
                mMainViewModel.onSearchTapped(mEtSearchString.getText().toString());
            }
        });

        //Observer to listen to different Errors
        mMainViewModel.getError().observe(this, new Observer<ErrorEnum>() {
            @Override
            public void onChanged(@Nullable ErrorEnum errorEnum) {
                if (errorEnum != null) {
                    switch (errorEnum) {
                        case UNABLE_TO_FETCH_DATA:
                            Toast.makeText(MainActivity.this, errorEnum.getErrorResource(), Toast.LENGTH_SHORT).show();
                            break;
                        case INVALID_DATA:
                            mEtSearchString.setError(getString(errorEnum.getErrorResource()));
                            break;
                    }
                }
            }
        });

        //Flexbox setup to Photos grid
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        recyclerView.setLayoutManager(layoutManager);
        List<PhotoListModel> photos = new ArrayList<>();

        mPhotoGridAdapter = new PhotoGridAdapter(photos);
        recyclerView.setAdapter(mPhotoGridAdapter);

        //Observer for showing/hiding progress bar while fetching data from web
        mMainViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean showLoading) {
                mProgressBar.setVisibility(showLoading == Boolean.TRUE ? View.VISIBLE : View.INVISIBLE);
            }
        });

        //Observer for photos fetched via web or DB
        mMainViewModel.getPhotos().observe(this, new Observer<List<PhotoListModel>>() {
            @Override
            public void onChanged(@Nullable List<PhotoListModel> photoListModels) {
                if (photoListModels != null) {
                    if (photoListModels.isEmpty()) {
                        /*
                            Empty data set indicates clearing of exiting data set of the adapter i.e show a blank list
                            which will happen when a search button is tapped as we have to clear existing data before showing new data
                        */
                        mPhotoGridAdapter.setDataSource(new ArrayList<PhotoListModel>());
                        mPhotoGridAdapter.notifyDataSetChanged();
                        mInitialPhotoRange = 0;
                    } else {

                        List<PhotoListModel> subList = photoListModels.subList(mInitialPhotoRange, mInitialPhotoRange + (photoListModels.size() - mInitialPhotoRange));
                        mPhotoGridAdapter.addPhotos(subList);
                        mPhotoGridAdapter.notifyItemRangeChanged(mInitialPhotoRange, subList.size());
                        Log.d(LOGGER_TAG, "SubList Size:" + subList.size() + " start:" + mInitialPhotoRange + " total:" + mPhotoGridAdapter.getItemCount());
                        mInitialPhotoRange = mInitialPhotoRange + (photoListModels.size() - mInitialPhotoRange);
                    }
                }
            }
        });

        recyclerView.addOnScrollListener(new FlexLayoutScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                if (!mIgnoreFetching) {
                    mIgnoreFetching = true;
                    new Handler(Looper.getMainLooper()).postDelayed(mResetFetchingFlag, 500);
                    Log.d(LOGGER_TAG, "Load more items called");
                    mMainViewModel.fetchPhotos();
                }
            }

            @Override
            public boolean isLastPage() {
                return mMainViewModel.isLastPageFetched();
            }

            @Override
            public boolean isLoading() {
                return mMainViewModel.isLoading();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy != 0) {
                    clearFocus();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void clearFocus() {
        mEtSearchString.clearFocus();
        Window currentWindow = getWindow();
        if (currentWindow != null) {
            //Hide the keyboard on launch or scroll
            View currentFocus = currentWindow.getDecorView();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }
}
