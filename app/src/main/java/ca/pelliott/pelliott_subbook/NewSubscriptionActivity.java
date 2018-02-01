package ca.pelliott.pelliott_subbook;

import android.widget.EditText;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * creates a new subscription and adds it to SubscriptionList
 */

public class NewSubscriptionActivity extends SubscriptionModifyActivity {

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
        } catch(NumberFormatException e) {
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
        } catch(InvalidParameterException e) {
            makeSnackBar(e.getMessage());
            return;
        }

        SubscriptionList.getInstance(getBaseContext()).addSubscr(sub);
        setResult(RESULT_OK);
        finish();
    }
}
