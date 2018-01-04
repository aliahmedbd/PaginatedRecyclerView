package com.aliahmed.me.paginatedrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

class MainActivity extends AppCompatActivity {

    List<SimpleDataModel> simpleDataModels;
    RecyclerView mRecyclerView;
    public SimpleDataModel simpleDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvSimpeList);
        simpleDataModel = new SimpleDataModel();
        setDummyData();
    }

    private void setDummyData() {
        simpleDataModel.setName("Ali Ahmed");
        simpleDataModel.setMobileNumber("+8801681849871");
        simpleDataModel.setDept("Software Engineer");
    }


}
