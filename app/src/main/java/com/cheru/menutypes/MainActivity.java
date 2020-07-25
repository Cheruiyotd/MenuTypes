package com.cheru.menutypes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ActionMode actionMode1;

    private ActionMode.Callback actionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actionmode_context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.actionmodemenu1:
                    Toast.makeText(MainActivity.this, "Action mode 1 clicked", Toast.LENGTH_LONG).show();
                    actionMode.finish();
                    return true;
                case R.id.actionmodemenu2:
                    Toast.makeText(MainActivity.this,"Action mode 2 performed", Toast.LENGTH_LONG).show();
                    actionMode.finish();
                    return true;
                    default:
                        return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            actionMode1 = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textTitle);
        this.registerForContextMenu(tv);

        Button btn = findViewById(R.id.btnActionModeMenu);
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (actionMode1 != null){
                    return false;
            }
                actionMode1 = startActionMode(actionModeCallBack);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, "Checkout the menu course app");

        menu.addIntentOptions(
                R.id.intent_group,
                0,
                0,
                this.getComponentName(),
                null,
                i,
                0,
                null
        );

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.isChecked()) item.setChecked(false);
       else item.setChecked(true);
        switch (item.getItemId())
        {
            case R.id.menuItem1:
                Toast.makeText(this, "menu 1 selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuItem2:
                Toast.makeText(this, "menu 2 selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuItem3:Toast.makeText(this, "menu 3 selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextmenu1:
                Toast.makeText(this, "Context item ! selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.contextmenu2:
                Toast.makeText(this, " Context item 2 selected", Toast.LENGTH_LONG).show();
                return true;
                default:
                    return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.popupMenuItem1:
                Toast.makeText(this,"Item 1 selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.popupMenuItem2:
                Toast.makeText(this,"Item 2 selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.popupMenuItem3:
                Toast.makeText(this,"Item 3 selected", Toast.LENGTH_LONG).show();
                return true;
        default:
            return false;}
    }
    public void showListViewActivity(View view){
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }
}
