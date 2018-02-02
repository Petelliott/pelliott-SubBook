/* NewSubscriptionActivity
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

import android.widget.EditText;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * NewSubscriptionActivity lets the user create a new subscription and add
 * it to SubscriptionList.
 *
 * @author pelliott
 * @version 1.0
 *
 * @see SubscriptionModifyActivity
 */
public class NewSubscriptionActivity extends SubscriptionModifyActivity {

    /**
     * creates a new Subscription if all checks pass, then ends the activity.
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

        Subscription sub;
        try {
            sub = new Subscription(name, date, price, comment);
        } catch (InvalidParameterException e) {
            makeSnackBar(e.getMessage());
            return;
        }

        SubscriptionList.getInstance(getBaseContext()).addSubscr(sub);
        setResult(RESULT_OK);
        finish();
    }
}
