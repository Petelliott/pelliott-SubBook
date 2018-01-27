package ca.pelliott.pelliott_subbook;

import android.widget.EditText;

import java.security.InvalidParameterException;

/**
 * creates a new subscription and adds it to SubscriptionList
 */

public class NewSubscriptionActivity extends SubscriptionModifyActivity {

    @Override
    protected void onEditFinish() {
        EditText editname    = (EditText) findViewById(R.id.editName);
        EditText editcomment = (EditText) findViewById(R.id.editComment);
        EditText editprice   = (EditText) findViewById(R.id.editPrice);

        String name    = editname.getText().toString();
        String comment = editcomment.getText().toString();
        double price;
        try {
            price = Double.parseDouble(editprice.getText().toString());
        } catch(NumberFormatException e) {
            makeSnackBar("must enter a valid price");
            return;
        }

        Subscription sub;
        try {
            sub = new Subscription(name, price, comment);
        } catch(InvalidParameterException e) {
            makeSnackBar(e.getMessage());
            return;
        }

        SubscriptionList.getInstance(getBaseContext()).addSubscr(sub);
        setResult(RESULT_OK);
        finish();
    }
}
