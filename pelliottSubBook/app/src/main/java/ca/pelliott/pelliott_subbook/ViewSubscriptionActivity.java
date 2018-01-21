package ca.pelliott.pelliott_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static ca.pelliott.pelliott_subbook.EditSubscriptionActivity.SUBSCRIPTION_EXTRA;

/**
 * Created by peter on 21/01/18.
 */

public class ViewSubscriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        Intent intent = getIntent();
        Subscription sub = (Subscription) intent.getSerializableExtra(SUBSCRIPTION_EXTRA);

        TextView showName    = (TextView) findViewById(R.id.showName);
        TextView showComment = (TextView) findViewById(R.id.showComment);
        TextView showDate    = (TextView) findViewById(R.id.showDate);
        TextView showPrice   = (TextView) findViewById(R.id.showPrice);

        showName.setText(sub.getName());
        showComment.setText(sub.getComment());
        showDate.setText(sub.getDate().toString());
        showPrice.setText(String.format("$%.2f", sub.getCharge()));
    }
}
