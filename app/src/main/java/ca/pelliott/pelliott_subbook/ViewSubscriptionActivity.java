/* ViewSubscriptionActivity
 *
 * Version 1.0
 *
 * Feb 01, 2018
 *
 * Copyright (c) 2018 Peter Elliott, CMPUT301, University of Alberta - All rights Reserved
 * you may use, distribute or modify this code under terms and conditions of Code of Student
 * Behavior at University of Alberta
 * you can find a copy of the license in this project. Otherwise, please contact contact@abc.ca
 */
package ca.pelliott.pelliott_subbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static ca.pelliott.pelliott_subbook.EditSubscriptionActivity.SUBSCRIPTION_EXTRA;


/**
 * ViewSubscriptionActivty gets passed, by intent, an index to display and provides
 * the option to edit as well as delete
 *
 * @author pelliott
 * @version 1.0
 *
 * @see MainActivity
 * @see SubscriptionList
 */
public class ViewSubscriptionActivity extends AppCompatActivity {
    static final int EDIT_SUBSCRIPTION_REQUEST = 1;

    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        Intent intent = getIntent();
        final int index = intent.getIntExtra(SUBSCRIPTION_EXTRA, -1);
        sub = SubscriptionList.getInstance(getBaseContext()).getSubscr(index);

        showSubcription(sub);

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.editSub);

        fabEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditSubscriptionActivity.class);
                intent.putExtra(SUBSCRIPTION_EXTRA, index);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showSubcription(sub);
    }

    /**
     * displays the contents of a subscription to the view
     *
     * @param sub the subscription you wish to display
     */
    private void showSubcription(Subscription sub) {
        TextView showName    = (TextView) findViewById(R.id.showName);
        TextView showComment = (TextView) findViewById(R.id.showComment);
        TextView showDate    = (TextView) findViewById(R.id.showDate);
        TextView showPrice   = (TextView) findViewById(R.id.showPrice);
        TextView showInfo    = (TextView) findViewById(R.id.showInfo);

        showName.setText(sub.getName());
        showComment.setText(sub.getComment());

        DateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
        showDate.setText(datef.format(sub.getDate()));
        showPrice.setText(String.format("$%.2f", sub.getCharge()));

        double total = 0;

        for (Subscription i: SubscriptionList.getInstance(getBaseContext()).getArray()) {
            total += i.getCharge();
        }

        double percent = 100.0 * sub.getCharge() / total;

        showInfo.setText(
                String.format("This subscription is %.0f%% of your monthly total", percent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // from https://developer.android.com/guide/topics/ui/menus.html
        // 2018-01-23 (Apache 2.0)
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_sub_menu, menu);
        return true;
    }

    // from https://developer.android.com/guide/topics/ui/menus.html
    // 2018-01-23 (Apache 2.0)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder confirm = new AlertDialog.Builder(this);
                confirm.setMessage("are you sure you want to delete this Subscription?");

                // idea from http://jymden.com/android-simple-confirm-dialog/ (2018-01-23)
                confirm.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        SubscriptionList.getInstance(getBaseContext()).remove(sub);
                        finish(); // end the activity so we remove the invalid reference
                    }
                });

                confirm.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                confirm.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
