/* EditSubscriptionActivity
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

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * the EditSubscriptionActivity is passed the index of the Subscription
 * in SubscriptionList. it handles updating the subscription
 *
 * @author pelliott
 * @version 1.0
 *
 * @see SubscriptionModifyActivity
 */
public class EditSubscriptionActivity extends SubscriptionModifyActivity {

    /**
     * the intent string for sending an int Subscription index.
     */
    public static final String SUBSCRIPTION_EXTRA =
            "ca.pelliott.SUBSCRIPTION_EXTRA";
    /**
     * the subscription being edited.
     */
    private Subscription sub;

    /**
     * creates the EditSubscription Activity.
     *
     * @param savedInstanceState the android bundle of the state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the index passed by ViewSubscriptionActivity

        Intent intent = getIntent();

        sub = SubscriptionList.getInstance(getBaseContext()).getSubscr(
                intent.getIntExtra(SUBSCRIPTION_EXTRA, -1));

        // show the data from the edited subscription

        EditText editname    = (EditText) findViewById(R.id.editName);
        EditText editcomment = (EditText) findViewById(R.id.editComment);
        EditText editprice   = (EditText) findViewById(R.id.editPrice);
        EditText editdate    = (EditText) findViewById(R.id.editDate);

        editname.setText(sub.getName());
        editcomment.setText(sub.getComment());
        editprice.setText(String.format("%.2f", sub.getCharge()));

        DateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
        editdate.setText(datef.format(sub.getDate()));
    }

    /**
     * attempts to update the given subscription. if sucessful, end activity
     */
    @Override
    protected void onEditFinish() {
        EditText editname    = (EditText) findViewById(R.id.editName);
        EditText editcomment = (EditText) findViewById(R.id.editComment);
        EditText editprice   = (EditText) findViewById(R.id.editPrice);
        EditText editdate    = (EditText) findViewById(R.id.editDate);

        String name    = editname.getText().toString();
        String comment = editcomment.getText().toString();
        double price;
        try {
            price = Double.parseDouble(editprice.getText().toString());
        } catch (NumberFormatException e) {
            makeSnackBar("must enter a valid price");
            return;
        }

        String datestr = editdate.getText().toString();
        Date date;
        if (datestr.isEmpty()) {
            date = new Date();
        } else {
            try {
                DateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
                date = datef.parse(datestr);
            } catch (ParseException e) {
                makeSnackBar("invalid date");
                return;
            }
        }

        try {
            sub.setName(name);
            sub.setComment(comment);
            sub.setCharge(price);
            sub.setDate(date);
        } catch (InvalidParameterException e) {
            makeSnackBar(e.getMessage());
            return;
        }

        setResult(RESULT_OK);
        finish();
    }
}
