/* SubscriptionModifyActivity
 *
 * Version 1.0
 *
 * Feb 01, 2018
 *
 * Copyright (c) 2018 Peter Elliott, CMPUT301, University of Alberta - All
 * rights Reserved you may use, distribute or modify this code under terms and
 * conditions of Code of Student Behavior at University of Alberta you can find
 * a copy of the license in this project. Otherwise, please contact
 * pelliott@ualberta.ca
 */
package ca.pelliott.pelliott_subbook;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * SubscriptionModifyActivity provides an abstract class for the subscription
 * editing screen used by EditSubscriptionActivity and NewSubscriptionActivity.
 *
 * @author pelliott
 * @version 1.0
 *
 * @see NewSubscriptionActivity
 * @see EditSubscriptionActivity
 */
public abstract class SubscriptionModifyActivity extends AppCompatActivity {

    /**
     * creates the Activity.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);
    }

    /**
     * creates the option menu.
     *
     * @param menu the menu to be inflated
     * @return unused (true)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // from https://developer.android.com/guide/topics/ui/menus.html
        // 2018-01-22 (Apache 2.0)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_sub_menu, menu);
        return true;
    }

    // from https://developer.android.com/guide/topics/ui/menus.html
    // 2018-01-22

    /**
     * handles clicking an option item.
     *
     * @param item the item the user has clicked
     * @return a boolean indicating if an item was clicked
     */
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

    /**
     * displays a SnackBar with the String message.
     *
     * @param message the snackbar string
     */
    public void makeSnackBar(String message) {
        Snackbar snackbar;
        snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Action", null);
        snackbar.show();
    }

    /**
     * called by onOptionsItemSelected when the user presses the checkmark
     * defined by subclasses.
     */
    protected abstract void onEditFinish();
}
