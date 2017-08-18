package com.example.urgentreminders.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.urgentreminders.R;
import com.example.urgentreminders.utilities.Logger;
import com.example.urgentreminders.utilities.Utils;

public class HelpActivity extends Activity {
    private final String TAG = "HelpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Logger.getInstance().logMessage(TAG, "User is in the help activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        if(Utils.returnToHome(this)){
            menu.findItem(R.id.action_login).setVisible(false);
            menu.add(0, 0, 1, getString(R.string.action_home)).setIcon(R.drawable.home).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    onHomeMenuItemClicked();
                    return true;
                }
            }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            menu.findItem(R.id.action_home).setVisible(false);
            menu.add(0, 0, 1, getString(R.string.action_login)).setIcon(R.drawable.login).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    onLoginMenuItemClicked();
                    return true;
                }
            }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            onHomeMenuItemClicked();
            return true;
        }

        if (id == R.id.action_login) {
            onLoginMenuItemClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onHomeMenuItemClicked(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void onLoginMenuItemClicked(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
