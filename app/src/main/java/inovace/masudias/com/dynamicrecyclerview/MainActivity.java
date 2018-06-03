package inovace.masudias.com.dynamicrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListObject> firstList = new ArrayList<ListObject>();
    private ArrayList<ListObject> secondList = new ArrayList<ListObject>();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private DynamicListAdapter mDynamicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_list);

        firstList = Utilities.populateFirstList();
        secondList = Utilities.populateSecondList();

        // Initialize the list
        mDynamicListAdapter = new DynamicListAdapter();
        mDynamicListAdapter.setFirstList(firstList);
        mDynamicListAdapter.setSecondList(secondList);

        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDynamicListAdapter);
    }
}
