package com.simcoder.tuktukgo.Driver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simcoder.tuktukgo.Adapters.TypeAdapter;
import com.simcoder.tuktukgo.Objects.TypeObject;
import com.simcoder.tuktukgo.R;
import com.simcoder.tuktukgo.Utils.Utils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Activity responsible for letting the driver chose the service type
 * they offer.
 * <p>
 * It displays a recyclerView with the possible service type options
 */
public class DriverChooseTypeActivity extends AppCompatActivity {

    private TypeAdapter mAdapter;

    ArrayList<TypeObject> typeArrayList = new ArrayList<>();

    ImageView mConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_choose_type);

        setupToolbar();

        mConfirm = findViewById(R.id.confirm);
        mConfirm.setOnClickListener(view -> confirmEntry());

        initRecyclerView();


        String service = getIntent().getStringExtra("service");
        for (TypeObject mType : typeArrayList) {
            if (mType.getId().equals(service)) {
                mAdapter.setSelectedItem(mType);
            }
        }

        mAdapter.notifyItemChanged(
                mAdapter.getItemCount()
        );
    }

    /**
     * Sets up toolbar with custom text and a listener
     * to go back to the previous activity
     */
    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.type));
        myToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setNavigationOnClickListener(v -> finish());
    }

    private void confirmEntry() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", mAdapter.getSelectedItem().getId());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    /**
     * Initializes the recyclerview that shows the costumer the
     * available car types
     */
    private void initRecyclerView() {
        typeArrayList = Utils.getTypeList(DriverChooseTypeActivity.this);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(DriverChooseTypeActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TypeAdapter(typeArrayList, DriverChooseTypeActivity.this, null);
        mRecyclerView.setAdapter(mAdapter);
    }


}
