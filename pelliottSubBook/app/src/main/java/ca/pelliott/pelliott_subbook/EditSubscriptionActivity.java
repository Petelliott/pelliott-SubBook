package ca.pelliott.pelliott_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.security.InvalidParameterException;

/**
 * the EditSubscriptionActivity is passed the index of the Subscription
 * in SubscriptionList. it handles updating the subscription
 */

public class EditSubscriptionActivity extends SubscriptionModifyActivity {

    public static final String SUBSCRIPTION_EXTRA = "ca.pelliott.SUBSCRIPTION_EXTRA";
    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the index passed by ViewSubscriptionActivity

        Intent intent = getIntent();

        sub = SubscriptionList.getInstance(getBaseContext()).getSubscr(intent.getIntExtra(SUBSCRIPTION_EXTRA, -1));

        // show the data from the edited subscription

        EditText editname    = (EditText) findViewById(R.id.editName);
        EditText editcomment = (EditText) findViewById(R.id.editComment);
        EditText editprice   = (EditText) findViewById(R.id.editPrice);

        editname.setText(sub.getName());
        editcomment.setText(sub.getComment());
        editprice.setText(String.format("%.2f", sub.getCharge()));
    }

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

        try {
            sub.setName(name);
            sub.setComment(comment);
            sub.setCharge(price);
        } catch(InvalidParameterException e) {
            makeSnackBar(e.getMessage());
            return;
        }

        setResult(RESULT_OK);
        finish();
    }

}
