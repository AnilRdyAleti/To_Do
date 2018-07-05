package com.example.anilreddy.to_do_project.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anilreddy.to_do_project.R;
import com.example.anilreddy.to_do_project.addToDo.AddToDoActivity;
import com.example.anilreddy.to_do_project.appDefault.BaseFragment;
import com.example.anilreddy.to_do_project.utility.ItemTouchHelperClass;
import com.example.anilreddy.to_do_project.utility.RecyclerViewEmptySupport;
import com.example.anilreddy.to_do_project.utility.ToDoItem;

import java.util.ArrayList;

public class MainFragment extends BaseFragment {

    private RecyclerViewEmptySupport mRecyclerView;
    private FloatingActionButton mAddToDoItemFAB;
    private ArrayList<ToDoItem> mToDoItemsArrayList;
    private CoordinatorLayout mCoordLayout;
    public static final String TODOITEM = "com.avjindersinghsekhon.com.avjindersinghsekhon.minimaltodo.MainActivity";
    private MainFragment.BasicListAdapter adapter;
    private static final int REQUEST_ID_TODO_ITEM = 100;
    private ToDoItem mJustDeletedToDoItem;
    private int mIndexOfDeletedToDoItem;
    public static final String DATE_TIME_FORMAT_12_HOUR = "MMM d, yyyy  h:mm a";
    public static final String DATE_TIME_FORMAT_24_HOUR = "MMM d, yyyy  k:mm";
    public static final String FILENAME = "todoitems.json";
    private StoreRetrieveData storeRetrieveData;
    public ItemTouchHelper itemTouchHelper;
    private CustomRecyclerScrollViewListener customRecyclerScrollViewListener;
    public static final String SHARED_PREF_DATA_SET_CHANGED = "com.avjindersekhon.datasetchanged";
    public static final String CHANGE_OCCURED = "com.avjinder.changeoccured";
    private int mTheme = -1;
    private String theme = "name_of_the_theme";
    public static final String THEME_PREFERENCES = "com.avjindersekhon.themepref";
    public static final String RECREATE_ACTIVITY = "com.avjindersekhon.recreateactivity";
    public static final String THEME_SAVED = "com.avjindersekhon.savedtheme";
    public static final String DARKTHEME = "com.avjindersekon.darktheme";
    public static final String LIGHTTHEME = "com.avjindersekon.lighttheme";
    private AnalyticsApplication app;
    private String[] testStrings = {"Clean my room",
            "Water the plants",
            "Get car washed",
            "Get my dry cleaning"
    };


    @Override
    protected int layoutRes() {
        return R.layout.fragment_main;
    }

    public static Fragment newInstance() {
        return new MainFragment();
    }


    private class BasicListAdapter extends RecyclerView.Adapter<BasicListAdapter.ViewHolder> implements ItemTouchHelperClass.ItemTouchHelperAdapter {
        private ArrayList<ToDoItem> items;

        BasicListAdapter(ArrayList<ToDoItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public BasicListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        }

        @Override
        public void onBindViewHolder(@NonNull BasicListAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public void onItemMoved(int fromPosition, int toPosition) {

        }

        @Override
        public void onItemRemoved(int position) {

        }

        @SuppressWarnings("deprecation")
        public class ViewHolder extends RecyclerView.ViewHolder {
            View mView;
            LinearLayout linearLayout;
            TextView mToDoTextview;
            ImageView mColorImageView;
            TextView mTimeTextView;

            public ViewHolder(View v) {
                super(v);
                mView = v;
                v.setOnClickListener(view -> {
                    ToDoItem item = items.get(ViewHolder.this.getAdapterPosition());
                    Intent i = new Intent(getContext(), AddToDoActivity.class);
                    i.putExtra(TODOITEM, item);
                    startActivityForResult(i, REQUEST_ID_TODO_ITEM);
                });

                mToDoTextview = v.findViewById(R.id.toDoListItemTextview);
                mTimeTextView = v.findViewById(R.id.todoListItemTimeTextView);
                mColorImageView = v.findViewById(R.id.toDoListItemColorImageView);
                linearLayout = v.findViewById(R.id.listItemLinearLayout);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecyclerView.removeOnScrollListener(customRecyclerScrollViewListener);
    }
}
