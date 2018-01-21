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
 * Created by peter on 20/01/18.
 */

public class EditSubscriptionActivity extends AppCompatActivity {

    public static final String SUBSCRIPTION_EXTRA = "ca.pelliott.SUBSCRIPTION_EXTRA";

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
                Subscription sub = checkSubscription();
                if (sub != null) {
                    Intent data = new Intent(this, MainActivity.class);

                    data.putExtra(SUBSCRIPTION_EXTRA, sub);
                    setResult(RESULT_OK, data);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Subscription checkSubscription() {
        EditText editname    = (EditText) findViewById(R.id.editName);
        EditText editcomment = (EditText) findViewById(R.id.editComment);
        EditText editprice   = (EditText) findViewById(R.id.editPrice);

        String name    = editname.getText().toString();
        String comment = editcomment.getText().toString();
        double price;
        try {
            price = Double.parseDouble(editprice.getText().toString());
        } catch(NumberFormatException e) {
            Snackbar.make(findViewById(android.R.id.content), "must enter a price", Snackbar.LENGTH_LONG).show();
            return null;
        }

        Subscription sub;

        try {
            sub = new Subscription(name, price, comment);
        } catch(InvalidParameterException e) {
            Snackbar.make(findViewById(android.R.id.content), e.getMessage(), Snackbar.LENGTH_LONG).show();
            return null;
        }
        return sub;
    }
}
