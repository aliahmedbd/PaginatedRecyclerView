package com.aliahmed.me.paginatedrecyclerview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class MainActivity extends AppCompatActivity implements SimpleDataListAdapter.OnReloadClickListener, SimpleDataListAdapter.OnItemClickListener {
    public static final int TOTAL_PAGE_NUMBER = 200;
    public int totalItemCount = 200;
    public boolean isLastPage = false;
    List<SimpleDataModel> simpleDataModels;
    RecyclerView mRecyclerView;
    public SimpleDataModel simpleDataModel;
    public SimpleDataListAdapter simpleDataListAdapter;
    public LinearLayoutManager layoutManager;
    public int pageNumber = 1;

    private boolean isLoading = false;
    private RecyclerView.OnScrollListener recyclerViwListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalSize = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalSize) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        loadMoreItems();
                    }
                }, 1000);

            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializations
        mRecyclerView = (RecyclerView) findViewById(R.id.rvSimpeList);
        simpleDataModel = new SimpleDataModel();
        simpleDataModels = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        simpleDataModel.setName("Ali Ahmed");
        simpleDataModel.setMobileNumber("aliahmedaiub@gmail.com");
        simpleDataModel.setDept("Software Engineer");

        simpleDataListAdapter = new SimpleDataListAdapter();
        setDummyData();
        simpleDataListAdapter.addFooter();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(simpleDataListAdapter);
        mRecyclerView.addOnScrollListener(recyclerViwListener);

        //invoke click listeners
        simpleDataListAdapter.setOnItemClickListener(this);
        simpleDataListAdapter.setOnReloadClickListener(this);
    }

    private void setDummyData() {
        isLoading = false;
        for (int i = 0; i < 2; i++) {
            simpleDataListAdapter.addItem(simpleDataModel);
        }
    }


    @Override
    public void onItemClick(int position, View view) {
        Toast.makeText(MainActivity.this, "Position: " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReloadClick() {
        simpleDataListAdapter.updateFooter(SimpleDataListAdapter.FooterType.LOAD_MORE);
        setDummyData();
        simpleDataListAdapter.addAllItem(simpleDataModels);
    }

    /**
     * Load more for pagination.
     */
    public void loadMoreItems() {
        isLoading = true;
        simpleDataListAdapter.updateFooter(SimpleDataListAdapter.FooterType.LOAD_MORE);
        pageNumber += 1;
        setDummyData();
        simpleDataListAdapter.addAllItem(simpleDataModels);
    }
}
