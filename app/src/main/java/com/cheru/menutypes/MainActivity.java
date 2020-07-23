package com.cheru.menutypes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textTitle);
        this.registerForContextMenu(tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
