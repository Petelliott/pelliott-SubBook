package ca.pelliott.pelliott_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static ca.pelliott.pelliott_subbook.EditSubscriptionActivity.SUBSCRIPTION_EXTRA;

/**
 * gets passed an index to display and provides the option to edit
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
        sub = SubscriptionList.getSubscr(index);

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

    private void showSubcription(Subscription sub) {
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
