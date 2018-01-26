package ca.pelliott.pelliott_subbook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static ca.pelliott.pelliott_subbook.EditSubscriptionActivity.SUBSCRIPTION_EXTRA;

public class MainActivity extends AppCompatActivity {

    private SubscriptionArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.ListView);

        // set the adapter
        adapter = new SubscriptionArrayAdapter(this, SubscriptionList.getArray());
        listview.setAdapter(adapter);

        // set the listener for list items

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                if (position > 0) {
                    // handle total case
                    position -= 1;

                    Intent intent = new Intent(view.getContext(), ViewSubscriptionActivity.class);
                    intent.putExtra(SUBSCRIPTION_EXTRA, position);

                    startActivity(intent);
                }
            }
        });

        // set the listener for new subscriptions

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewSubscriptionActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // make sure that we show the newest data
        adapter.notifyDataSetChanged();
    }
}
