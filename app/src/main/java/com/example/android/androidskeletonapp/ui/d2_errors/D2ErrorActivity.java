package com.example.android.androidskeletonapp.ui.d2_errors;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.android.androidskeletonapp.R;
import com.example.android.androidskeletonapp.data.Sdk;
import com.example.android.androidskeletonapp.ui.base.ListActivity;

import org.hisp.dhis.android.core.maintenance.D2Error;

public class D2ErrorActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp(R.layout.activity_d2_errors, R.id.d2ErrorsToolbar, R.id.d2ErrorsRecyclerView);
        observeError();
    }

    private void observeError(){
        D2ErrorAdapter adapter = new D2ErrorAdapter();
        recyclerView.setAdapter(adapter);


        LiveData<PagedList<D2Error>> errorPagedListLiveData = Sdk.d2().maintenanceModule().d2Errors.getPaged(10);

        errorPagedListLiveData.observe(this,errorPagedList->{
            adapter.submitList(errorPagedList);
            findViewById(R.id.d2ErrorsNotificator).setVisibility(
                    errorPagedList.isEmpty() ? View.VISIBLE : View.GONE
            );
        });
    }
}
