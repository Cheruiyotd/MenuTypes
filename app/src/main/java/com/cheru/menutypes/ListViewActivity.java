package com.cheru.menutypes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends AppCompatActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    ArrayList<String> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mainListView = findViewById(R.id.mainListView);

        String[] planets = new  String[]{"Mercury", "Venus", "Earth", "Mars",
        "Jupyter", "Saturn", "Uranus", "Neptune"};
        planetList = new ArrayList<String>();
        planetList.addAll(Arrays.asList(planets));

        listAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, planetList);

        mainListView.setAdapter(listAdapter);

        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        mainListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.listDelete:
                        deleteSelectedItems();
                        actionMode.finish();
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });
    }

    private void deleteSelectedItems() {
        SparseBooleanArray checkedItemPositions = mainListView.getCheckedItemPositions();
        int itemCount = mainListView.getCount();

        for (int i = itemCount - 1; i >= 0; i--){
            if (checkedItemPositions.get(i)){
                listAdapter.remove(planetList.get(i));
            }
            checkedItemPositions.clear();
            listAdapter.notifyDataSetChanged();
        }
    }
}
