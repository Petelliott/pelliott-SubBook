package ca.pelliott.pelliott_subbook;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.security.InvalidParameterException;

/**
 * provides an abstract class for the subscription editing screen
 * used by EditSubscriptionActivity and NewSubscriptionActivity
 */

public abstract class SubscriptionModifyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // from https://developer.android.com/guide/topics/ui/menus.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_sub_menu, menu);
        return true;
    }

    // from https://developer.android.com/guide/topics/ui/menus.html
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                onEditFinish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void makeSnackBar(String message) {
        Snackbar snackbar;
        snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null);
        snackbar.show();
    }

    protected abstract void onEditFinish();
}
